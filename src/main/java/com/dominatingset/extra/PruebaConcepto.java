package com.dominatingset.extra;

import java.util.ArrayList;
import java.util.List;

public class PruebaConcepto {

    private static boolean[][] graph;
    private static boolean[] visited;

    private static List<Integer> findDominantSet(int vertices) {
        List<Integer> dominantSet = new ArrayList<>();
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dominantSet.add(i);
                visited[i] = true;
                for (int j = 0; j < vertices; j++) {
                    if (graph[i][j]) {
                        visited[j] = true;
                    }
                }
            }
        }
        return dominantSet;
    }

    public static void main(String[] args) {
        int vertices = 12;

        // Define the graph
        graph = new boolean[vertices][vertices];

        graph[0][1] = true;
        graph[1][2] = true;
        graph[2][3] = true;
        graph[0][3] = true;
        graph[3][4] = true;
        graph[2][4] = true;
        graph[0][4] = true;
        graph[1][4] = true;
        graph[0][2] = true;
        graph[1][3] = true;
        graph[6][7] = true;
        graph[6][8] = true;
        graph[7][8] = true;
        graph[6][11] = true;

        List<Integer> dominantSet = findDominantSet(vertices);

        System.out.print("The Dominant Set is : { ");
        for (int i = 0; i < dominantSet.size(); i++) {
            System.out.print((dominantSet.get(i) + 1) + " ");
        }
        System.out.print("}");
    }
}
