package com.dominatingset.components;

import java.util.HashSet;
import java.util.List;
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
        System.out.println("Initial insertion solution: " + initialSolution);
        return initialSolution;
    }

    // function for finding initial solution using greedy deletion method
    public static Set<Integer> greedyDeletion(Graph graph) {
        List<Integer> initialSolution = graph.getAllVertices();
        initialSolution.removeAll(graph.getLeafVertices());
        Integer lastRemoved = null;

        // remove vertices from initial solution in order of their number of neighbours
        for (Integer vertex : graph.getWorstVertices()) {
            if (!graph.getSupportVertices().contains(vertex) || AdjacencyListFinder.isDominatingSet(graph, new HashSet<>(initialSolution))) {
                // if vertex is not a support vertex or the current initial solution is a dominating set, remove vertex from initial solution
                initialSolution.remove(vertex);
                lastRemoved = vertex;
            }
            initialSolution.add(lastRemoved);
        }
        System.out.println("Initial deletion solution: " + initialSolution);
        return new HashSet<>(initialSolution);
    }
}
