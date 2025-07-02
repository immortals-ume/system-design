package algorithms.graph;

import java.util.*;

public class DFSGraph {

    // ✅ Main method for testing all
    public static void main(String[] args) {
        // Example: Change to true for directed graph
        Graph graph = new Graph(6, false);

        // Edges for example
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(6, 7); // Disconnected component

        // Test Recursive DFS
        graph.dfsRecursive(1);

        // Test Iterative DFS
        graph.dfsIterative(1);

        // Test Connected Components
        System.out.println("\nConnected Components:");
        graph.findConnectedComponents();

        // Test Cycle Detection
        System.out.println("\nCycle Detection:");
        if (graph.directed) {
            System.out.println("Cycle in directed graph? " + graph.hasCycleDirected());
        } else {
            System.out.println("Cycle in undirected graph? " + graph.hasCycleUndirected());
        }
    }

    static class Graph {
        private final int V;
        private final Map<Integer, List<Integer>> adj = new HashMap<>();
        private final boolean directed;

        public Graph(int v, boolean directed) {
            V = v;
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

        // ✅ 1. Recursive DFS
        public void dfsRecursive(int start) {
            Set<Integer> visited = new HashSet<>();
            System.out.println("Recursive DFS starting from " + start + ":");
            dfsHelper(start, visited);
        }

        private void dfsHelper(int node, Set<Integer> visited) {
            visited.add(node);
            System.out.println("Visited: " + node);
            for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    dfsHelper(neighbor, visited);
                }
            }
        }

        // ✅ 2. Iterative DFS using Stack
        public void dfsIterative(int start) {
            Set<Integer> visited = new HashSet<>();
            Stack<Integer> stack = new Stack<>();

            stack.push(start);
            System.out.println("Iterative DFS starting from " + start + ":");

            while (!stack.isEmpty()) {
                int current = stack.pop();
                if (!visited.contains(current)) {
                    visited.add(current);
                    System.out.println("Visited: " + current);
                    for (int neighbor : adj.getOrDefault(current, new ArrayList<>())) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }

        // ✅ 3. Connected Components using DFS
        public void findConnectedComponents() {
            Set<Integer> visited = new HashSet<>();
            int count = 0;

            for (int node : adj.keySet()) {
                if (!visited.contains(node)) {
                    count++;
                    System.out.println("\nComponent " + count + ":");
                    dfsHelper(node, visited);
                }
            }
        }

        // ✅ 4a. Cycle Detection in Undirected Graph
        public boolean hasCycleUndirected() {
            Set<Integer> visited = new HashSet<>();
            for (int node : adj.keySet()) {
                if (!visited.contains(node)) {
                    if (dfsCycleUndirected(node, -1, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfsCycleUndirected(int node, int parent, Set<Integer> visited) {
            visited.add(node);
            for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    if (dfsCycleUndirected(neighbor, node, visited)) return true;
                } else if (neighbor != parent) {
                    return true; // found a cycle
                }
            }
            return false;
        }

        // ✅ 4b. Cycle Detection in Directed Graph
        public boolean hasCycleDirected() {
            Set<Integer> visited = new HashSet<>();
            Set<Integer> recStack = new HashSet<>();

            for (int node : adj.keySet()) {
                if (dfsCycleDirected(node, visited, recStack)) {
                    return true;
                }
            }
            return false;
        }

        private boolean dfsCycleDirected(int node, Set<Integer> visited, Set<Integer> recStack) {
            if (recStack.contains(node)) return true;
            if (visited.contains(node)) return false;

            visited.add(node);
            recStack.add(node);

            for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                if (dfsCycleDirected(neighbor, visited, recStack)) return true;
            }

            recStack.remove(node);
            return false;
        }

        public void findBridges() {
            int[] disc = new int[V];
            int[] low = new int[V];
            boolean[] visited = new boolean[V];
            int[] parent = new int[V];
            Arrays.fill(disc, -1);
            Arrays.fill(low, -1);
            Arrays.fill(parent, -1);

            List<List<Integer>> bridges = new ArrayList<>();
            int[] time = {0};

            for (int u = 0; u < V; u++) {
                if (!visited[u]) {
                    dfsBridge(u, visited, disc, low, parent, time, bridges);
                }
            }

            System.out.println("Bridges found:");
            for (List<Integer> bridge : bridges) {
                System.out.println(bridge.get(0) + " - " + bridge.get(1));
            }
        }

        private void dfsBridge(int u, boolean[] visited, int[] disc, int[] low, int[] parent,
                               int[] time, List<List<Integer>> bridges) {
            visited[u] = true;
            disc[u] = low[u] = time[0]++;

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    parent[v] = u;
                    dfsBridge(v, visited, disc, low, parent, time, bridges);

                    low[u] = Math.min(low[u], low[v]);

                    if (low[v] > disc[u]) {
                        bridges.add(Arrays.asList(u, v));
                    }
                } else if (v != parent[u]) {
                    // back edge
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
    }
}
