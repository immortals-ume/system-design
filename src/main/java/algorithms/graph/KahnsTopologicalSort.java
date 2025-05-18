package algorithms.graph;

import java.util.*;

public class KahnsTopologicalSort {
    public static void topologicalSortKahns(Map<String, List<String>> graph) {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String node : graph.keySet()) {
            inDegree.putIfAbsent(node, 0);
            for (String neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            result.add(curr);
            for (String neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        System.out.println("✅ Topological Order (Kahn’s): " + result);
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();

        graph.put("Cook", List.of("Eat"));
        graph.put("Shop", List.of("Cook"));
        graph.put("Code", List.of("Sleep"));
        graph.put("Eat", List.of("Sleep"));
        graph.put("Sleep", new ArrayList<>());

        topologicalSortKahns(graph);
    }
}
