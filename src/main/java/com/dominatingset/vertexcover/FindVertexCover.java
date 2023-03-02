package com.dominatingset.vertexcover;

import java.util.List;
import java.util.Arrays;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm.VertexCover;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;

import com.dominatingset.extra.generators.GraphGenerator;
import com.dominatingset.extra.plotters.GraphPlotter;

public class FindVertexCover {

    public static List<Object> exactDominatingSet(Graph<Integer, DefaultEdge> graph) {
        // Compute the vertex cover of the graph using the exact algorithm
        long exactStartTime = System.nanoTime();
        RecursiveExactVCImpl<Integer, DefaultEdge> exactAlgorithm = new RecursiveExactVCImpl<>(graph);
        VertexCover<Integer> vertexCover = exactAlgorithm.getVertexCover();
        long exactEndTime = System.nanoTime();
        double exactTimeElapsed = (exactEndTime - exactStartTime) / 1_000_000_000.0;
        long exactMemoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);

        // Print the results
        System.out.println("------- Exact vertex cover -------");
        System.out.println("Exact solution: " + exactAlgorithm.getVertexCover());
        System.out.println("Exact solution dominant number: " + exactAlgorithm.getVertexCover().size());
        System.out.println("Exact time elapsed: " + exactTimeElapsed + " seconds");
        System.out.println("Exact memory used: " + exactMemoryUsed + " MB");

        return Arrays.asList(vertexCover, exactTimeElapsed, exactMemoryUsed);
    }

    public static List<Object> aproxDominatingSet(Graph<Integer, DefaultEdge> graph) {
        // Compute the vertex cover of the graph using the greedy algorithm
        long approximateStartTime = System.nanoTime();
        GreedyVCImpl<Integer, DefaultEdge> approximateAlgorithm = new GreedyVCImpl<>(graph);
        VertexCover<Integer> vertexCover = approximateAlgorithm.getVertexCover();
        long approximateEndTime = System.nanoTime();
        double approximateTimeElapsed = (approximateEndTime - approximateStartTime) / 1_000_000_000.0;
        long approximateMemoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                / (1024 * 1024);

        // Print the results
        System.out.println("------- Approximate vertex cover -------");
        System.out.println("Approximate solution: " + vertexCover);
        System.out.println("Approximate solution dominant number: " + approximateAlgorithm.getVertexCover().size());
        System.out.println("Approximate time elapsed: " + approximateTimeElapsed + " seconds");
        System.out.println("Approximate memory used: " + approximateMemoryUsed + " MB");

        return Arrays.asList(vertexCover, approximateTimeElapsed, approximateMemoryUsed);
    }

    public static void compareWithLibs(Graph<Integer, DefaultEdge> graph) {
        List<Object> exact = exactDominatingSet(graph);
        List<Object> approximate = aproxDominatingSet(graph);

        System.out.println("------- Comparison -------");

        // Compare time and space differences
        double timeDifference = (double) exact.get(1) - (double) approximate.get(1);
        long memoryDifference = (long) exact.get(2) - (long) approximate.get(2);
        System.out.println("Time difference: " + timeDifference + " seconds");
        System.out.println("Memory difference: " + memoryDifference + " MB");
    }

    public static void main(String[] args) {
        // Create an undirected graph
        Graph<Integer, DefaultEdge> graph = GraphGenerator.generateGraph(4, 1);

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph);

        System.out.println("--------------------");
        System.out.println("Compute the Minimum Dominating Set of a Graph");
        System.out.println("--------------------");

        // Find the dominating set
        compareWithLibs(graph);
    }
}
