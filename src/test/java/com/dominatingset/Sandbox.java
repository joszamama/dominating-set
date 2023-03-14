package com.dominatingset;

import java.util.HashSet;
import java.util.Set;

public class Sandbox {
    
    public static void checkDominance(Graph graph, Set<Integer> ds) {
        System.out.println(graph.isDominatingSet(ds));
    }

    public static void main(String[] args) {
        int[] solution = {4592, 2705, 609, 2888, 2330, 4058, 1114, 523, 1691, 365, 4463, 1151};
        Set<Integer> ds = new HashSet<Integer>();
        for (int i = 0; i < solution.length; i++) {
            ds.add(solution[i]);
        }
        Graph graph = new Graph("rnd_graph_5000_50_1.txt");
        checkDominance(graph, ds);
    }
}