package com.dominatingset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.generate.GnpRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphGenerator {

    public static Graph<String, DefaultEdge> generateGraph(int n, double p) {
        Supplier<String> vertexSupplier = new Supplier<String>() {
            private int id = 0;

            @Override
            public String get() {
                return "v" + id++;
            }
        };
        Supplier<DefaultEdge> edgeSupplier = new Supplier<DefaultEdge>() {
            @Override
            public DefaultEdge get() {
                return new DefaultEdge();
            }
        };
        GnpRandomGraphGenerator<String, DefaultEdge> generator = new GnpRandomGraphGenerator<>(n, p);
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(vertexSupplier, edgeSupplier, false);
        generator.generateGraph(graph);
        return graph;
    }

    public static void graphToFile(Graph<String, DefaultEdge> graph, String filename) {
        try {
            FileWriter writer = new FileWriter("./src/main/java/com/dominatingset/graphs/" + filename);
            // Write the vertices
            for (String vertex : graph.vertexSet()) {
                writer.write(vertex + "\n");
            }
            // Write the edges
            writer.write("--------------" + "\n");
            for (DefaultEdge edge : graph.edgeSet()) {
                String source = graph.getEdgeSource(edge);
                String target = graph.getEdgeTarget(edge);
                writer.write("{" + source + " : " + target + "}" + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Graph<String, DefaultEdge> fileToGraph(String filename) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("./src/main/java/com/dominatingset/graphs/" + filename));
            String line = reader.readLine();
            while (line != null && !line.equals("--------------")) {
                graph.addVertex(line);
                line = reader.readLine();
            }
            line = reader.readLine(); // skip "--------------" line
            while (line != null) {
                String[] parts = line.replaceAll("[{} ]", "").split(":");
                String source = parts[0];
                String target = parts[1];
                graph.addEdge(source, target);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static void main(String[] args) {
        Graph<String, DefaultEdge> graph = generateGraph(10, 0.5);
        graphToFile(graph, "test.txt");
        Graph<String, DefaultEdge> graph2 = fileToGraph("test.txt");
        System.out.println(graph2);
    }
}
