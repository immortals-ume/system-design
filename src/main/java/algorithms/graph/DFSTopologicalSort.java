package algorithms.graph;

import java.util.*;

public class DFSTopologicalSort {
    static void dfs(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, stack);
            }
        }
        stack.push(node); // Add to stack when all dependencies are done
    }

    public static void topologicalSortDFS(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, graph, visited, stack);
            }
        }

        System.out.println("✅ Topological Order (DFS):");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("Cook", List.of("Eat"));
        graph.put("Shop", List.of("Cook"));
        graph.put("Code", List.of("Sleep"));
        graph.put("Eat", List.of("Sleep"));
        graph.put("Sleep", new ArrayList<>()); // end task

        topologicalSortDFS(graph);
    }
}
