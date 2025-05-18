package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSTopologicalSort {

    public static void dfsTopoSort(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsTopoSort(neighbor, visited, stack, adj);
            }
        }

        stack.push(node); // Push after visiting all dependencies
    }

    public static void topologicalSort(int vertices, List<List<Integer>> adj) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsTopoSort(i, visited, stack, adj);
            }
        }

        System.out.print("Topological Sort Order (DFS): ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Same graph as before
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        topologicalSort(vertices, adj);
    }
}
