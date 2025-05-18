package algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordAlgorithm {

    int V; // number of vertices
    List<Edge> edges;
    public BellmanFordAlgorithm(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public static void main(String[] args) {
        BellmanFordAlgorithm graph = new BellmanFordAlgorithm(5);

        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 5);
        graph.addEdge(1, 4, -4);
        graph.addEdge(2, 3, -3);
        graph.addEdge(2, 4, 9);
        graph.addEdge(3, 1, -2);
        graph.addEdge(4, 0, 2);
        graph.addEdge(4, 3, 7);

        graph.bellmanFord(0);
    }

    public void addEdge(int from, int to, int weight) {
        edges.add(new Edge(from, to, weight));
    }

    public void bellmanFord(int start) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Relax edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.from] + edge.weight < dist[edge.to]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print distances
        for (int i = 0; i < V; i++) {
            System.out.println("Distance from " + start + " to " + i + " is " + dist[i]);
        }
    }

    static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
