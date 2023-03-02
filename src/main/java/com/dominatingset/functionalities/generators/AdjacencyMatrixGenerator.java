package com.dominatingset.functionalities.generators;

import com.dominatingset.functionalities.plotters.AdjacencyMatrixPlotter;
import com.dominatingset.functionalities.readers.AdjacencyMatrixReader;
import com.dominatingset.functionalities.writers.AdjacencyMatrixWriter;

public class AdjacencyMatrixGenerator {

    public static boolean[][] generateMatrix(int nodes, double probability) {
        // Create an empty adjacency matrix
        boolean[][] adjMatrix = new boolean[nodes][nodes];

        // Populate the adjacency matrix with edges
        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                if (Math.random() < probability) {
                    adjMatrix[i][j] = true;
                    adjMatrix[j][i] = true;
                }
            }
        }
        return adjMatrix;
    }

    public static void main(String[] args) {
        // Generate a graph
        boolean[][] adjMatrix = generateMatrix(50, 0.25);

        // Write the graph to a file
        AdjacencyMatrixWriter.writeMatrix(adjMatrix, "adjmatrix.txt");

        // Read the graph from a file
        boolean[][] adjMatrix2 = AdjacencyMatrixReader.readMatrix("adjmatrix.txt");

        // Plot the adjacency matrix
        AdjacencyMatrixPlotter.plotMatrix(adjMatrix2);
    }
}
