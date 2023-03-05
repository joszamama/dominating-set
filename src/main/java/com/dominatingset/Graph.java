package com.dominatingset;

import java.util.List;
import java.util.Set;

import com.dominatingset.functionalities.finders.AdjacencyListFinder;
import com.dominatingset.functionalities.readers.AdjacencyMatrixReader;
import com.dominatingset.functionalities.transformers.AdjacencyMatrixTransformer;

public class Graph {

    private boolean[][] adjacencyMatrix;
    private List<Integer>[] adjacencyList;
    private Set<Integer> leafVertices;
    private Set<Integer> supportVertices;
    private List<Integer> bestVertices;

    public Graph(String file) {
        adjacencyMatrix = AdjacencyMatrixReader.readMatrix(file);
        adjacencyList = AdjacencyMatrixTransformer.matrixToAdjacencyList(adjacencyMatrix);

        List<Set<Integer>> leafAndSupportVertices = AdjacencyListFinder.findLeafAndSupportVertices(adjacencyList);

        leafVertices = leafAndSupportVertices.get(0);
        supportVertices = leafAndSupportVertices.get(1);

        bestVertices = AdjacencyListFinder.getKVertices(adjacencyList, 25, "TOP");
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public List<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    public Set<Integer> getLeafVertices() {
        return leafVertices;
    }

    public Set<Integer> getSupportVertices() {
        return supportVertices;
    }

    public List<Integer> getBestVertices() {
        return bestVertices;
    }

    public Integer getNumberOfVertices() {
        return adjacencyList.length;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("adjmatrix.txt");

        System.out.println("Number of vertices: " + graph.getNumberOfVertices());
        System.out.println("Leaf vertices: " + graph.getLeafVertices());
        System.out.println("Support vertices: " + graph.getSupportVertices());
        System.out.println("Best vertices: " + graph.getBestVertices());
    }
}
