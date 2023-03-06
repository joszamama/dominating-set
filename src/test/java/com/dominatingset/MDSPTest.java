package com.dominatingset;

import java.util.HashSet;
import java.util.Set;

import com.dominatingset.components.Destruction;
import com.dominatingset.components.InitialSolution;

public class MDSPTest {

    public static void TestRuntime() {
        System.out.println("---------------------- Read runtime ----------------------");
        // set initial time for measuring runtime
        long readStartTime = System.nanoTime();

        // create graph from file
        Graph graph = new Graph("astro-ph.txt");

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

        System.out.println("---------------------- InitialSolution runtime ----------------------");

        // set initial time for measuring runtime
        long ISStartTime = System.nanoTime();

        // generate initial solution
        Set<Integer> initialSolution = InitialSolution.greedyInsertion(graph);

        // set final time for measuring runtime
        long ISEndTime = System.nanoTime();

        // print runtime in seconds
        System.out.println("Initial solution runtime: " + (ISEndTime - ISStartTime) / 1000000000.0 + "s");
        System.out.println("Initial solution size: " + initialSolution.size());

        System.out.println("---------------------- Destruction1 runtime ----------------------");

        // copy initial solution
        Set<Integer> initialSolutionCopy1 = new HashSet<>(initialSolution);

        // set initial time for measuring runtime
        long DEStartTime1 = System.nanoTime();

        // destroy initial solution method randomDestruction
        Set<Integer> destroyedSolution1 = Destruction.randomDestruction(initialSolutionCopy1, 0.5);

        // set final time for measuring runtime
        long DEEndTime1 = System.nanoTime();

        // print runtime in seconds
        System.out.println("Destruction runtime: " + (DEEndTime1 - DEStartTime1) / 1000000000.0 + "s");
        System.out.println("Destroyed solution size: " + destroyedSolution1.size());

        System.out.println("---------------------- Destruction2 runtime ----------------------");

        // copy initial solution
        Set<Integer> initialSolutionCopy2 = new HashSet<>(initialSolution);

        // set initial time for measuring runtime
        long DEStartTime2 = System.nanoTime();

        // destroy initial solution method randomSlicing
        Set<Integer> destroyedSolution2 = Destruction.randomSlicing(initialSolutionCopy2, 0.5);

        // set final time for measuring runtime
        long DEEndTime2 = System.nanoTime();

        // print runtime in seconds
        System.out.println("Destruction runtime: " + (DEEndTime2 - DEStartTime2) / 1000000000.0 + "s");
        System.out.println("Destroyed solution size: " + destroyedSolution2.size());

        System.out.println("---------------------- Destruction3 runtime ----------------------");

        // copy initial solution
        Set<Integer> initialSolutionCopy3 = new HashSet<>(initialSolution);

        // set initial time for measuring runtime
        long DEStartTime3 = System.nanoTime();

        // destroy initial solution method greedyDestruction
        Set<Integer> destroyedSolution3 = Destruction.greedyDestruction(initialSolutionCopy3, 0.5);

        // set final time for measuring runtime
        long DEEndTime3 = System.nanoTime();

        // print runtime in seconds
        System.out.println("Destruction runtime: " + (DEEndTime3 - DEStartTime3) / 1000000000.0 + "s");
        System.out.println("Destroyed solution size: " + destroyedSolution3.size());
    }

    public static void main(String[] args) {
        TestRuntime();
    }

}
