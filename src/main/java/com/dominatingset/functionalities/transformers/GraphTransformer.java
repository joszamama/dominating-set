package com.dominatingset.functionalities.transformers;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.dominatingset.functionalities.generators.GraphGenerator;
import com.dominatingset.functionalities.plotters.AdjacencyMatrixPlotter;

public class GraphTransformer {

    // function for transforming a graph into an adjacency matrix
    public static boolean[][] graphToMatrix(Graph<Integer, DefaultEdge> graph) {
        // create a new adjacency matrix
        boolean[][] adjMatrix = new boolean[graph.vertexSet().size()][graph.vertexSet().size()];

        // populate the adjacency matrix with edges
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = i + 1; j < adjMatrix[i].length; j++) {
                if (graph.containsEdge(i, j)) {
                    adjMatrix[i][j] = true;
                    adjMatrix[j][i] = true;
                }
            }
        }
        return adjMatrix;
    }

    public static void main(String[] args) {
        // create a new graph
        Graph<Integer, DefaultEdge> graph = GraphGenerator.generateGraph(10, 0.5);

        // transform the graph into an adjacency matrix
        boolean[][] adjMatrix = graphToMatrix(graph);

        // Plot the adjacency matrix
        AdjacencyMatrixPlotter.plotMatrix(adjMatrix);
    }
}
