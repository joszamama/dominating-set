package com.dominatingset;

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
}
