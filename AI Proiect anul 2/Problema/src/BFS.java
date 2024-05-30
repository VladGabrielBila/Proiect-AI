import java.util.*;

public class BFS {
    //Node class to represent a state in the search space
    static class Node {
        int city;//Current city
        int visited;//Visited cities
        long cost;//Total cost
        List<Integer> path;//Path taken

        //Constructor to initialize a Node
        public Node(int city, int visited, long cost, List<Integer> path) {
            this.city = city;
            this.visited = visited;
            this.cost = cost;
            this.path = new ArrayList<>(path); //List for path
            this.path.add(city + 1); // 1-indexed path
        }
    }

    // Function to perform Breadth First Search for Traveling Salesman Problem
    public static long tspBFS(int[][] graph, List<Integer> minPath) {
        int n = graph.length;// Number of cities
        long minCost = Long.MAX_VALUE;// Initialize minimum cost to maximum value
        Queue<Node> queue = new LinkedList<>();// Queue for BFS
        int initialVisited = 1;// Start with first city visited
        List<Integer> initialPath = new ArrayList<>();// Start with empty path
        queue.add(new Node(0, initialVisited, 0L, initialPath));// Add initial state to queue
        // Perform BFS
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();// Dequeue current state
            // If all cities are visited
            if (currentNode.visited == (1 << n) - 1) {
                // Check if there's a path back to the starting city
                if (graph[currentNode.city][0] > 0) {
                    long totalCost = currentNode.cost + graph[currentNode.city][0]; // Total cost of path
                    // Update minimum cost and path if this path is cheaper
                    if (totalCost < minCost) {
                        minCost = totalCost;
                        minPath.clear();// Clear previous path
                        minPath.addAll(currentNode.path); // Add starting city to complete the cycle
                        minPath.add(1); // Return to the starting city
                    }
                }
                continue;// Skip to next iteration
            }
            // Iterate over neighbors (cities that can be visited)
            for (int neighbor = 0; neighbor < n; neighbor++) {
                // If neighbor is not visited and there is a path to it
                if ((currentNode.visited & (1 << neighbor)) == 0 && graph[currentNode.city][neighbor] > 0) {
                    int nextVisited = currentNode.visited | (1 << neighbor);// Mark neighbor as visited
                    long newCost = currentNode.cost + graph[currentNode.city][neighbor]; // Update cost
                    // If new cost exceeds current minimum cost, skip
                    if (newCost >= minCost)
                        continue;
                    // Add new state (neighbor) to queue
                    queue.add(new Node(neighbor, nextVisited, newCost, currentNode.path));
                }
            }
        }
        // Return minimum cost or -1 if no valid path found
        return minCost == Long.MAX_VALUE ? -1 : minCost;
    }
}