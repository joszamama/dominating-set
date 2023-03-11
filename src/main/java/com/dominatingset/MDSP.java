package com.dominatingset;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.dominatingset.components.Destruction;
import com.dominatingset.components.LocalImprovement;
import com.dominatingset.components.Reconstruction;

public class MDSP {

    public static void IteratedGreedy(Graph graph, double REMOVE_VERTICES_PERCENTAGE,
            int MAX_ITERATIONS_WITHOUT_IMPROVEMENT,
            Function<Graph, Set<Integer>> InitialSolution,
            Function<Set<Integer>, Set<Integer>> LocalImprovement,
            BiFunction<Set<Integer>, Double, Set<Integer>> Destruction,
            Function<Set<Integer>, Set<Integer>> Reconstruction) {

        System.out.println("\nGenerating initial solution...");
        Set<Integer> startingSolution = InitialSolution.apply(graph); // D <- InitialSolution(G)
        System.out.println("Initial Solution with size: " + startingSolution.size());

        System.out.println("\nApplying local improvement...");
        Set<Integer> incumbentSolution = LocalImprovement.apply(startingSolution); // Db <- LocalImprovement(D)
        System.out.println("Improved Solution with size: " + incumbentSolution.size());

        int i = 0; // i <- 0;

        System.out.println("\nApplying Iterated Greedy...");
        while (i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT) { // while i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT do
            Set<Integer> unfeasableSolution = Destruction.apply(incumbentSolution, REMOVE_VERTICES_PERCENTAGE);
            Set<Integer> feasibleSolution = Reconstruction.apply(unfeasableSolution); // Dr <- Reconstruction(Dd)
            Set<Integer> minimalSolution = LocalImprovement.apply(feasibleSolution); // Di <- LocalImprovement(Dr)

            if (minimalSolution.size() < incumbentSolution.size()) { // if |Di| < |Db| then,
                System.out.println("Smaller DS found: " + incumbentSolution);
                incumbentSolution = minimalSolution; // Db <- Di
                i = 0; // i <- 0
            } else { // else
                i++; // i <- i + 1
            } // end if
        } // end while
        if (graph.isDominatingSet(incumbentSolution)) {
            System.out.println("Solution is: " + incumbentSolution + ", with size: " + incumbentSolution.size());
        } else {
            System.out.println("Error found, solution is not DS");
        }
    }

    public static void runIG(String file, double REMOVE_VERTICES_PERCENTAGE, int MAX_ITERATIONS_WITHOUT_IMPROVEMENT,
            String InitialSolutionMethod, String LocalImprovementMethod, String DestructionMethod,
            String ReconstructionMethod) {
        
        // Instantiating the graph
        System.out.println(" ------- Iterated Greedy Algorithm -------");
        System.out.println("Instantiating the graph...");
        Graph graph = new Graph(file);

        Function<Graph, Set<Integer>> InitialSolution = null;
        Function<Set<Integer>, Set<Integer>> LocalImprovement = null;
        BiFunction<Set<Integer>, Double, Set<Integer>> Destruction = null;
        Function<Set<Integer>, Set<Integer>> Reconstruction = null;

        switch (InitialSolutionMethod) {
            case "greedyInsertion":
                InitialSolution = com.dominatingset.components.InitialSolution::greedyInsertion;
                break;
            // add more cases for other InitialSolution methods
        }

        switch (LocalImprovementMethod) {
            case "exchange":
                LocalImprovement = new LocalImprovement(graph)::exchange;
                break;
            // add more cases for other LocalImprovement methods
        }

        switch (DestructionMethod) {
            case "randomDestruction":
                Destruction = new Destruction(graph)::randomDestruction;
                break;
            // add more cases for other Destruction methods
        }

        switch (ReconstructionMethod) {
            case "randomReconstruction":
                Reconstruction = new Reconstruction(graph)::randomReconstruction;
                break;
            // add more cases for other Reconstruction methods
        }

        // Get time before the algorithm
        long startTime = System.currentTimeMillis();

        // Calling the Iterated Greedy algorithm
        IteratedGreedy(graph, REMOVE_VERTICES_PERCENTAGE, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, InitialSolution,
                LocalImprovement, Destruction, Reconstruction);

        // Get time after the algorithm
        long endTime = System.currentTimeMillis();

        // Print the time it took to run the algorithm in seconds
        System.out.println("Runtime: " + (endTime - startTime) / 1000.0 + "s");
    }

    public static void main(String[] args) {
        // Instantiating the parameters
        String file = "rnd_graph_5000_50_1.txt";
        double REMOVE_VERTICES_PERCENTAGE = 0.15;
        int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 1000;

        String InitialSolutionMethod = "greedyInsertion";
        String LocalImprovementMethod = "exchange";
        String DestructionMethod = "randomDestruction";
        String ReconstructionMethod = "randomReconstruction";

        // Calling the Iterated Greedy algorithm
        runIG(file, REMOVE_VERTICES_PERCENTAGE, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, InitialSolutionMethod,
                LocalImprovementMethod, DestructionMethod, ReconstructionMethod);
    }
}
