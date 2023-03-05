package com.dominatingset.functionalities.transformers;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.dominatingset.functionalities.generators.AdjacencyMatrixGenerator;
import com.dominatingset.functionalities.plotters.GraphPlotter;

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

    // function for transforming an adjacency matrix into a adjacency list
    @SuppressWarnings("unchecked")
    public static List<Integer>[] matrixToAdjacencyList(boolean[][] adjMatrix) {
        // create a new adjacency list
        List<Integer>[] adjList = new ArrayList[adjMatrix.length];

        // add all vertices to the adjacency list and add edges as you go
        for (int i = 0; i < adjMatrix.length; i++) {
            adjList[i] = new ArrayList<>();

            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j]) {
                    adjList[i].add(j);
                }
            }
        }
        return adjList;
    }

    public static void main(String[] args) {
        // create a new adjacency matrix
        boolean[][] adjMatrix = AdjacencyMatrixGenerator.generateMatrix(10, 0.5);

        // transform the adjacency matrix into a graph
        Graph<Integer, DefaultEdge> graph = matrixToGraph(adjMatrix);

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph);

        // transform the adjacency matrix into a adjacency list
        List<Integer>[] adjList = matrixToAdjacencyList(adjMatrix);

        // print the adjacency list
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}
