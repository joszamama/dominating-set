package com.dominatingset;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.dominatingset.functionalities.generators.AdjacencyMatrixGenerator;

public class MDSP {

    public static void IteratedGreedy(boolean[][] graph, double REMOVE_VERTICES_PERCENTAGE,
            int MAX_ITERATIONS_WITHOUT_IMPROVEMENT,
            Function<boolean[][], Set<Integer>> InitialSolution,
            Function<Set<Integer>, Set<Integer>> LocalImprovement,
            BiFunction<Set<Integer>, Double, Set<Integer>> Destruction,
            Function<Set<Integer>, Set<Integer>> Reconstruction) {

        Set<Integer> startingSolution = InitialSolution.apply(graph); // D <- InitialSolution(G)
        Set<Integer> incumbentSolution = LocalImprovement.apply(startingSolution); // Db <- LocalImprovement(D)
        int i = 0; // i <- 0;

        while (i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT) { // while i < MAX_ITERATIONS_WITHOUT_IMPROVEMENT do
            Set<Integer> unfeasableSolution = Destruction.apply(incumbentSolution, REMOVE_VERTICES_PERCENTAGE); // Dd <- Destruction(Db, REMOVE_VERTICES_PERCENTAGE)
            Set<Integer> feasibleSolution = Reconstruction.apply(unfeasableSolution); // Dr <- Reconstruction(Dd)
            Set<Integer> minimalSolution = LocalImprovement.apply(feasibleSolution); // Di <- LocalImprovement(Dr)

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

        boolean[][] graph = AdjacencyMatrixGenerator.generateMatrix(50, 0.25);
        double REMOVE_VERTICES_PERCENTAGE = 0.1;
        int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 100;

        // Instantiating the functions
        Function<boolean[][], Set<Integer>> InitialSolution = com.dominatingset.components.InitialSolution::InitialSolution2;
        Function<Set<Integer>, Set<Integer>> LocalImprovement = com.dominatingset.components.LocalImprovement::LocalImprovement1;
        BiFunction<Set<Integer>, Double, Set<Integer>> Destruction = com.dominatingset.components.Destruction::Destruction1;
        Function<Set<Integer>, Set<Integer>> Reconstruction = com.dominatingset.components.Reconstruction::Reconstruction1;

        // Calling the Iterated Greedy algorithm
        IteratedGreedy(graph, REMOVE_VERTICES_PERCENTAGE, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, InitialSolution,
                LocalImprovement, Destruction, Reconstruction);
    }
}
