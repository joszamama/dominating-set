package com.dominatingset.functionalities.finders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.dominatingset.Graph;

public class AdjacencyListFinder {

    // from a given adjacency list, find the leaf vertices
    public static Set<Integer> findLeafVertices(List<Integer>[] adjList) {
        Set<Integer> leafVertices = new HashSet<>();

        // iterate over all vertices on the adj list
        for (int i = 0; i < adjList.length; i++) {
            // if the vertex has exactly 1 neighbor, it is a leaf vertex
            if (adjList[i].size() == 1) {
                leafVertices.add(i);
            }
        }
        return leafVertices;
    }

    // from a given adjacency list and a list of leaf vertices, find the support
    // vertices that support the leaf vertices
    public static Set<Integer> findSupportVertices(List<Integer>[] adjList, Set<Integer> leafVertices) {
        Set<Integer> supportVertices = new HashSet<>();

        // iterate over all leaf vertices
        for (int leafVertex : leafVertices) {
            // add the neighbor of the leaf vertex to the support vertices
            supportVertices.add(adjList[leafVertex].get(0));
        }
        return supportVertices;
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

    public static void main(String[] args) {
        Graph graph = new Graph("massive.txt");

        System.out.println("Leaf vertices: " + graph.getLeafVertices());
        System.out.println("Support vertices: " + graph.getSupportVertices());

    }
}
