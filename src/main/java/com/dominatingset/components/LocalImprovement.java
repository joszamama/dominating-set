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
        Set<Integer> allVerticesSet = graph.getAllVertices();
        for (int i = 0; i < minimalSolution.size(); i++) {
            Set<Integer> newMinimalSolution = new HashSet<Integer>(minimalSolution);
            // remove random vertex from minimalSolution
            int randomVertex = (int) (Math.random() * minimalSolution.size());
            newMinimalSolution.remove(newMinimalSolution.toArray(new Integer[0])[randomVertex]);

            // add random vertex from allVerticesSet
            randomVertex = (int) (Math.random() * allVerticesSet.size());
            newMinimalSolution.add(allVerticesSet.toArray(new Integer[0])[randomVertex]);

            // check for redundant vertices
            newMinimalSolution = graph.removeRedundancy(newMinimalSolution);

            if (graph.isDominatingSet(newMinimalSolution) && newMinimalSolution.size() < minimalSolution.size()) {
                minimalSolution = new HashSet<>(newMinimalSolution);
            }
        }
        return minimalSolution;
    }

}
