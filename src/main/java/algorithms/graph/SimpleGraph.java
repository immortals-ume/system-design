package algorithms.graph;

import java.util.*;

public class SimpleGraph {
    private final Map<String, List<String>> graph;

    public SimpleGraph() {
        graph = new HashMap<>();
    }

    public static void main(String[] args) {
        SimpleGraph myGraph = new SimpleGraph();

        myGraph.addEdge("Alice", "Bob");
        myGraph.addEdge("Alice", "Charlie");
        myGraph.addEdge("Bob", "Dave");
        myGraph.addEdge("Charlie", "Dave");

        myGraph.printGraph();

        // Start DFS from Alice
        myGraph.dfs("Alice");
        myGraph.bfs("Alice");
    }

    // Add a node to the graph
    public void addNode(String node) {
        graph.putIfAbsent(node, new ArrayList<>());
    }

    // Add an edge (connection) between two nodes
    public void addEdge(String from, String to) {
        // Make sure both nodes exist
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());

        // Add the connection from "from" to "to"
        graph.get(from).add(to);
    }

    // Print the graph connections
    public void printGraph() {
        for (String node : graph.keySet()) {
            System.out.println(node + " is connected to " + graph.get(node));
        }
    }

    // DFS method: starts from a node and visits all reachable nodes
    public void dfs(String start) {
        Set<String> visited = new HashSet<>(); // To keep track of visited nodes
        dfsHelper(start, visited);
    }

    // Helper method to do recursive DFS
    private void dfsHelper(String node, Set<String> visited) {
        if (visited.contains(node)) {
            return; // Already visited, stop here
        }

        // Mark this node as visited and "visit" it (print it)
        System.out.println("Visited: " + node);
        visited.add(node);

        // Visit all neighbors (connected nodes) of this node recursively
        for (String neighbor : graph.get(node)) {
            dfsHelper(neighbor, visited);
        }
    }

    // Iterative DFS using a stack
    public void dfsIterative(String start) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String node = stack.pop();

            if (!visited.contains(node)) {
                System.out.println("Visited: " + node);
                visited.add(node);

                // Push neighbors onto stack
                // To maintain similar order as recursive, push neighbors in reverse order
                List<String> neighbors = graph.get(node);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    // BFS using a queue
    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();  // Remove from front of the queue
            System.out.println("Visited: " + node);

            for (String neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}
