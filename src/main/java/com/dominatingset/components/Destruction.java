package com.dominatingset.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Destruction {

    // function for destroying a solution by removing a random percentage of
    // vertices
    public static Set<Integer> randomDestruction(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        // Remove a random percentage of vertices from the minimal solution
        for (int i = 0; i < minimalSolution.size() * REMOVE_VERTICES_PERCENTAGE; i++) {
            int randomVertex = (int) (Math.random() * minimalSolution.size());
            minimalSolution.remove(randomVertex);
        }
        return minimalSolution;
    }

    // function for destroying a solution by removing a random percentage of
    // vertices
    public static Set<Integer> randomSlicing(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        int k = (int) (minimalSolution.size() * REMOVE_VERTICES_PERCENTAGE);
        List<Integer> indices = new ArrayList<>(minimalSolution);
        Collections.shuffle(indices);
        Set<Integer> removedVertices = new HashSet<>(indices.subList(0, k));
        minimalSolution.removeAll(removedVertices);
        return minimalSolution;
    }

    // function for destroying a solution by removing vertices in a greedy way
    public static Set<Integer> greedyDestruction(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        return minimalSolution;
    }

}
