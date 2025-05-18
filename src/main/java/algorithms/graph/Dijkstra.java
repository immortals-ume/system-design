package algorithms.graph;

import java.util.*;

public class Dijkstra {
    private final Map<String, List<Edge>> graph;

    public Dijkstra() {
        graph = new HashMap<>();
    }

    public static void main(String[] args) {
        Dijkstra myGraph = new Dijkstra();

        myGraph.addEdge("A", "B", 4);
        myGraph.addEdge("A", "C", 2);
        myGraph.addEdge("B", "C", 5);
        myGraph.addEdge("B", "D", 10);
        myGraph.addEdge("C", "E", 3);
        myGraph.addEdge("E", "D", 4);
        myGraph.addEdge("D", "F", 11);

        Map<String, Integer> distances = myGraph.dijkstra("A");

        myGraph.printGraph();

        for (String node : distances.keySet()) {
            System.out.println("Distance from A to " + node + " is " + distances.get(node));
        }
    }

    // Add a weighted edge from 'from' to 'to'
    public void addEdge(String from, String to, int weight) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(new Edge(to, weight));
    }

    // Dijkstra's Algorithm
    public Map<String, Integer> dijkstra(String start) {
        // Distances map: node -> the shortest distance from start
        Map<String, Integer> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);  // Infinity
        }
        distances.put(start, 0);

        // Priority queue to pick the node with smallest distance
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );
        pq.add(new AbstractMap.SimpleEntry<>(start, 0));

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            String currentNode = entry.getKey();

            if (visited.contains(currentNode)) continue;
            visited.add(currentNode);

            for (Edge edge : graph.get(currentNode)) {
                String neighbor = edge.target;
                int newDist = distances.get(currentNode) + edge.weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new AbstractMap.SimpleEntry<>(neighbor, newDist));
                }
            }
        }

        return distances;
    }

    public void printGraph() {
        for (String node : graph.keySet()) {
            System.out.println(node + " is connected to " + graph.get(node));
        }
    }

    // Edge class to store neighbors and weights
    static class Edge {
        String target;
        int weight;

        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
}
