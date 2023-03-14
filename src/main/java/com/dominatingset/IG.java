package com.dominatingset;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class IG {

    Graph graph;
    Integer MAX_ITERATIONS_WITHOUT_IMPROVEMENT;
    Double REMOVE_VERTICES_PERCENTAGE;

    Function<Graph, Set<Integer>> InitialSolution;
    Function<Set<Integer>, Set<Integer>> LocalImprovement;
    BiFunction<Set<Integer>, Double, Set<Integer>> Destruction;
    Function<Set<Integer>, Set<Integer>> Reconstruction;

    public IG(Graph graph, Integer MAX_ITERATIONS_WITHOUT_IMPROVEMENT, Double REMOVE_VERTICES_PERCENTAGE, String IS,
            String LI, String D, String R) {

        this.graph = graph;
        this.MAX_ITERATIONS_WITHOUT_IMPROVEMENT = MAX_ITERATIONS_WITHOUT_IMPROVEMENT;
        this.REMOVE_VERTICES_PERCENTAGE = REMOVE_VERTICES_PERCENTAGE;

        switch (IS) {
            case "greedyInsertion":
                InitialSolution = com.dominatingset.components.InitialSolution::greedyInsertion;
                break;
            case "greedyDeletion":
                InitialSolution = com.dominatingset.components.InitialSolution::greedyDeletion;
                break;
        }

        switch (LI) {
            case "exchange":
                LocalImprovement = new com.dominatingset.components.LocalImprovement(graph)::exchange;
                break;
            // add more cases for other LocalImprovement methods
        }

        switch (D) {
            case "randomDestruction":
                Destruction = new com.dominatingset.components.Destruction(graph)::randomDestruction;
                break;
            case "randomSlicing":
                Destruction = new com.dominatingset.components.Destruction(graph)::randomSlicing;
                break;
            case "greedyDestruction":
                Destruction = new com.dominatingset.components.Destruction(graph)::greedyDestruction;
                break;
        }

        switch (R) {
            case "randomReconstruction":
                Reconstruction = new com.dominatingset.components.Reconstruction(graph)::randomReconstruction;
                break;
            // add more cases for other Reconstruction methods
        }
    }

    public void run() {
        System.out.println("\nGenerating initial solution...");
        Set<Integer> startingSolution = InitialSolution.apply(graph); // D <- InitialSolution(G)
        System.out.println("Initial Solution with size: " + startingSolution.size());

        System.out.println("\nApplying local improvement...");
        Set<Integer> incumbentSolution = LocalImprovement.apply(startingSolution); // Db <- LocalImprovement(D)
        System.out.println("Improved Solution with size: " + incumbentSolution.size());

        int i = 0; // i <- 0;

        System.out.println("\nApplying Iterated Greedy...");
        while (i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT) { // while i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT do
            Set<Integer> copy = new HashSet<>(incumbentSolution); // Dd <- Db
            Set<Integer> unfeasableSolution = Destruction.apply(copy, REMOVE_VERTICES_PERCENTAGE);
            Set<Integer> feasibleSolution = Reconstruction.apply(unfeasableSolution); // Dr <- Reconstruction(Dd)
            Set<Integer> minimalSolution = LocalImprovement.apply(feasibleSolution); // Di <- LocalImprovement(Dr)

            if (minimalSolution.size() < incumbentSolution.size()) { // if |Di| < |Db| then,
                System.out.println("Smaller DS found with size: " + minimalSolution.size());
                incumbentSolution = new HashSet<>(minimalSolution); // Db <- Di
                i = 0; // i <- 0
            } else { // else
                i++; // i <- i + 1
            } // end if
        } // end while
        System.out.println("\nSolution is: " + incumbentSolution + ", with size: " + incumbentSolution.size());
    }

    public static void main(String args[]) {
        // Instantiating the parameters
        String file = "rnd_graph_500_50_1.txt";
        double REMOVE_VERTICES_PERCENTAGE = 0.2;
        int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 250;

        String InitialSolutionMethod = "greedyInsertion";
        String LocalImprovementMethod = "exchange";
        String DestructionMethod = "randomDestruction";
        String ReconstructionMethod = "randomReconstruction";

        // Instantiating the graph
        Graph graph = new Graph(file);

        // Instantiating the Iterated Greedy
        IG ig = new IG(graph, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, REMOVE_VERTICES_PERCENTAGE, InitialSolutionMethod,
                LocalImprovementMethod, DestructionMethod, ReconstructionMethod);

        // Print IG parameters
        System.out.println("Parameters:");
        System.out.println("Graph: " + ig.graph);
        System.out.println("MAX_ITERATIONS_WITHOUT_IMPROVEMENT: " + ig.MAX_ITERATIONS_WITHOUT_IMPROVEMENT);
        System.out.println("REMOVE_VERTICES_PERCENTAGE: " + ig.REMOVE_VERTICES_PERCENTAGE);

        // Print IG methods
        System.out.println("Methods:");
        System.out.println("InitialSolution: " + ig.InitialSolution);
        System.out.println("LocalImprovement: " + ig.LocalImprovement);
        System.out.println("Destruction: " + ig.Destruction);
        System.out.println("Reconstruction: " + ig.Reconstruction);
    }
}
