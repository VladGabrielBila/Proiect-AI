import java.io.*;
import java.util.*;

public class RandomGraph {
    private Random random;

    public RandomGraph() {
        this.random = new Random();
    }

    // Generate a random graph with 'numVertices' vertices and 'density' density
    public int[][] generateRandomGraph(int numVertices, double density, int maxWeight) {
        int[][] graph = new int[numVertices][numVertices];

        // Generate edges based on density
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (random.nextDouble() < density) {
                    int weight = random.nextInt(maxWeight) + 1; // Add 1 to avoid weight of 0
                    graph[i][j] = weight;
                    graph[j][i] = weight; // Assuming undirected graph
                }
            }
        }

        // Ensure no self-loops
        for (int i = 0; i < numVertices; i++) {
            graph[i][i] = 0;
        }

        return graph;
    }

    // Write the generated graph to a file
    public void writeGraphToFile(int[][] graph, FileWriter writer) throws IOException {
        writer.write("Generated Graph:\n");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                writer.write(graph[i][j] + " ");
            }
            writer.write("\n");
        }
    }
}
