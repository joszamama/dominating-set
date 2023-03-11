package com.dominatingset.components;

import java.util.HashSet;
import java.util.Set;

import com.dominatingset.Graph;

public class InitialSolution {

    public static Set<Integer> greedyInsertion(Graph graph) {
        Set<Integer> initialSolution = graph.getSupportVertices();
        Set<Integer> bestVertices = new HashSet<>(graph.getBestVertices());

        bestVertices.removeAll(initialSolution);
        bestVertices.removeAll(graph.getLeafVertices());

        // add vertices to initial solution in order of their number of neighbours
        for (Integer vertex : graph.getBestVertices()) {
            if (!graph.isDominatingSet(initialSolution)) {
                // if vertex is not a leaf vertex, not already in initial solution and the
                // current initial solution is not a dominating set, add vertex to initial
                // solution
                initialSolution.add(vertex);
            }
        }

        // remove redundant vertices from initial solution
        return graph.removeRedundancy(initialSolution);
    }

    public static Set<Integer> greedyDeletion(Graph graph) {
        Set<Integer> initialSolution = new HashSet<>(graph.getAllVertices());
        Set<Integer> worstVertices = new HashSet<>(graph.getWorstVertices());

        initialSolution.removeAll(graph.getLeafVertices());
        worstVertices.removeAll(graph.getSupportVertices());

        // remove vertices from initial solution in order of their number of neighbours

        for (Integer vertex : worstVertices) {
            int removedVertex = 0;
            if (graph.isDominatingSet(initialSolution)) {
                // if vertex is not a leaf vertex, not already in initial solution and the
                // current initial solution is not a dominating set, add vertex to initial
                // solution
                initialSolution.remove(vertex);
                removedVertex = vertex;
            } else {
                initialSolution.add(removedVertex);
            }

        }

        // remove redundant vertices from initial solution
        return graph.removeRedundancy(initialSolution);
    }

}