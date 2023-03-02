package com.dominatingset;

import java.util.Set;

import com.dominatingset.functionalities.generators.AdjacencyMatrixGenerator;

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

        Set<Integer> startingSolution = InitialSolution(graph); // D <- InitialSolution(G)
        Set<Integer> incumbentSolution = LocalImprovement(startingSolution); // Db <- LocalImprovement(D)
        int i = 0; // i <- 0;

        while (i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT) { // while i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT do
            Set<Integer> unfeasableSolution = Destruction(incumbentSolution, REMOVE_VERTICES_PERCENTAGE); // Dd <- Destruction(Db, G)
            Set<Integer> feasibleSolution = Reconstruction(unfeasableSolution); // Dr <- Reconstruction(Dd)
            Set<Integer> minimalSolution = LocalImprovement(feasibleSolution); // Di <- LocalImprovement(Dr)

            if (minimalSolution.size() < incumbentSolution.size()) { // if |Di| < |Db| then,
                incumbentSolution = minimalSolution; // Db <- Di
                i = 0; // i <- 0
            } else { // else
                i++; // i <- i + 1
            } // end if
        } // end while
        System.out.println(incumbentSolution);// return Db
    }

    public static void main(String[] args) {
        // define the adjacency matrix of the graph
        boolean[][] graph = AdjacencyMatrixGenerator.generateMatrix(50, 0.25);

        // define the percentage of vertices to be removed in the destruction phase
        double REMOVE_VERTICES_PERCENTAGE = 0.1;

        // define the maximum number of iterations without improvement
        int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 100;

        // run the algorithm
        IteratedGreedy(graph, REMOVE_VERTICES_PERCENTAGE, MAX_ITERATIONS_WITHOUT_IMPROVEMENT);
    }
}
