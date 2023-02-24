package com.dominatingset;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Dominating {

    public static void main(String[] args) {
        // Create an undirected graph
        Graph<Integer, DefaultEdge> graph = GraphGenerator.generateGraph(4, 1);

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph);

        System.out.println("--------------------");
        System.out.println("Compute the Minimum Dominating Set of a Graph");
        System.out.println("--------------------");

        // Find the dominating set
        Set<Integer> dominatingSet = null;

        // Print the dominating set
        System.out.println("The dominating set is: " + dominatingSet);

    }
}
