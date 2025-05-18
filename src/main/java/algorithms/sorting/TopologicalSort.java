package algorithms.sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    public static void main(String[] args) {
        int vertices = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Define the graph edges
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        /*
         Graph:
         5 → 2 → 3 → 1
         5 → 0
         4 → 0
         4 → 1
        */

        topologicalSort(vertices, adj);
    }

    private static void topologicalSort(int vertices, List<List<Integer>> adj) {
        int[] inDegree = new int[vertices];

        // 1. Calculate in-degrees (how many nodes point to each node)
        for (List<Integer> neighbours : adj) {
            for (int neighbour : neighbours) {
                inDegree[neighbour]++;
            }
        }

        // 2. Add nodes with 0 in-degree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 3. Process the queue
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            topoOrder.add(current);

            // Decrease in-degree of neighbours
            for (int neighbour : adj.get(current)) {
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        // 4. Check for cycle
        if (topoOrder.size() != vertices) {
            System.out.println("Graph has a cycle! No topological order.");
        } else {
            System.out.println("Topological Sort Order: " + topoOrder);
        }
    }
}
