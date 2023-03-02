package com.dominatingset.extra.generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.dominatingset.extra.plotters.AdjacencyMatrixPlotter;

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

    public static void writeMatrix(boolean[][] adjMatrix, String filename) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("./src/main/java/com/dominatingset/adjmatrix/" + filename))) {
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

    public static boolean[][] readMatrix(String filename) {
        boolean[][] adjMatrix = null;
        try (Scanner scanner = new Scanner(new File("./src/main/java/com/dominatingset/adjmatrix/" + filename))) {
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
        // Generate a graph
        boolean[][] adjMatrix = generateMatrix(50, 0.25);

        // Write the graph to a file
        writeMatrix(adjMatrix, "adjmatrix.txt");

        // Read the graph from a file
        boolean[][] adjMatrix2 = readMatrix("adjmatrix.txt");

        // Plot the adjacency matrix
        AdjacencyMatrixPlotter.plotMatrix(adjMatrix2);
    }
}
