package com.dominatingset.functionalities.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.dominatingset.functionalities.plotters.GraphPlotter;

public class GraphReader {

    public static Graph<Integer, DefaultEdge> readGraph(String filename) {
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        try (Scanner scanner = new Scanner(new File("./src/main/java/com/dominatingset/files/graphs/" + filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("-----------------")) {
                    break;
                }
                graph.addVertex(Integer.parseInt(line));
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.substring(1, line.length() - 1);
                String[] vertices = line.split(" : ");
                graph.addEdge(Integer.parseInt(vertices[0].trim()), Integer.parseInt(vertices[1].trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static void main(String[] args) {
        // Read the graph from a file
        Graph<Integer, DefaultEdge> graph = GraphReader.readGraph("graph.txt");

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph);
    }
}
