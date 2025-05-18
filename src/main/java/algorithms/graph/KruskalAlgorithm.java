package algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int s, int d, int w) {
        this.src = s;
        this.dest = d;
        this.weight = w;
    }

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

public class KruskalAlgorithm {
    static int V = 5; // Number of vertices

    // Find the parent of a node (with path compression)
    static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    // Union two sets
    static void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    static void kruskalMST(List<Edge> edges) {
        Collections.sort(edges); // Step 1: Sort edges by weight

        int[] parent = new int[V];
        int[] rank = new int[V];

        // Initialize each node's parent to itself
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        List<Edge> result = new ArrayList<>();
        int i = 0; // edge index

        while (result.size() < V - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);

            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            // If no cycle formed, add the edge
            if (x != y) {
                result.add(nextEdge);
                union(parent, rank, x, y);
            }
        }

        // Print the result
        System.out.println("Edges in Minimum Spanning Tree:");
        int totalWeight = 0;
        for (Edge edge : result) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
            totalWeight += edge.weight;
        }
        System.out.println("Total Minimum Cost = " + totalWeight);
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();

        // Example graph
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        kruskalMST(edges);
    }
}
