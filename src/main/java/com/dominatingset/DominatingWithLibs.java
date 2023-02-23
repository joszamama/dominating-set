package com.dominatingset;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexCoverAlgorithm.VertexCover;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;
import org.jgrapht.graph.DefaultEdge;
import java.util.Arrays;
import java.util.List;

public class DominatingWithLibs {

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
        // Compute the vertex cover of the graph using the exact algorithm
        long exactStartTime = System.nanoTime();
        RecursiveExactVCImpl<String, DefaultEdge> exactAlgorithm = new RecursiveExactVCImpl<>(graph);
        VertexCover<String> vertexCover = exactAlgorithm.getVertexCover();
        long exactEndTime = System.nanoTime();
        double exactTimeElapsed = (exactEndTime - exactStartTime) / 1_000_000_000.0;
        long exactMemoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);

        // Print the results
        System.out.println("------- Exact vertex cover -------");
        System.out.println("Exact solution: " + exactAlgorithm.getVertexCover());
        System.out.println("Exact time elapsed: " + exactTimeElapsed + " seconds");
        System.out.println("Exact memory used: " + exactMemoryUsed + " MB");

        return Arrays.asList(vertexCover, exactTimeElapsed, exactMemoryUsed);
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
        long approximateStartTime = System.nanoTime();
        GreedyVCImpl<String, DefaultEdge> approximateAlgorithm = new GreedyVCImpl<>(graph);
        VertexCover<String> vertexCover = approximateAlgorithm.getVertexCover();
        long approximateEndTime = System.nanoTime();
        double approximateTimeElapsed = (approximateEndTime - approximateStartTime) / 1_000_000_000.0;
        long approximateMemoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                / (1024 * 1024);

        // Print the results
        System.out.println("------- Approximate vertex cover -------");
        System.out.println("Approximate solution: " + vertexCover);
        System.out.println("Approximate time elapsed: " + approximateTimeElapsed + " seconds");
        System.out.println("Approximate memory used: " + approximateMemoryUsed + " MB");

        return Arrays.asList(vertexCover, approximateTimeElapsed, approximateMemoryUsed);
    }

    public static void compareWithLibs(Graph<String, DefaultEdge> graph) {
        List<Object> exact = exactDominatingSetWithLibs(graph);
        List<Object> approximate = aproxDominatingSetWithLibs(graph);

        System.out.println("------- Comparison -------");

        // Compare time and space differences
        double timeDifference = (double) exact.get(1) - (double) approximate.get(1);
        long memoryDifference = (long) exact.get(2) - (long) approximate.get(2);
        System.out.println("Time difference: " + timeDifference + " seconds");
        System.out.println("Memory difference: " + memoryDifference + " MB");
    }

    public static void main(String[] args) {
        // Create a graph
        Graph<String, DefaultEdge> graph = GraphGenerator.generateGraph(130, 0.3);

        System.out.println("--------------------");
        System.out.println("Compute the Minimum Dominating Set of a Graph");
        System.out.println("--------------------");

        // Compare the results of the exact and approximate algorithms
        compareWithLibs(graph);

        // Overall, the GreedyVCImpl algorithm is relatively efficient in both time and
        // space complexity, especially compared to the RecursiveExactVCImpl algorithm
        // which has an exponential worst-case time complexity. However, it should be
        // noted that the GreedyVCImpl algorithm may not always compute the exact
        // minimum vertex cover of the graph, since it uses a heuristic approach to
        // select the vertices for the vertex cover.

        // The RecursiveExactVCImpl algorithm, on the other hand, always computes the
        // exact minimum vertex cover of the graph, but it is much more computationally
        // expensive than the GreedyVCImpl algorithm. Therefore, the GreedyVCImpl
        // algorithm is a good choice for computing a vertex cover of a graph when the
        // exact solution is not required, and the time and space complexity of the
        // algorithm are important considerations.

    }
}
