package algorithms.graph;

import java.util.*;

public class CycleDetectionUndirected {
    static boolean dfs(String node, String parent, Map<String, List<String>> graph, Set<String> visited) {
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, node, graph, visited)) return true;
            } else if (!neighbor.equals(parent)) {
                return true; // Found a cycle
            }
        }
        return false;
    }

    public static boolean hasCycle(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node) && dfs(node, null, graph, visited)) return true;

        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", Arrays.asList("A", "C"));
        graph.put("C", Arrays.asList("B", "A")); // Cycle: A - B - C - A

        System.out.println("Cycle Detected: " + hasCycle(graph)); // true
    }
}
