package com.dominatingset.components;

import java.util.HashSet;
import java.util.Set;

import com.dominatingset.Graph;
import com.dominatingset.functionalities.finders.AdjacencyListFinder;

public class InitialSolution {

    public static Set<Integer> greedyInsertion(Graph graph) {
        Set<Integer> initialSolution = graph.getSupportVertices();

        // add vertices to initial solution in order of their number of neighbours
        for (Integer vertex : graph.getBestVertices()) {
            if (!graph.getLeafVertices().contains(vertex) && !initialSolution.contains(vertex)
                    && !AdjacencyListFinder.isDominatingSet(graph, initialSolution)) {
                // if vertex is not a leaf vertex, not already in initial solution and the
                // current initial solution is not a dominating set, add vertex to initial
                // solution
                initialSolution.add(vertex);
            }
        }

        // remove redundant vertices from initial solution
        Set<Integer> dominatedVertices = new HashSet<>(initialSolution);
        Set<Integer> checkedVertices = new HashSet<>();

        // iterate over all vertices in initial solution
        for (Integer vertex : initialSolution) {
            // if vertex has already been checked, continue
            if (checkedVertices.contains(vertex)) {
                continue;
            }
            // if vertex is dominated by other vertices in initial solution, remove it
            boolean isRedundant = true;
            // iterate over all neighbours of vertex
            for (Integer neighbor : graph.getNeighbors(vertex)) {
                // if neighbour is not in initial solution, vertex is not redundant
                if (!dominatedVertices.contains(neighbor)) {
                    isRedundant = false;
                    break;
                }
            }
            // if vertex is redundant, remove it from initial solution and add its neighbors
            if (isRedundant) {
                dominatedVertices.remove(vertex);
                // add neighbours of vertex to checked vertices
                for (Integer neighbor : graph.getNeighbors(vertex)) {
                    checkedVertices.add(neighbor);
                }
            }
        }
        // return initial solution without redundant vertices
        return dominatedVertices;
    }

}