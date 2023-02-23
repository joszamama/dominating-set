package com.dominatingset;

import org.jgrapht.Graph;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.List;

public class Dominating {

    // The RecursiveExactVCImpl algorithm computes the exact minimum vertex cover of
    // a graph using a recursive branching strategy. The algorithm has an
    // exponential time complexity in the worst case, with a running time of O(2^n),
    // where n is the number of vertices in the graph. However, in practice, the
    // algorithm performs much better on real-world graphs, often finding the exact
    // minimum vertex cover in a reasonable amount of time.

    // The space complexity of the algorithm is also exponential in the worst case,
    // with a space usage of O(2^n). This is because the algorithm constructs a
    // binary tree of depth n, where each node represents a possible solution to the
    // vertex cover problem. The space used by the algorithm is proportional to the
    // number of nodes in this tree, which is exponential in the worst case.

    public static List<Object> exactDominatingSetWithLibs(Graph<String, DefaultEdge> graph) {
        RecursiveExactVCImpl<String, DefaultEdge> exactAlgorithm = new RecursiveExactVCImpl<>(graph);
        long exactStartTime = System.nanoTime();
        long exactEndTime = System.nanoTime();
        long exactTimeElapsed = exactEndTime - exactStartTime;
        long exactMemoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Print the results
        System.out.println("------- Exact vertex cover -------");
        System.out.println("Exact solution: " + exactAlgorithm.getVertexCover());
        System.out.println("Exact time elapsed: " + exactTimeElapsed + " nanoseconds");
        System.out.println("Exact memory used: " + exactMemoryUsed + " bytes");

        return Arrays.asList(exactAlgorithm.getVertexCover(), exactTimeElapsed, exactMemoryUsed);
    }

    // The time complexity of the GreedyVCImpl algorithm is O(|E|), where |E| is the
    // number of edges in the graph. This is because the algorithm iterates over all
    // the edges in the graph and selects the endpoints of each edge that are not
    // yet covered by the vertex cover.

    // The space complexity of the GreedyVCImpl algorithm is O(|V|), where |V| is
    // the number of vertices in the graph. This is because the algorithm maintains
    // a set of candidate vertices that have not yet been added to the vertex cover,
    // which could potentially include all the vertices in the graph.

    public static List<Object> aproxDominatingSetWithLibs(Graph<String, DefaultEdge> graph) {
        // Compute the vertex cover of the graph using the greedy algorithm
        GreedyVCImpl<String, DefaultEdge> approximateAlgorithm = new GreedyVCImpl<>(graph);
        long approximateStartTime = System.nanoTime();
        long approximateEndTime = System.nanoTime();
        long approximateTimeElapsed = approximateEndTime - approximateStartTime;
        long approximateMemoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // Print the results
        System.out.println("------- Approximate vertex cover -------");
        System.out.println("Approximate solution: " + approximateAlgorithm.getVertexCover());
        System.out.println("Approximate time elapsed: " + approximateTimeElapsed + " nanoseconds");
        System.out.println("Approximate memory used: " + approximateMemoryUsed + " bytes");

        return Arrays.asList(approximateAlgorithm.getVertexCover(), approximateTimeElapsed, approximateMemoryUsed);

    }

    public static void main(String[] args) {
        // Create a graph
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Add some vertices to the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");

        // Add some edges to the graph
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");
        graph.addEdge("E", "H");
        graph.addEdge("F", "G");
        graph.addEdge("A", "H");
        graph.addEdge("H", "I");
        graph.addEdge("B", "G");
        graph.addEdge("C", "I");

        System.out.println("--------------------");

        System.out.println("Compute the Minimum Dominating Set of Graph:");
        System.out.println(graph);

        // Compute the minimum dominating set of the graph using exact algorithm
        exactDominatingSetWithLibs(graph);

        // Compute the minimum dominating set of the graph using approximate algorithm
        aproxDominatingSetWithLibs(graph);

        System.out.println("--------------------");

        // Overall, the GreedyVCImpl algorithm is relatively efficient in both time and
        // space complexity, especially compared to the RecursiveExactVCImpl algorithm
        // which has an exponential worst-case time complexity. However, it should be
        // noted that the GreedyVCImpl algorithm may not always compute the exact
        // minimum vertex cover of the graph, since it uses a heuristic approach to
        // select the vertices for the vertex cover.

    }
}
