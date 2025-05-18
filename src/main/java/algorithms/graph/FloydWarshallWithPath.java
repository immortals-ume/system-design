package algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshallWithPath {
    final static int INF = Integer.MAX_VALUE; // Big number as "infinity"
    final static int V = 4; // Number of nodes

    public static void floydWarshall(int[][] graph) {
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        // Step 1: Initialize distance and next matrices
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];

                // If there's a direct edge, set the next node
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Step 2: Run Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Step 3: Detect negative weight cycle
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative weight cycle detected!");
                return;
            }
        }

        // Step 4: Print shortest paths and distances
        printAllPaths(dist, next);
    }

    static void printAllPaths(int[][] dist, int[][] next) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) continue;

                System.out.print("Shortest path from " + i + " to " + j + " (cost: ");
                if (dist[i][j] == INF) {
                    System.out.println("INF): No path");
                } else {
                    System.out.print(dist[i][j] + "): ");
                    List<Integer> path = reconstructPath(i, j, next);
                    for (int node : path) {
                        System.out.print(node + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    static List<Integer> reconstructPath(int u, int v, int[][] next) {
        if (next[u][v] == -1) return new ArrayList<>(); // No path

        List<Integer> path = new ArrayList<>();
        path.add(u);
        while (u != v) {
            u = next[u][v];
            path.add(u);
        }
        return path;
    }

    public static void main(String[] args) {
        int INF = 1000000000;
        int[][] graph = {
                {0, 3, INF, 7},
                {8, 0, 2, INF},
                {5, INF, 0, 1},
                {2, INF, INF, 0}
        };

        floydWarshall(graph);
    }
}
