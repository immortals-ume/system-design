package algorithms.graph;

import java.util.List;

public class BFSMain {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "F");

        List<String> bfsResult = graph.bfs("A");
        System.out.println("BFS Traversal Order: " + bfsResult);
    }
}
