package com.dominatingset.components;

import java.util.HashSet;
import java.util.Set;

import com.dominatingset.Graph;

public class LocalImprovement {

    private static Graph graph;

    public LocalImprovement(Graph graph) {
        LocalImprovement.graph = graph;
    }

    // function to exchange vertices in a dominating set
    public Set<Integer> exchange(Set<Integer> minimalSolution) {
        Set<Integer> vertices = graph.getAllVertices();
        for (int i = 0; i < minimalSolution.size(); i++) {
            Set<Integer> newMinimalSolution = new HashSet<Integer>(minimalSolution);
            // remove random vertex from minimalSolution
            int randomVertex = (int) (Math.random() * minimalSolution.size());
            newMinimalSolution.remove(newMinimalSolution.toArray(new Integer[0])[randomVertex]);
            // add random vertex from vertices
            randomVertex = (int) (Math.random() * vertices.size());
            newMinimalSolution.add(vertices.toArray(new Integer[0])[randomVertex]);
            if (graph.isDominatingSet(newMinimalSolution)) {
                minimalSolution = newMinimalSolution;
            }
        }
        return minimalSolution;
    }

}
