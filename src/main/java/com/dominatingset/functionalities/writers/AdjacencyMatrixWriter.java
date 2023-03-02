package com.dominatingset.functionalities.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.dominatingset.functionalities.generators.AdjacencyMatrixGenerator;

public class AdjacencyMatrixWriter {

    public static void writeMatrix(boolean[][] adjMatrix, String filename) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("./src/main/java/com/dominatingset/files/adjmatrix/" + filename))) {
            for (int i = 0; i < adjMatrix.length; i++) {
                writer.print(adjMatrix[i][0] ? 1 : 0);
                for (int j = 1; j < adjMatrix[i].length; j++) {
                    writer.print(" ");
                    writer.print(adjMatrix[i][j] ? 1 : 0);
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generate a graph
        boolean[][] adjMatrix = AdjacencyMatrixGenerator.generateMatrix(50, 0.25);

        // Write the graph to a file
        writeMatrix(adjMatrix, "adjmatrix.txt");
    }

}
