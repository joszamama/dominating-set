package com.dominatingset;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dominatingset.components.functionalities.Finder;
import com.dominatingset.components.functionalities.Reader;
import com.dominatingset.components.functionalities.Transformer;

public class Graph {

    private boolean[][] adjacencyMatrix;
    private List<Integer>[] adjacencyList;

    private Set<Integer> allVertices;
    private Set<Integer> leafVertices;
    private Set<Integer> supportVertices;

    private List<Integer> bestVertices;
    private List<Integer> worstVertices;

    public Graph(String file) {
        System.out.println("Creating graph...");

        adjacencyMatrix = Reader.readMatrix(file);
        adjacencyList = Transformer.matrixToAdjacencyList(adjacencyMatrix);

        allVertices = new HashSet<>();
        for (int i = 0; i < adjacencyList.length; i++) {
            allVertices.add(i);
        }

        leafVertices = Finder.findLeafVertices(adjacencyList);
        supportVertices = Finder.findSupportVertices(adjacencyList, leafVertices);

        bestVertices = Finder.findKVertices(adjacencyList, adjacencyList.length, "TOP");
        worstVertices = Finder.findKVertices(adjacencyList, adjacencyList.length, "BOTTOM");

        System.out.println("Graph created");
    }

    public Graph(Graph graph) {
        this.adjacencyMatrix = graph.getAdjacencyMatrix();
        this.adjacencyList = graph.getAdjacencyList();
        this.allVertices = graph.getAllVertices();
        this.leafVertices = graph.getLeafVertices();
        this.supportVertices = graph.getSupportVertices();
        this.bestVertices = graph.getBestVertices();
        this.worstVertices = graph.getWorstVertices();
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public List<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    public Set<Integer> getAllVertices() {
        return allVertices;
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

    public List<Integer> getWorstVertices() {
        return worstVertices;
    }

    public List<Integer> getNeighbors(Integer vertex) {
        return adjacencyList[vertex];
    }

    public boolean isNeighbor(Integer vertex1, Integer vertex2) {
        return adjacencyList[vertex1].contains(vertex2);
    }

    public boolean isDominatingSet(Set<Integer> dominatingSet) {
        Set<Integer> domination = new HashSet<>();
        for (Integer vertex : dominatingSet) {
            domination.add(vertex);
            for (Integer neighbor : adjacencyList[vertex]) {
                domination.add(neighbor);
            }
        }
        return domination.size() == adjacencyList.length;
    }

    public Set<Integer> findDominatedVertices(Set<Integer> solution) {
        Set<Integer> dominated = new HashSet<>();
        for (Integer vertex : solution) {
            dominated.add(vertex);
            for (Integer neighbor : adjacencyList[vertex]) {
                dominated.add(neighbor);
            }
        }
        return dominated;
    }

    public Set<Integer> findUndominated(Set<Integer> solution) {
        Set<Integer> undominated = new HashSet<>(allVertices);
        undominated.removeAll(findDominatedVertices(solution));
        return undominated;
    }

    public Set<Integer> removeRedundancy(Set<Integer> dominatingSet) {
        // remove redundant vertices from initial solution
        Set<Integer> dominatedVertices = new HashSet<>(dominatingSet);
        Set<Integer> checkedVertices = new HashSet<>();

        // iterate over all vertices in initial solution
        for (Integer vertex : dominatingSet) {
            // if vertex has already been checked, continue
            if (checkedVertices.contains(vertex)) {
                continue;
            }
            // if vertex is dominated by other vertices in initial solution, remove it
            boolean isRedundant = true;
            // iterate over all neighbours of vertex
            for (Integer neighbor : getNeighbors(vertex)) {
                // if neighbour is not in initial solution, vertex is not redundant
                if (!dominatedVertices.contains(neighbor)) {
                    isRedundant = false;
                    break;
                }
            }
            // if vertex is redundant, remove it from initial solution and add its neighbors
            if (isRedundant) {
                dominatedVertices.remove(vertex);
                // add neighbours of vertex to checked vertices
                for (Integer neighbor : getNeighbors(vertex)) {
                    checkedVertices.add(neighbor);
                }
            }
        }
        // return initial solution without redundant vertices
        return dominatedVertices;
    }

    public boolean v1IsDominatedByV2(Integer v1, Integer v2) {
        return adjacencyList[v1].contains(v2);
    }

    public boolean v1DominatesV2(Integer v1, Integer v2) {
        return adjacencyList[v2].contains(v1);
    }

    public Integer getNumberOfVertices() {
        return adjacencyList.length;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("rnd_graph_5000_50_1.txt");

        System.out.println("Number of vertices: " + graph.getNumberOfVertices());
        System.out.println("Leaf vertices number: " + graph.getLeafVertices().size());
        System.out.println("Support vertices number: " + graph.getSupportVertices().size());
        System.out.println("Best vertices number: " + graph.getBestVertices().size());
    }
}
