package com.dominatingset;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Hello world!
 *
 */
public class Cheatsheet {
    public static void main(String[] args) {
        // Create a graph
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Add some vertices to the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Add some edges to the graph
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "A");
        graph.addEdge("A", "C");

        // Print the vertices in the graph
        System.out.println("Vertices:");
        for (String vertex : graph.vertexSet()) {
            System.out.println(vertex);
        }

        // Print the edges in the graph
        System.out.println("\nEdges:");
        for (DefaultEdge edge : graph.edgeSet()) {
            System.out.println(graph.getEdgeSource(edge) + " -> " + graph.getEdgeTarget(edge));
        }

        // Find the vertex with the most edges
        String maxDegreeVertex = null;
        int maxDegree = 0;
        for (String vertex : graph.vertexSet()) {
            int degree = graph.degreeOf(vertex);
            if (degree > maxDegree) {
                maxDegree = degree;
                maxDegreeVertex = vertex;
            }
        }
        System.out.println("\nVertex with most edges: " + maxDegreeVertex + " (" + maxDegree + " edges)");
    }
}
