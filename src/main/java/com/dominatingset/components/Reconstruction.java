package com.dominatingset.components;

import java.util.List;
import java.util.Set;

import com.dominatingset.Graph;

public class Reconstruction {

    private static Graph graph;

    public Reconstruction(Graph graph) {
        Reconstruction.graph = graph;
    }

    public Set<Integer> randomReconstruction(Set<Integer> minimalSolution) {
        Set<Integer> vertices = graph.getAllVertices();
        while (!graph.isDominatingSet(minimalSolution)) {
            int randomVertex = (int) (Math.random() * vertices.size()); // Select random vertex from vertices
            minimalSolution.add(vertices.toArray(new Integer[0])[randomVertex]); // Add vertex to minimalSolution
            vertices.remove(vertices.toArray(new Integer[0])[randomVertex]); // Remove vertex from vertices
        }
        return minimalSolution;
    }

    public Set<Integer> greedyReconstruction(Set<Integer> minimalSolution) {
        List<Integer> bestVertices = graph.getBestVertices();
        for (int i = 0; i < bestVertices.size(); i++) {
            minimalSolution.add(bestVertices.get(i));
            if (graph.isDominatingSet(minimalSolution)) {
                return minimalSolution;
            }
        }
        return minimalSolution;
    }

}
