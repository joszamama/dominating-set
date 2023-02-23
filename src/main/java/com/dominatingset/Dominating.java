package com.dominatingset;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm.VertexCover;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Dominating {
    public static void main(String[] args) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Add some vertices to the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        // Add some edges to the graph
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");

        // Compute the minimum dominating set of the graph
        RecursiveExactVCImpl<String, DefaultEdge> algorithm = new RecursiveExactVCImpl<>(graph);
        VertexCover<String> vertexCover = algorithm.getVertexCover();

        // Print the minimum dominating set
        System.out.println("Minimum dominating set:");
        for (String vertex : vertexCover) {
            System.out.println(vertex);
        }
    }
}
