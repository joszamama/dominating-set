package com.dominatingset.functionalities.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import com.dominatingset.functionalities.generators.GraphGenerator;

public class GraphWriter {

    public static void writeGraph(Graph<Integer, DefaultEdge> graph, String filename) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("./src/main/java/com/dominatingset/files/graphs/" + filename))) {
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

    public static void main(String[] args) {
        // create a new graph
        Graph<Integer, DefaultEdge> graph = GraphGenerator.generateGraph(10, 0.5);

        // write the graph to a file
        writeGraph(graph, "graph.txt");
    }

}
