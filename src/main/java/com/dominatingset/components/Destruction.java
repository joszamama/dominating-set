package com.dominatingset.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dominatingset.Graph;

public class Destruction {

    private static Graph graph;

    public Destruction(Graph graph) {
        Destruction.graph = graph;
    }

    // function for destroying a solution by removing a random percentage of
    // vertices
    public Set<Integer> randomDestruction(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        // Remove a random percentage of vertices from the minimal solution
        for (int i = 0; i < minimalSolution.size() * REMOVE_VERTICES_PERCENTAGE; i++) {
            int randomVertex = (int) (Math.random() * minimalSolution.size());
            minimalSolution.remove(randomVertex);
        }
        return minimalSolution;
    }

    // function for destroying a solution by removing a random percentage of
    // vertices
    public Set<Integer> randomSlicing(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        int k = (int) (minimalSolution.size() * REMOVE_VERTICES_PERCENTAGE);
        List<Integer> indices = new ArrayList<>(minimalSolution);
        Collections.shuffle(indices);
        Set<Integer> removedVertices = new HashSet<>(indices.subList(0, k));
        minimalSolution.removeAll(removedVertices);
        return minimalSolution;
    }

    public Set<Integer> greedyDestruction(Set<Integer> minimalSolution, double REMOVE_VERTICES_PERCENTAGE) {
        int n = (int) (minimalSolution.size() * REMOVE_VERTICES_PERCENTAGE);
        List<Integer> verticesToRemove = new ArrayList<>();
        List<Integer> sortedVertices = minimalSolution.stream()
                .sorted(Comparator.comparingInt(vertex -> graph.getNeighbors(vertex).size()))
                .collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            if (sortedVertices.size() > i) {
                verticesToRemove.add(sortedVertices.get(i));
            } else {
                break;
            }
        }
        System.out.println("Removing " + verticesToRemove.size() + " vertices");
        minimalSolution.removeAll(verticesToRemove);
        return minimalSolution;
    }

}
