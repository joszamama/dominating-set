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

        System.out.println("Starting algorithm...");
        Set<Integer> startingSolution = InitialSolution.apply(graph); // D <- InitialSolution(G)
        Set<Integer> incumbentSolution = LocalImprovement.apply(startingSolution); // Db <- LocalImprovement(D)
        int i = 0; // i <- 0;

        System.out.println("Initial Solution: " + incumbentSolution);
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

    public static void main(String[] args) {
        // Instantiating the parameters
        Graph graph = new Graph("rnd_graph_5000_50_4.txt");
        double REMOVE_VERTICES_PERCENTAGE = 0.15;
        int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 1000;

        LocalImprovement localImprovement = new LocalImprovement(graph);
        Destruction destruction = new Destruction(graph);
        Reconstruction reconstruction = new Reconstruction(graph);

        // Instantiating the functions
        Function<Graph, Set<Integer>> GreedyInsertion = com.dominatingset.components.InitialSolution::greedyInsertion;
        Function<Set<Integer>, Set<Integer>> Exchange = localImprovement::exchange;
        BiFunction<Set<Integer>, Double, Set<Integer>> RandomDestruction = destruction::randomDestruction;
        Function<Set<Integer>, Set<Integer>> Reconstruction = reconstruction::greedyReconstruction;

        // Get time before the algorithm
        long startTime = System.currentTimeMillis();

        // Calling the Iterated Greedy algorithm
        IteratedGreedy(graph, REMOVE_VERTICES_PERCENTAGE, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, GreedyInsertion,
                Exchange, RandomDestruction, Reconstruction);

        // Get time after the algorithm
        long endTime = System.currentTimeMillis();

        // Print the time it took to run the algorithm in seconds
        System.out.println("Runtime: " + (endTime - startTime) / 1000.0 + "s");
    }
}
