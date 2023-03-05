package com.dominatingset.functionalities.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dominatingset.functionalities.plotters.AdjacencyMatrixPlotter;

public class AdjacencyMatrixReader {

    public static boolean[][] readMatrix(String filename) {
        boolean[][] adjMatrix = null;
        try (Scanner scanner = new Scanner(new File("./src/main/java/com/dominatingset/files/" + filename))) {
            // Read the first line to determine the size of the matrix
            String firstLine = scanner.nextLine();
            int nodes = firstLine.split(" ").length;
            adjMatrix = new boolean[nodes][nodes];

            // Populate the matrix with values from the file
            adjMatrix[0] = parseLine(firstLine);
            for (int i = 1; i < nodes; i++) {
                String line = scanner.nextLine();
                adjMatrix[i] = parseLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return adjMatrix;
    }

    private static boolean[] parseLine(String line) {
        String[] parts = line.split(" ");
        boolean[] values = new boolean[parts.length];
        for (int i = 0; i < parts.length; i++) {
            values[i] = parts[i].equals("1");
        }
        return values;
    }

    public static void main(String[] args) {
        // Read the adjacency matrix from a file
        boolean[][] adjMatrix = readMatrix("small.txt");

        // Plot the adjacency matrix
        AdjacencyMatrixPlotter.plotMatrix(adjMatrix);
    }
}
