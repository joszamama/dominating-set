package com.dominatingset.components;

import java.util.Set;

import com.dominatingset.Graph;
import com.dominatingset.functionalities.finders.AdjacencyListFinder;

public class InitialSolution {

    // function for finding initial solution using greedy insertion method
    public static Set<Integer> greedyInsertion(Graph graph) {
        Set<Integer> initialSolution = graph.getSupportVertices();

        // add vertices to initial solution in order of their number of neighbours
        for (Integer vertex : graph.getBestVertices()) {
            if (!graph.getLeafVertices().contains(vertex) && !initialSolution.contains(vertex) && !AdjacencyListFinder.isDominatingSet(graph, initialSolution)) {
                // if vertex is not a leaf vertex, not already in initial solution and the current initial solution is not a dominating set, add vertex to initial solution
                initialSolution.add(vertex);
            }
        }
        return initialSolution;
    }
}
