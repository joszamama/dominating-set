package com.dominatingset.functionalities.plotters;

import com.dominatingset.functionalities.generators.AdjacencyMatrixGenerator;

public class AdjacencyMatrixPlotter {

    // function for plotting an adjacency matrix
    public static void plotMatrix(boolean[][] adjMatrix) {
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print(adjMatrix[i][0] ? 1 : 0);
            for (int j = 1; j < adjMatrix[i].length; j++) {
                System.out.print(" ");
                System.out.print(adjMatrix[i][j] ? 1 : 0);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // create a new adjacency matrix
        boolean[][] adjMatrix = AdjacencyMatrixGenerator.generateMatrix(10, 0.5);

        // plot the adjacency matrix
        plotMatrix(adjMatrix);

    }
}
