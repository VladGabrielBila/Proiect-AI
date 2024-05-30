import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Generate a random graph
        RandomGraph generator = new RandomGraph();
        int[][] graph = generator.generateRandomGraph(15, 0.5, 10); // Example: 15 vertices, density 0.5, max weight 10

        // Measure time and execute BFS
        List<Integer> minPathBFS = new ArrayList<>();
        long startTimeBFS = System.nanoTime();
        long minCostBFS = BFS.tspBFS(graph, minPathBFS);
        long endTimeBFS = System.nanoTime();
        double durationBFS = (endTimeBFS - startTimeBFS) / 1_000_000_000.0;

        // Measure time and execute UCS
        List<Integer> minPathUCS = new ArrayList<>();
        long startTimeUCS = System.nanoTime();
        long minCostUCS = UCS.tspUCS(graph, minPathUCS);
        long endTimeUCS = System.nanoTime();
        double durationUCS = (endTimeUCS - startTimeUCS) / 1_000_000_000.0;

        // Measure time and execute A* with MST heuristic
        List<Integer> minPathAStar = new ArrayList<>();
        long startTimeAStar = System.nanoTime();
        long minCostAStar = A_Star.tspAStar(graph, minPathAStar);
        long endTimeAStar = System.nanoTime();
        double durationAStar = (endTimeAStar - startTimeAStar) / 1_000_000_000.0;

        try {
            FileWriter writer = new FileWriter("output.txt", true);
            generator.writeGraphToFile(graph, writer);
            writer.write("\n");
            writer.write("Minimum travel cost using BFS: " + minCostBFS + "\n");
            writer.write("Path (BFS): " + minPathBFS + "\n");
            writer.write("Time taken (BFS): " + durationBFS + " seconds\n");
            writer.write("\n");
            writer.write("Minimum travel cost using UCS: " + minCostUCS + "\n");
            writer.write("Path (UCS): " + minPathUCS + "\n");
            writer.write("Time taken (UCS): " + durationUCS + " seconds\n");
            writer.write("\n");
            writer.write("Minimum travel cost using A* with MST heuristic: " + minCostAStar + "\n");
            writer.write("Path (A* with MST heuristic): " + minPathAStar + "\n");
            writer.write("Time taken (A* with MST heuristic): " + durationAStar + " seconds\n");
            writer.write("\n--------------------------------\n");
            writer.close();
            System.out.println("Results appended to output.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
}
