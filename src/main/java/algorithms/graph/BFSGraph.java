package algorithms.graph;

import java.util.*;

public class BFSGraph {

    // Main runner
    public static void main(String[] args) {
        Graph graph = new Graph(false); // set to true for directed graph

        // Example Graph:
        //     1
        //    / \
        //   2   3
        //  /
        // 4       5
        // Disconnected component: 6 — 7

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(5, 3); // cross-link
        graph.addEdge(6, 7); // separate component

        // BFS from node 1
        graph.bfs(1);

        // Optional: Traverse all disconnected components
        System.out.println("\n--- BFS on All Components ---");
        graph.bfsAll();
    }

    // Graph using adjacency list
    static class Graph {
        private final Map<Integer, List<Integer>> adj = new HashMap<>();
        private final boolean directed;

        public Graph(boolean directed) {
            this.directed = directed;
        }

        public void addEdge(int u, int v) {
            adj.computeIfAbsent(u, k -> new ArrayList<>())
                    .add(v);
            if (!directed) {
                adj.computeIfAbsent(v, k -> new ArrayList<>())
                        .add(u);
            }
        }

        // BFS starting from a source node
        public void bfs(int start) {
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, Integer> distance = new HashMap<>();

            queue.offer(start);
            visited.add(start);
            distance.put(start, 0);

            System.out.println("BFS traversal from node " + start + ":");

            while (!queue.isEmpty()) {
                int current = queue.poll();
                System.out.println("Visited: " + current + " at level: " + distance.get(current));

                for (int neighbor : adj.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                        distance.put(neighbor, distance.get(current) + 1);
                    }
                }
            }
        }

        // To ensure disconnected nodes are covered
        public void bfsAll() {
            Set<Integer> visited = new HashSet<>();
            for (int node : adj.keySet()) {
                if (!visited.contains(node)) {
                    System.out.println("\nStarting new BFS component from node: " + node);
                    bfsComponent(node, visited);
                }
            }
        }

        private void bfsComponent(int start, Set<Integer> visited) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                int current = queue.poll();
                System.out.println("Visited: " + current);
                for (int neighbor : adj.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
    }
}
