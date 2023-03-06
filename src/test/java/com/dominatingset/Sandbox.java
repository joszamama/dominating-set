package com.dominatingset;

import java.util.HashSet;
import java.util.Set;

public class Sandbox {

    public static boolean checkSolutionIsDS(Graph graph, Set<Integer> solution) {
        return graph.isDominatingSet(solution);
    }

    public static void main(String[] args) {
        // create graph from file
        Graph graph = new Graph("zachary.txt");
        // provide solution
        Set<Integer> solution = new HashSet<Integer>();
        solution.add(0);
        solution.add(5);
        solution.add(31);
        solution.add(33);

        // check if solution is dominating set
        System.out.println("Solution is: " + checkSolutionIsDS(graph, solution));

    }
}
