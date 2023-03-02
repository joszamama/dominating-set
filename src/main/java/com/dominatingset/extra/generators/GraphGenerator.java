package com.dominatingset.extra.generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.dominatingset.extra.plotters.GraphPlotter;

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

    public static void writeGraph(Graph<Integer, DefaultEdge> graph, String filename) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("./src/main/java/com/dominatingset/graphs/" + filename))) {
            for (Integer vertex : graph.vertexSet()) {
                writer.println(vertex);
            }
            writer.println("-----------------");
            for (DefaultEdge edge : graph.edgeSet()) {
                Integer source = graph.getEdgeSource(edge);
                Integer target = graph.getEdgeTarget(edge);
                writer.println("{ " + source + " : " + target + " }");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Graph<Integer, DefaultEdge> readGraph(String filename) {
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        try (Scanner scanner = new Scanner(new File("./src/main/java/com/dominatingset/graphs/" + filename))) {
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
        // Generate a graph
        Graph<Integer, DefaultEdge> graph = generateGraph(50, 0.05);

        // Write the graph to a file
        writeGraph(graph, "graph.txt");

        // Read the graph from a file
        Graph<Integer, DefaultEdge> graph2 = readGraph("graph.txt");

        // Plot the graph
        GraphPlotter.plotUndirectedGraph(graph2);
    }
}
