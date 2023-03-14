package com.dominatingset.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.dominatingset.Graph;

public class Reconstruction {

    private Graph graph;

    public Reconstruction(Graph graph) {
        this.graph = graph;
    }

    public Set<Integer> randomReconstruction(Set<Integer> solution) {
        Set<Integer> reconstructedSolution = new HashSet<>(solution);
        while (!graph.isDominatingSet(reconstructedSolution)) {
            // get random vertex from graph.getAllVertices()
            Integer randomVertex = new ArrayList<>(graph.getAllVertices())
                    .get((int) (Math.random() * graph.getAllVertices().size()));
            // add random vertex to solution
            reconstructedSolution.add(randomVertex);
        }
        return reconstructedSolution;
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

        // Destroy the solution
        Set<Integer> unfeasibleSolution = new Destruction().randomDestruction(solution, 0.2);

        // Print the unfeasible solution
        System.out.println("Unfeasible solution: " + unfeasibleSolution + ", with size: " + unfeasibleSolution.size());

        // Start timer
        long startTime = System.currentTimeMillis();

        // Reconstruct the solution with greedy reconstruction
        Set<Integer> reconstructedSolution = new Reconstruction(graph)
                .randomReconstruction(unfeasibleSolution);

        // Stop timer
        long stopTime = System.currentTimeMillis();

        // Print the reconstructed solution
        System.out.println(
                "Reconstructed solution: " + reconstructedSolution + ", with size: "
                        + reconstructedSolution.size()
                        + " in " + (stopTime - startTime) / 1000.0 + " s");
    }
}
