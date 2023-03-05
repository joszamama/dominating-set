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

    public static void main(String[] args) {
        // set initial time for measuring runtime
        long readStartTime = System.nanoTime();

        // create graph from file
        Graph graph = new Graph("astro-ph.txt");

        // set final time for measuring runtime
        long readEndTime = System.nanoTime();

        // print runtime in seconds
        System.out.println("Read runtime: " + (readEndTime - readStartTime) / 1000000000.0 + "s");

        // pirnt graph information
        System.out.println("Number of vertices: " + graph.getNumberOfVertices());
        System.out.println("Number of leaf vertices: " + graph.getLeafVertices().size());
        System.out.println("Number of support vertices: " + graph.getSupportVertices().size());
        System.out.println("Number of best vertices: " + graph.getBestVertices().size());

        // set initial time for measuring runtime
        long startTime = System.nanoTime();

        // generate initial solution
        Set<Integer> initialSolution = greedyInsertion(graph);

        // set final time for measuring runtime
        long endTime = System.nanoTime();

        // print runtime in seconds
        System.out.println("Runtime: " + (endTime - startTime) / 1000000000.0 + "s");
        System.out.println("Initial solution: " + initialSolution);
    }

}
