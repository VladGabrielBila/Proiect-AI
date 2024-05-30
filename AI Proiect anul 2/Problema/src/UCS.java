import java.util.*;

public class UCS {
    static class Node implements Comparable<Node> {
        int city;  // Stores the current city
        int visited;  // Stores the bitmask of visited cities
        long cost;  // Stores the cost of reaching the current city
        List<Integer> path;  // Stores the path taken so far

        // Constructor to initialize Node
        public Node(int city, int visited, long cost, List<Integer> path) {
            this.city = city;
            this.visited = visited;
            this.cost = cost;
            this.path = new ArrayList<>(path);  // Copy the path
            this.path.add(city + 1); // 1-indexed path
        }

        // Comparison method for priority queue ordering
        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    // Method to find the minimum cost of visiting all cities (TSP) using UCS
    public static long tspUCS(int[][] graph, List<Integer> minPath) {
        int n = graph.length;  // Number of cities
        long minCost = Long.MAX_VALUE;  // Initialize minimum cost
        PriorityQueue<Node> queue = new PriorityQueue<>();  // Priority queue for UCS
        int initialVisited = 1;  // Starting with city 0 visited
        List<Integer> initialPath = new ArrayList<>();  // Empty initial path
        queue.add(new Node(0, initialVisited, 0L, initialPath));  // Add starting node to queue

        // Track minimum costs for each (city, visited) pair to prune paths
        Map<String, Long> minCostMap = new HashMap<>();
        minCostMap.put("0|1", 0L);  // Initial cost for starting city

        // Perform UCS
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();  // Get the node with minimum cost
            int currentCity = currentNode.city;  // Current city
            int currentVisited = currentNode.visited;  // Visited cities bitmask
            long currentCost = currentNode.cost;  // Cost to reach current city
            List<Integer> currentPath = currentNode.path;  // Path taken so far

            // Check if all cities have been visited
            if (currentVisited == (1 << n) - 1) {
                // If so, check if there's a path back to the starting city
                if (graph[currentCity][0] > 0) {
                    long totalCost = currentCost + graph[currentCity][0];  // Total cost of the path
                    // Update minimum cost and path if a cheaper path is found
                    if (totalCost < minCost) {
                        minCost = totalCost;
                        minPath.clear();
                        minPath.addAll(currentPath);
                        minPath.add(1); // Add the starting city at the end of the path
                    }
                }
                continue;  // Continue to explore other paths
            }

            // Explore the next cities
            for (int nextCity = 0; nextCity < n; nextCity++) {
                // Check if the next city is not visited and there's a path to it
                if ((currentVisited & (1 << nextCity)) == 0 && graph[currentCity][nextCity] > 0) {
                    int nextVisited = currentVisited | (1 << nextCity);  // Mark next city as visited
                    long nextCost = currentCost + graph[currentCity][nextCity];  // Calculate cost to reach next city

                    String key = nextCity + "|" + nextVisited;  // Key for cost map
                    // Update cost map if current path to next city is cheaper
                    if (!minCostMap.containsKey(key) || nextCost < minCostMap.get(key)) {
                        minCostMap.put(key, nextCost);
                        queue.add(new Node(nextCity, nextVisited, nextCost, currentPath));  // Add next node to queue
                    }
                }
            }
        }

        return minCost == Long.MAX_VALUE ? -1 : minCost;  // Return minimum cost or -1 if no valid path found
    }
}
