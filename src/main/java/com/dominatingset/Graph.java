package com.dominatingset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dominatingset.components.functionalities.Finder;
import com.dominatingset.components.functionalities.Reader;
import com.dominatingset.components.functionalities.Transformer;

public class Graph {

    private List<Integer>[] adjacencyList;

    private Set<Integer> allVertices = new HashSet<>();
    private Set<Integer> leafVertices = new HashSet<>();
    private Set<Integer> supportVertices = new HashSet<>();

    Map<Integer, List<Integer>> sortedVertices = new HashMap<>();

    public Graph(String file) {
        System.out.println("Creating graph...");

        boolean[][] adjacencyMatrix = Reader.readMatrix(file);
        this.adjacencyList = Transformer.matrixToAdjacencyList(adjacencyMatrix);

        for (int i = 0; i < adjacencyList.length; i++) {
            this.allVertices.add(i);
        }

        this.leafVertices = Finder.findLeafVertices(adjacencyList);
        this.supportVertices = Finder.findSupportVertices(adjacencyList, leafVertices);

        for (int i = 0; i < this.adjacencyList.length; i++) {
            int numNeighbors = this.adjacencyList[i].size();
            if (numNeighbors == 0 || numNeighbors == 1) {
                continue;
            }
            if (!this.sortedVertices.containsKey(numNeighbors)) {
                this.sortedVertices.put(numNeighbors, new ArrayList<>());
            }
            this.sortedVertices.get(numNeighbors).add(i);
        }

        List<Integer> sortedKeys = new ArrayList<>(this.sortedVertices.keySet());
        Collections.sort(sortedKeys, Collections.reverseOrder());

        LinkedHashMap<Integer, List<Integer>> sortedMap = new LinkedHashMap<>();
        for (Integer key : sortedKeys) {
            sortedMap.put(key, this.sortedVertices.get(key));
        }

        this.sortedVertices = sortedMap;

        System.out.println("Graph created");
    }

    public Graph(Graph graph) {
        this.adjacencyList = graph.getAdjacencyList();
        this.allVertices = graph.getAllVertices();
        this.leafVertices = graph.getLeafVertices();
        this.supportVertices = graph.getSupportVertices();
        this.sortedVertices = graph.getSortedVertices();
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

    public Map<Integer, List<Integer>> getSortedVertices() {
        return sortedVertices;
    }

    public List<Integer> getNeighbors(Integer vertex) {
        return adjacencyList[vertex];
    }

    public boolean isNeighbor(Integer vertex1, Integer vertex2) {
        return adjacencyList[vertex1].contains(vertex2);
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

    public boolean isDominatingSet(Set<Integer> dominatingSet) {
        Set<Integer> domination = findDominatedVertices(dominatingSet);
        return domination.size() == adjacencyList.length;
    }

    public Set<Integer> findUndominated(Set<Integer> solution) {
        Set<Integer> undominated = new HashSet<>(allVertices);
        undominated.removeAll(findDominatedVertices(solution));
        return undominated;
    }

    public Set<Integer> removeRedundantVertices(Set<Integer> solution) {
        Set<Integer> nonRedundantVertices = new HashSet<>(solution);

        for (int vertex : solution) {
            List<Integer> dominatedVerticesByVertex = getNeighbors(vertex);
            dominatedVerticesByVertex.add(vertex);

            Set<Integer> dominatedVerticesBySet = new HashSet<>();
            for (int otherVertex : solution) {
                if (vertex != otherVertex) {
                    dominatedVerticesBySet.add(otherVertex);
                    dominatedVerticesBySet.addAll(getNeighbors(otherVertex));
                }
            }
            if (dominatedVerticesBySet.containsAll(dominatedVerticesByVertex)) {
                nonRedundantVertices.remove(vertex);
            }
        }

        return nonRedundantVertices;
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
        System.out.println("Sorted vertices: " + graph.sortedVertices);
    }
}
