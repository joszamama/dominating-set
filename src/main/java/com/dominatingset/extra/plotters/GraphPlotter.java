package com.dominatingset.extra.plotters;

import java.util.Map;

import javax.swing.JFrame;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

public class GraphPlotter {

    public static void plotUndirectedGraph(Graph<Integer, DefaultEdge> graph) {
        // create a graph plotter object
        JGraphXAdapter<Integer, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);

        // set the edge style to be undirected (no arrowhead)
        mxStylesheet stylesheet = graphAdapter.getStylesheet();
        Map<String, Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
        edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);

        // create a graph window
        mxGraphComponent component = new mxGraphComponent(graphAdapter);
        JFrame frame = new JFrame();
        frame.getContentPane().add(component);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        // position vertices nicely within the window
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);

        // execute the layout
        layout.execute(graphAdapter.getDefaultParent());
    }

    public static void plotDirectedGraph(Graph<Integer, DefaultEdge> graph) {
        // create a graph plotter object
        JGraphXAdapter<Integer, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);

        // create a graph window
        mxGraphComponent component = new mxGraphComponent(graphAdapter);
        JFrame frame = new JFrame();
        frame.getContentPane().add(component);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        // position vertices nicely within the window
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);

        // execute the layout
        layout.execute(graphAdapter.getDefaultParent());
    }

    public static void main(String[] args) {
        // create a graph
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // add vertices to the graph
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        // add edges to the graph
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        // plot the graph
        plotUndirectedGraph(graph);
    }
}