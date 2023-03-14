package com.dominatingset.components.functionalities;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    // function for transforming an adjacency matrix into a adjacency list
    @SuppressWarnings("unchecked")
    public static List<Integer>[] matrixToAdjacencyList(boolean[][] adjMatrix) {
        // create a new adjacency list
        List<Integer>[] adjList = new ArrayList[adjMatrix.length];

        // add all vertices to the adjacency list and add edges as you go
        for (int i = 0; i < adjMatrix.length; i++) {
            adjList[i] = new ArrayList<>();

            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j]) {
                    adjList[i].add(j);
                }
            }
        }
        return adjList;
    }
}
