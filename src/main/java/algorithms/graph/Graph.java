package algorithms.graph;

import java.util.*;

public class Graph<T> {
    private final Map<T, List<T>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Add an edge between source and destination (undirected)
    public void addEdge(T source, T destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        adjacencyList.get(source)
                .add(destination);
        adjacencyList.get(destination)
                .add(source); // Remove if directed graph
    }

    // BFS traversal starting from startNode
    public List<T> bfs(T startNode) {
        if (!adjacencyList.containsKey(startNode)) {
            throw new IllegalArgumentException("Start node not found in graph");
        }

        List<T> traversalOrder = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            traversalOrder.add(current);

            for (T neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return traversalOrder;
    }

    // Optional: Get neighbors of a vertex
    public List<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    // Optional: Check if graph contains a vertex
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    // Optional: String representation of graph
    @Override
    public String toString() {
        return adjacencyList.toString();
    }
}
