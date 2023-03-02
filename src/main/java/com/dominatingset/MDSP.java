package com.dominatingset;

import java.util.Set;

public class MDSP {

    public static Set<Integer> InitialSolution(boolean[][] graph) {
        return null;
    }

    public static Set<Integer> LocalImprovement(Set<Integer> minimalSolution) {
        return null;
    }

    public static Set<Integer> Destruction(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        return null;
    }

    public static Set<Integer> Reconstruction(Set<Integer> minimalSolution) {
        return null;
    }

    public static void IteratedGreedy(boolean[][] graph, double REMOVE_VERTICES_PERCENTAGE,
            int MAX_ITERATIONS_WITHOUT_IMPROVEMENT) {
        // D <- InitialSolution(G)
        Set<Integer> startingSolution = InitialSolution(graph);
        // Db <- LocalImprovement(D)
        Set<Integer> incumbentSolution = LocalImprovement(startingSolution);
        // i <- 0;
        int i = 0;

        // while i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT do
        while (i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT) {
            // Dd <- Destruction(Db, G)
            Set<Integer> unfeasableSolution = Destruction(incumbentSolution, REMOVE_VERTICES_PERCENTAGE);
            // Dr <- Reconstruction(Dd)
            Set<Integer> feasibleSolution = Reconstruction(unfeasableSolution);
            // Di <- LocalImprovement(Dr)
            Set<Integer> minimalSolution = LocalImprovement(feasibleSolution);

            // if |Di| < |Db| then
            if (minimalSolution.size() < incumbentSolution.size()) {
                // Db <- Di
                incumbentSolution = minimalSolution;
                // i <- 0
                i = 0;
                // else
            } else {
                // i <- i + 1
                i++;
            } // end if
        } // end while

        // return Db
        System.out.println(incumbentSolution);
    }

    public static void main(String[] args) {

        // create a graph
        boolean[][] graph = new boolean[3][3];

        // run the algorithm
        IteratedGreedy(graph, 0.5, 10);
    }
}
