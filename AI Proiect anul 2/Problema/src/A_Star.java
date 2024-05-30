import java.util.*;

public class A_Star {
    static class Node implements Comparable<Node> {
        int city; // Represents the current city index
        int visited; // Visited cities
        long cost; // Cost accumulated
        long heuristic; // Heuristic value for A* algorithm
        List<Integer> path; // List representing the current path

        // Constructor to initialize the Node
        public Node(int city, int visited, long cost, long heuristic, List<Integer> path) {
            this.city = city;
            this.visited = visited;
            this.cost = cost;
            this.heuristic = heuristic;
            this.path = new ArrayList<>(path); // Make a copy of the path list
            this.path.add(city + 1); // Add the current city to the path (1-indexed path)
        }

        // Method to compare Nodes based on their total cost + heuristic
        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost + this.heuristic, other.cost + other.heuristic);
        }
    }

    // Method to find the minimum cost Hamiltonian cycle using A* algorithm
    public static long tspAStar(int[][] graph, List<Integer> minPath) {
        int n = graph.length; // Number of cities
        long minCost = Long.MAX_VALUE; // Initialize minimum cost to maximum value
        PriorityQueue<Node> queue = new PriorityQueue<>(); // Priority queue for A* algorithm
        int initialVisited = 1; // Starting with only the first city visited
        List<Integer> initialPath = new ArrayList<>(); // Initial path list
        queue.add(new Node(0, initialVisited, 0L, computeMSTCost(getVisitedCities(initialVisited, n), graph), initialPath)); // Add initial Node to the queue

        // A* algorithm loop
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll(); // Extract the Node with the lowest cost
            int currentCity = currentNode.city; // Current city index
            int currentVisited = currentNode.visited; // Bitmask representing visited cities
            long currentCost = currentNode.cost; // Accumulated cost so far
            List<Integer> currentPath = currentNode.path; // Current path

            // Check if all cities have been visited
            if (currentVisited == (1 << n) - 1) {
                if (graph[currentCity][0] > 0) { // Check if there's a path back to the starting city
                    long totalCost = currentCost + graph[currentCity][0]; // Calculate total cost of the cycle
                    if (totalCost < minCost) { // If total cost is less than the current minimum
                        minCost = totalCost; // Update minimum cost
                        minPath.clear(); // Clear previous minimum path
                        minPath.addAll(currentPath); // Update minimum path
                        minPath.add(1); // Add the starting city to complete the cycle
                    }
                }
                continue; // Continue to the next iteration
            }

            // Loop through all possible next cities
            for (int nextCity = 0; nextCity < n; nextCity++) {
                if ((currentVisited & (1 << nextCity)) == 0 && graph[currentCity][nextCity] > 0) { // If the next city is not visited and there's a path to it
                    int nextVisited = currentVisited | (1 << nextCity); // Mark the next city as visited
                    long costToNext = currentCost + graph[currentCity][nextCity]; // Calculate the cost to reach the next city
                    long heuristic = computeMSTCost(getVisitedCities(nextVisited, n), graph); // Calculate heuristic value
                    queue.add(new Node(nextCity, nextVisited, costToNext, heuristic, currentPath)); // Add the next Node to the queue
                }
            }
        }

        // Return the minimum cost found
        return minCost == Long.MAX_VALUE ? -1 : minCost; // If no valid cycle found, return -1
    }

    // Method to compute the minimum spanning tree (MST) cost using Prim's algorithm
    private static long computeMSTCost(boolean[] visitedCities, int[][] graph) {
        int n = graph.length; // Number of cities
        long totalWeight = 0; // Total weight of the MST
        boolean[] inMST = new boolean[n]; // Array to mark if a city is in the MST
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Priority queue for Prim's algorithm

        int startCity = -1; // Initialize start city index
        for (int i = 0; i < n; i++) {
            if (!visitedCities[i]) { // Find the first unvisited city
                startCity = i;
                break;
            }
        }

        if (startCity == -1) { // If all cities are visited, return 0 (no need to build MST)
            return 0;
        }

        pq.add(new int[]{startCity, 0}); // Add the start city to the priority queue with weight 0
        while (!pq.isEmpty()) {
            int[] edge = pq.poll(); // Extract the edge with the lowest weight
            int v = edge[0]; // Vertex of the edge
            int weight = edge[1]; // Weight of the edge

            if (!inMST[v]) { // If the vertex is not in the MST
                inMST[v] = true; // Mark it as in the MST
                totalWeight += weight; // Add the edge weight to the total weight of the MST

                // Explore all edges from the current vertex
                for (int u = 0; u < n; u++) {
                    if (!inMST[u] && !visitedCities[u] && graph[v][u] > 0) { // If the adjacent vertex is not in the MST and there's a path to it
                        pq.add(new int[]{u, graph[v][u]}); // Add the edge to the priority queue
                    }
                }
            }
        }

        // Return the total weight of the MST
        return totalWeight;
    }

    // Method to convert the visited bitmask to an array of visited cities
    private static boolean[] getVisitedCities(int visited, int n) {
        boolean[] visitedCities = new boolean[n]; // Array to store visited cities
        for (int i = 0; i < n; i++) {
            visitedCities[i] = ((visited >> i) & 1) == 1; // Check if the i-th bit is set in the visited bitmask
        }
        return visitedCities; // Return the array of visited cities
    }
}
