package algorithms.graph;

import java.util.*;

public class PrimCityMST {
    public static void prim(Map<String, List<Edge>> graph, String start) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        visited.add(start);
        pq.addAll(graph.get(start));

        int totalCost = 0;

        System.out.println("🚀 Roads selected for MST:");
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (visited.contains(edge.to)) continue;

            visited.add(edge.to);
            totalCost += edge.cost;
            System.out.println(" - Connect to " + edge.to + " @ " + edge.cost);

            for (Edge next : graph.get(edge.to)) {
                if (!visited.contains(next.to)) {
                    pq.offer(next);
                }
            }
        }

        System.out.println("✅ Total minimum cost = " + totalCost);
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        String[] cities = {"A", "B", "C", "D", "E"};

        for (String city : cities) {
            graph.put(city, new ArrayList<>());
        }

        // Add roads (bidirectional)
        graph.get("A").add(new Edge("B", 4));
        graph.get("B").add(new Edge("A", 4));

        graph.get("A").add(new Edge("C", 2));
        graph.get("C").add(new Edge("A", 2));

        graph.get("B").add(new Edge("C", 1));
        graph.get("C").add(new Edge("B", 1));

        graph.get("B").add(new Edge("D", 5));
        graph.get("D").add(new Edge("B", 5));

        graph.get("C").add(new Edge("D", 8));
        graph.get("D").add(new Edge("C", 8));

        graph.get("C").add(new Edge("E", 10));
        graph.get("E").add(new Edge("C", 10));

        graph.get("D").add(new Edge("E", 2));
        graph.get("E").add(new Edge("D", 2));

        prim(graph, "A");
    }

    static class Edge {
        String to;
        int cost;

        Edge(String to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
