package com.dominatingset.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dominatingset.Graph;

public class InitialSolution {

    public static Set<Integer> greedyInsertion(Graph graph) {
        Set<Integer> initialSolution = new HashSet<>(graph.getSupportVertices());
        Map<Integer, List<Integer>> remainingVertices = new HashMap<>(graph.getSortedVertices());

        for (int i = 0; i < graph.getAdjacencyList().length && !graph.isDominatingSet(initialSolution); i++) {
            // get the highest number of neighbors
            int degree = remainingVertices.keySet().iterator().next();

            // get the list of vertex with the highest number of neighbors
            List<Integer> vertices = remainingVertices.get(degree);
            if (vertices.isEmpty()) {
                remainingVertices.remove(degree);
                degree = remainingVertices.keySet().iterator().next();
                continue;
            }

            // get a random vertex from the list
            int vertex = vertices.get((int) (Math.random() * vertices.size()));

            // add the vertex to the initial solution
            initialSolution.add(vertex);

            // remove the vertex from the list of remaining vertices
            remainingVertices.get(degree).remove((Integer) vertex);

        }

        return initialSolution;
    }

    public static void main(String args[]) {
        // Instanciate a graph
        Graph graph = new Graph("rnd_graph_5000_50_1.txt");

        // Start timer
        long startTime = System.currentTimeMillis();

        // Get the initial solution
        Set<Integer> initialSolution = greedyInsertion(graph);

        // Stop timer
        long stopTime = System.currentTimeMillis();

        // Print the initial solution
        System.out.println("Initial solution: " + initialSolution + ", with size: " + initialSolution.size() + " in "
                + (stopTime - startTime) / 1000.0 + " s");
    }

}