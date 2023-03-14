package com.dominatingset;

public class MDSP {

    public static void runIG(String file, double REMOVE_VERTICES_PERCENTAGE, int MAX_ITERATIONS_WITHOUT_IMPROVEMENT,
            String InitialSolutionMethod, String LocalImprovementMethod, String DestructionMethod,
            String ReconstructionMethod) {

        // Instantiating the graph
        System.out.println("\n ------- Iterated Greedy Algorithm -------");
        System.out.println("Instantiating the graph...");

        Graph graph = new Graph(file);
        IG ig = new IG(graph, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, REMOVE_VERTICES_PERCENTAGE, InitialSolutionMethod,
                LocalImprovementMethod, DestructionMethod, ReconstructionMethod);

        // Get time before the algorithm
        long startTime = System.currentTimeMillis();

        // Calling the Iterated Greedy algorithm
        ig.run();

        // Get time after the algorithm
        long endTime = System.currentTimeMillis();

        // Print the time it took to run the algorithm in seconds
        System.out.println("Runtime: " + (endTime - startTime) / 1000.0 + "s");
        System.out.println(" --------------------------------\n");
    }

    public static void main(String[] args) {
        // Instantiating the parameters
        String file = "rnd_graph_5000_50_1.txt";
        double REMOVE_VERTICES_PERCENTAGE = 0.2;
        int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 250;

        String InitialSolutionMethod = "greedyInsertion";
        String LocalImprovementMethod = "exchange";
        String DestructionMethod = "randomDestruction";
        String ReconstructionMethod = "randomReconstruction";

        // Calling the Iterated Greedy algorithm
        runIG(file, REMOVE_VERTICES_PERCENTAGE, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, InitialSolutionMethod,
                LocalImprovementMethod, DestructionMethod, ReconstructionMethod);

    }
}
