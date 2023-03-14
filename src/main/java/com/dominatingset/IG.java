package com.dominatingset;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class IG {

    String file;
    Integer MAX_ITERATIONS_WITHOUT_IMPROVEMENT;
    Double REMOVE_VERTICES_PERCENTAGE;

    String IS, LI, D, R;

    Graph graph;

    Function<Graph, Set<Integer>> InitialSolution = null;
    Function<Set<Integer>, Set<Integer>> LocalImprovement = null;
    BiFunction<Set<Integer>, Double, Set<Integer>> Destruction = null;
    Function<Set<Integer>, Set<Integer>> Reconstruction = null;

    Set<Integer> solution;
    long time;

    public IG(String file, Integer MAX_ITERATIONS_WITHOUT_IMPROVEMENT, Double REMOVE_VERTICES_PERCENTAGE, String IS,
            String LI, String D, String R) {

        this.file = file;

        this.MAX_ITERATIONS_WITHOUT_IMPROVEMENT = MAX_ITERATIONS_WITHOUT_IMPROVEMENT;
        this.REMOVE_VERTICES_PERCENTAGE = REMOVE_VERTICES_PERCENTAGE;

        this.IS = IS;
        this.LI = LI;
        this.D = D;
        this.R = R;

        // Instantiating the graph
        System.out.println("\n ------- Iterated Greedy Algorithm -------");
        System.out.println("Instantiating the graph...");
        this.graph = new Graph(file);

        // Instantiating the methods
        System.out.println("Instantiating the methods...");

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
        // Start timer
        long startTime = System.currentTimeMillis();

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
          // Stop timer
        long endTime = System.currentTimeMillis();

        this.time = endTime - startTime;
        this.solution = incumbentSolution;

        System.out.println("\nSolution is: " + solution + ", with size: " + solution.size());
        System.out.println("Time: " + time / 1000.0 + " seconds");
    }

    public String getResults() {
        return "For graph: " + file + ", using MIWI: " + MAX_ITERATIONS_WITHOUT_IMPROVEMENT + " and RVP: "
                + REMOVE_VERTICES_PERCENTAGE + ", using methods: " + IS + ", " + LI + ", " + D + ", " + R
                + ", IG found a solution with size: " + solution.size()
                + " in: " + time / 1000.0 + " seconds\n";
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

        // Instantiating the Iterated Greedy
        IG ig = new IG(file, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, REMOVE_VERTICES_PERCENTAGE, InitialSolutionMethod,
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
