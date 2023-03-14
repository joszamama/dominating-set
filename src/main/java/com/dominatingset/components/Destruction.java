package com.dominatingset.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dominatingset.Graph;

public class Destruction {

    public Set<Integer> randomDestruction(Set<Integer> solution, Double REMOVE_VERTICES_PERCENTAGE) {
        // delete the % of the vertices in the solution at random
        List<Integer> unfeasibleSolution = new ArrayList<>(solution);
        int verticesToRemove = (int) (REMOVE_VERTICES_PERCENTAGE * solution.size());
        for (int i = 0; i < verticesToRemove; i++) {
            // get random vertex in length of unfeasibleSolution
            Integer randomVertex = unfeasibleSolution.get((int) (Math.random() * unfeasibleSolution.size()));
            // remove random vertex
            unfeasibleSolution.remove(randomVertex);
        }

        return new HashSet<>(unfeasibleSolution);
    }

    public static void main(String args[]) {
        // Instance new graph
        Graph graph = new Graph("rnd_graph_5000_50_1.txt");

        // Get initial solution
        Set<Integer> initialSolution = InitialSolution.greedyInsertion(graph);

        System.out.println("Initial solution: " + initialSolution + ", with size: " + initialSolution.size());

        // Get local improvement
        Set<Integer> solution = new LocalImprovement(graph).exchange(initialSolution);

        // Print the local improvement
        System.out.println("Local improvement: " + solution + ", with size: " + solution.size());

        // Start timer
        long startTime = System.currentTimeMillis();

        // Destroy the solution
        Set<Integer> unfeasibleSolution = new Destruction().randomDestruction(solution, 0.2);

        // Stop timer
        long stopTime = System.currentTimeMillis();

        // Print the unfeasible solution
        System.out.println("Unfeasible solution: " + unfeasibleSolution + ", with size: " + unfeasibleSolution.size()
                + " in " + (stopTime - startTime) / 1000.0 + " s");

    }
}
