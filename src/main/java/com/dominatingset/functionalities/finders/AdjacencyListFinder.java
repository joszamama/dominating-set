package com.dominatingset.functionalities.finders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.dominatingset.Graph;
import com.dominatingset.functionalities.generators.AdjacencyMatrixGenerator;
import com.dominatingset.functionalities.transformers.AdjacencyMatrixTransformer;

public class AdjacencyListFinder {

    // from a given adjacency list, find the leaf Vertices and the support Vertices
    public static List<Set<Integer>> findLeafAndSupportVertices(List<Integer>[] adjList) {
        Set<Integer> supportVertices = new HashSet<>();
        Set<Integer> leafVertices = new HashSet<>();

        for (int i = 0; i < adjList.length; i++) {
            List<Integer> neighbors = adjList[i];

            // If vertex i has only one neighbor, it's a leaf vertex
            if (neighbors.size() == 1) {
                leafVertices.add(i);
            }

            // Iterate over neighbors of vertex i
            for (int neighbor : neighbors) {

                // If neighbor of vertex i has only one neighbor (which is i), it's a support
                // vertex
                if (adjList[neighbor].size() == 1 && adjList[neighbor].contains(i)) {
                    supportVertices.add(neighbor);
                }
            }
        }
        return List.of(leafVertices, supportVertices);
    }

    // from a given adjacency list, find the k best or worst vertices
    public static List<Integer> getKVertices(List<Integer>[] adjList, int k, String type) {
        List<Integer> kVertices = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (v1, v2) -> type.equals("TOP") ? Integer.compare(adjList[v2].size(), adjList[v1].size())
                        : Integer.compare(adjList[v1].size(), adjList[v2].size()));

        for (int i = 0; i < adjList.length; i++) {
            if (adjList[i] == null) {
                throw new IllegalArgumentException("Invalid adjacency list: element at index " + i + " is null.");
            }
            pq.add(i);
        }

        if (k > adjList.length) {
            throw new IllegalArgumentException(
                    "Invalid value for k: k is greater than the number of vertices in the graph.");
        }

        for (int i = 0; i < k; i++) {
            if (!pq.isEmpty()) {
                kVertices.add(pq.poll());
            }
        }

        return kVertices;
    }

    // Check if a given set of vertices is a dominating set
    public static boolean isDominatingSet(Graph graph, Set<Integer> dominatingSet) {
        List<Integer>[] adjList = graph.getAdjacencyList();
        // Create a sorted list of vertices in descending order of degree
        List<Integer> sortedVertices = new ArrayList<Integer>();
        for (int i = 0; i < adjList.length; i++) {
            sortedVertices.add(i);
        }
        sortedVertices.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer v1, Integer v2) {
                return Integer.compare(adjList[v2].size(), adjList[v1].size());
            }
        });

        // Iterate over the sorted list of vertices and check if each vertex is
        // dominated by the given set
        for (int i = 0; i < sortedVertices.size(); i++) {
            int vertex = sortedVertices.get(i);
            if (!dominatingSet.contains(vertex)) {
                boolean isNeighborInSet = false;
                List<Integer> neighbors = adjList[vertex];
                for (int neighbor : neighbors) {
                    if (dominatingSet.contains(neighbor)) {
                        isNeighborInSet = true;
                        break;
                    }
                }
                if (!isNeighborInSet) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean[][] adjMatrix = AdjacencyMatrixGenerator.generateMatrix(10000, 0.5);
        List<Integer>[] adjList = AdjacencyMatrixTransformer.matrixToAdjacencyList(adjMatrix);
        List<Set<Integer>> leafAndSupportVertices = findLeafAndSupportVertices(adjList);

        System.out.println("Leaf vertices: " + leafAndSupportVertices.get(0));
        System.out.println("Support vertices: " + leafAndSupportVertices.get(1));
    }
}
