package com.dominatingset.extra.transformers;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.dominatingset.extra.generators.AdjacencyMatrixGenerator;
import com.dominatingset.extra.plotters.GraphPlotter;

public class AdjacencyMatrixTransformer {

    // function for transforming an adjacency matrix into a graph
    public static Graph<Integer, DefaultEdge> matrixToGraph(boolean[][] adjMatrix) {
        // create a new graph
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // add all vertices to the graph
        for (int i = 0; i < adjMatrix.length; i++) {
            graph.addVertex(i);
        }

        // add all edges to the graph
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = i + 1; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j]) {
                    graph.addEdge(i, j);
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) {

        // create a new adjacency matrix
        boolean[][] adjMatrix = AdjacencyMatrixGenerator.generateMatrix(10, 0.5);

        // transform the adjacency matrix into a graph
        Graph<Integer, DefaultEdge> graph = matrixToGraph(adjMatrix);

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph);
    }
}
