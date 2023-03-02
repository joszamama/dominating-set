package com.dominatingset.functionalities.generators;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.dominatingset.functionalities.plotters.GraphPlotter;
import com.dominatingset.functionalities.readers.GraphReader;
import com.dominatingset.functionalities.writers.GraphWriter;

public class GraphGenerator {

    public static Graph<Integer, DefaultEdge> generateGraph(int nodes, double probability) {
        // Create an undirected graph
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Add vertices to the graph
        for (int i = 1; i <= nodes; i++) {
            graph.addVertex(i);
        }

        // Add edges to the graph
        for (int i = 1; i <= nodes; i++) {
            for (int j = i + 1; j <= nodes; j++) {
                if (Math.random() < probability) {
                    graph.addEdge(i, j);
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        // Generate a graph
        Graph<Integer, DefaultEdge> graph = generateGraph(50, 0.05);

        // Write the graph to a file
        GraphWriter.writeGraph(graph, "graph.txt");

        // Read the graph from a file
        Graph<Integer, DefaultEdge> graph2 = GraphReader.readGraph("graph.txt");

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph2);
    }
}
