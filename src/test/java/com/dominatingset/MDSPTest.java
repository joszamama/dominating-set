package com.dominatingset;

import java.util.Set;

import com.dominatingset.components.InitialSolution;

public class MDSPTest {

    public static void TestRuntime(String file) {
        System.out.println("---------------------- Read runtime ----------------------");
        // set initial time for measuring runtime
        long readStartTime = System.nanoTime();

        // create graph from file
        Graph graph = new Graph(file);

        // set final time for measuring runtime
        long readEndTime = System.nanoTime();

        // print runtime in seconds
        System.out.println("Read runtime: " + (readEndTime - readStartTime) / 1000000000.0 + "s");

        System.out.println("---------------------- Info runtime ----------------------");

        // print graph information
        System.out.println("Number of vertices: " + graph.getNumberOfVertices());
        System.out.println("Number of leaf vertices: " + graph.getLeafVertices().size());
        System.out.println("Number of support vertices: " + graph.getSupportVertices().size());
        System.out.println("Number of best vertices: " + graph.getBestVertices().size());

        System.out.println("---------------------- GreedyInsertion runtime ----------------------");

        // copy graph
        Graph graph1 = new Graph(graph);

        // set initial time for measuring runtime
        long ISStartTime = System.nanoTime();

        // generate initial solution
        Set<Integer> initialSolution = InitialSolution.greedyInsertion(graph1);

        // set final time for measuring runtime
        long ISEndTime = System.nanoTime();

        // print runtime in seconds
        System.out.println("Initial solution runtime: " + (ISEndTime - ISStartTime) / 1000000000.0 + "s");
        System.out.println("Initial solution size: " + initialSolution.size());

        System.out.println("---------------------- GreedyDeletion runtime ----------------------");

        // copy graph
        Graph graph2 = new Graph(graph);

        // set initial time for measuring runtime
        long ISStartTime2 = System.nanoTime();

        // generate initial solution
        Set<Integer> initialSolution2 = InitialSolution.greedyDeletion(graph2);

        // set final time for measuring runtime
        long ISEndTime2 = System.nanoTime();

        // print runtime in seconds
        System.out.println("Initial solution runtime: " + (ISEndTime2 - ISStartTime2) / 1000000000.0 + "s");
        System.out.println("Initial solution size: " + initialSolution2.size());

    }

    public static void main(String[] args) {
        TestRuntime("power.txt");
    }

}
