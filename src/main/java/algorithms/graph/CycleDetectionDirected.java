package algorithms.graph;

import java.util.*;

public class CycleDetectionDirected {
    static boolean dfs(String node, Map<String, List<String>> graph, Set<String> visited, Set<String> inStack) {
        visited.add(node);
        inStack.add(node);

        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, graph, visited, inStack)) return true;
            } else if (inStack.contains(neighbor)) {
                return true; // Cycle found
            }
        }

        inStack.remove(node);
        return false;
    }

    public static boolean hasCycle(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> inStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node) && dfs(node, graph, visited, inStack)) return true;

        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));
        graph.put("B", List.of("C"));
        graph.put("C", List.of("A")); // Cycle: A → B → C → A

        System.out.println("Cycle Detected: " + hasCycle(graph)); // true
    }
}
