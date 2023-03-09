package com.dominatingset;

import java.util.HashSet;
import java.util.Set;

public class Sandbox {

    public static boolean checkSolutionIsDS(Graph graph, Set<Integer> solution) {
        return graph.isDominatingSet(solution);
    }

    public static void main(String[] args) {
        // create graph from file
        Graph graph = new Graph("rnd_graph_5000_50_4.txt");
        // provide solution
        Set<Integer> check = new HashSet<>();
        int[] solution = { 512, 2416, 995, 1539, 2403, 3717, 3607, 2441, 4795, 668, 894, 1775 };
        for (int i = 0; i < solution.length; i++) {
            check.add(solution[i]);
        }

        // check if solution is dominating set
        System.out.println("Solution is: " + checkSolutionIsDS(graph, check));

    }
}
