
package algorithms.graph;

import java.util.*;

public class KosarajuSCC {

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", List.of("C"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", List.of("E"));
        graph.put("E", List.of("F"));
        graph.put("F", List.of("D"));

        findSCCs(graph);
    }

    static void findSCCs(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        // Step 1: First DFS to fill stack
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs1(node, graph, visited, stack);
            }
        }

        // Step 2: Transpose the graph (reverse all edges)
        Map<String, List<String>> transposed = transposeGraph(graph);

        // Step 3: Second DFS on transposed graph
        visited.clear();
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            String node = stack.pop();
            if (!visited.contains(node)) {
                List<String> component = new ArrayList<>();
                dfs2(node, transposed, visited, component);
                System.out.println(component);
            }
        }
    }

    static void dfs1(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs1(neighbor, graph, visited, stack);
            }
        }
        stack.push(node);
    }

    static void dfs2(String node, Map<String, List<String>> graph, Set<String> visited, List<String> component) {
        visited.add(node);
        component.add(node);
        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs2(neighbor, graph, visited, component);
            }
        }
    }

    static Map<String, List<String>> transposeGraph(Map<String, List<String>> graph) {
        Map<String, List<String>> transposed = new HashMap<>();
        for (String u : graph.keySet()) {
            for (String v : graph.get(u)) {
                transposed.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
            }
        }
        return transposed;
    }
}
