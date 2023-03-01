package com.dominatingset;

import java.util.ArrayList;
import java.util.List;

public class Mine {

    private static List<Integer>[] graph;
    private static boolean[] visited;

    private static List<Integer> findDominantSet(int vertices) {
        List<Integer> dominantSet = new ArrayList<>();
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dominantSet.add(i);
                visited[i] = true;
                for (int neighbor : graph[i]) {
                    visited[neighbor] = true;
                }
            }
        }
        return dominantSet;
    }

    public static void main(String[] args) {
        int vertices = 12;
        int edges = 12;

        // Define the graph
        graph = new List[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(1);
        graph[1].add(0); // x = 1, y = 2 ;
        graph[1].add(2);
        graph[2].add(1); // x = 2, y = 3 ;
        graph[2].add(3);
        graph[3].add(2); // x = 3, y = 4 ;
        graph[0].add(3);
        graph[3].add(0); // x = 1, y = 4 ;
        graph[3].add(4);
        graph[4].add(3); // x = 4, y = 5 ;
        graph[2].add(4);
        graph[4].add(2); // x = 3, y = 5 ;
        graph[0].add(4);
        graph[4].add(0); // x = 1, y = 5 ;
        graph[1].add(4);
        graph[4].add(1); // x = 2, y = 5 ;
        graph[0].add(2);
        graph[2].add(0); // x = 1, y = 3 ;
        graph[1].add(3);
        graph[3].add(1); // x = 2, y = 4 ;
        graph[6].add(7);
        graph[7].add(6); // x = 7, y = 8 ;
        graph[6].add(8);
        graph[8].add(6); // x = 7, y = 9 ;
        graph[7].add(8);
        graph[8].add(7); // x = 8, y = 9 ;
        graph[6].add(11);
        graph[11].add(6); // x = 7, y = 12 ;

        List<Integer> dominantSet = findDominantSet(vertices);

        System.out.print("The Dominant Set is : { ");
        for (int i = 0; i < dominantSet.size(); i++) {
            System.out.print(dominantSet.get(i) + 1 + " ");
        }
        System.out.print("}");
    }
}
