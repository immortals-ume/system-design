# Graph Algorithms in Data Structures & Algorithms (DSA)

This repository contains implementations and explanations of common **graph algorithms** frequently used in **Data
Structures and Algorithms (DSA)**.

---

## Table of Contents

1. [Introduction](#introduction)
2. [Graph Basics](#graph-basics)
3. [Algorithms Included](#algorithms-included)
4. [Time and Space Complexity Overview](#Time-and-Space-Complexity-Overview)
5. [Detailed Algorithm Descriptions](#detailed-algorithm-descriptions)
6. [Applications](#applications)
7. [References](#references)

---

## Introduction

Graphs are fundamental data structures used to model relationships between objects. They are widely applied in social
networks, routing, task scheduling, compiler optimizations, and more.

This repo provides Java implementations for popular graph algorithms to help learners understand and practice graph
problems.

---

## Graph Basics

- **Directed vs Undirected Graphs**
- **Weighted vs Unweighted Graphs**
- **Representations:** Adjacency List, Adjacency Matrix

---

# Graph Types and Representations

This section provides a concise overview of the main types of graphs, their properties, structural features, and how
they are commonly represented. Understanding these distinctions is essential when selecting algorithms and data
structures for specific graph problems.

---

## 1. Based on Direction of Edges

### Undirected Graph

- **Definition**: Edges do not have direction. For any two nodes \( u \) and \( v \), the edge \((u, v)\) is identical
  to \((v, u)\).
- **Representation**: Adjacency list or matrix is symmetric.
- **Use-cases**:
  - Social networks (e.g., Facebook friendships).
  - Physical connections (roads without one-way constraints).
- **Algorithm Impact**: Traversals like BFS and DFS are simpler due to the symmetry of edges.

### Directed Graph (Digraph)

- **Definition**: Edges have direction; \((u \rightarrow v) \neq (v \rightarrow u)\).
- **Representation**: Adjacency matrix/list is asymmetric.
- **Use-cases**:
  - Task scheduling (topological sort).
  - Web link structures.
  - "Following" systems (Twitter).
- **Key Concepts**:
  - In-degree & Out-degree
  - Strong vs. Weak Connectivity

---

## 2. Based on Edge Weights

### Unweighted Graph

- **Definition**: All edges are treated equally (conceptually, weight = 1).
- **Use-cases**:
  - Shortest path in an unweighted maze (using BFS).

### Weighted Graph

- **Definition**: Edges have weights or costs, impacting traversal.
- **Use-cases**:
  - Road networks (shortest distance/time).
  - Flight cost graphs.
- **Algorithms**:
  - Dijkstra’s (non-negative weights)
  - Bellman-Ford (handles negative weights)
  - Floyd-Warshall (all-pairs shortest path)
  - Prim’s/Kruskal’s (Minimum Spanning Tree)

---

## 3. Based on Cyclic Nature

### Cyclic Graph

- **Definition**: Contains at least one cycle.
- **Examples**:
  - Road loops
  - Circular dependencies in software modules

### Acyclic Graph

- **Definition**: Contains no cycles.
- **Special Case**: Directed Acyclic Graph (DAG)
- **Use-cases**:
  - Topological sorting (e.g., compiler dependency resolution)
  - Task scheduling
  - Dataflow in pipelines

---

## 4. Based on Connectivity

### Connected Graph (Undirected)

- **Definition**: Every vertex is reachable from every other vertex.

### Disconnected Graph

- **Definition**: Some nodes are isolated from others.

### Strongly Connected Graph (Directed)

- **Definition**: Every vertex is reachable from every other vertex, respecting edge directions.
- **Detection**: Kosaraju’s or Tarjan’s algorithms for finding Strongly Connected Components (SCC).

### Weakly Connected Graph

- **Definition**: If all directed edges are treated as undirected, the graph becomes connected.

---

## 5. Based on Vertex Degree & Structure

### Regular Graph

- **Definition**: All vertices have the same degree (e.g., every node has 3 neighbors).
- **Use-case**: Network topology design (e.g., hypercube).

### Complete Graph (Kn)

- **Definition**: Every node is connected to every other node.
- **Edges**:
  - Undirected: \( n(n-1)/2 \)
  - Directed: \( n(n-1) \)
- **Use-cases**:
  - Brute-force TSP (Traveling Salesman Problem)
  - Clique problems

### Sparse Graph

- **Definition**: Number of edges is much less than the maximum (\(|E| \approx |V|\)).
- **Advantages**: Efficient storage with adjacency lists, fast traversals.

### Dense Graph

- **Definition**: Number of edges is close to the maximum possible.
- **Representation**: Often uses adjacency matrices for \(O(1)\) edge checks.

---

## 6. Special Graph Types

### Tree

- **Definition**: Connected, acyclic, undirected graph.
- **Edges**: \( n - 1 \) for \( n \) nodes.
- **Properties**:
  - Unique path between any two nodes.
  - No cycles.
- **Use-cases**:
  - File systems
  - Parse trees
  - Binary Search Trees (BST), Segment Trees

### Forest

- **Definition**: A disjoint union of trees.
- **Use-case**: Union-Find (Disjoint Set) operations.

### Bipartite Graph

- **Definition**: Vertices can be split into two sets \( U \) and \( V \) with no edges within a set.
- **Checking**: BFS coloring (2-colorable = bipartite).
- **Use-cases**:
  - Job assignment (workers ↔ tasks)
  - Matching problems (e.g., Hopcroft-Karp algorithm)

### Multigraph

- **Definition**: Multiple (parallel) edges allowed between the same nodes; may have loops (edges from a node to
  itself).
- **Real-world**: Multiple flights between the same pair of cities.

### Subgraph

- **Definition**: A portion of the original graph, containing a subset of vertices and/or edges.

---

## Graph Representations

| Representation       | Space          | Edge Existence Check        |
|----------------------|----------------|-----------------------------|
| **Adjacency Matrix** | \( O(V^2) \)   | \( O(1) \)                  |
| **Adjacency List**   | \( O(V + E) \) | \( O(V) \) per node (worst) |
| **Edge List**        | \( O(E) \)     | Linear scan (\( O(E) \))    |

---

**Tip**: Choose the graph representation that best matches the problem's requirements and the expected size/structure of
your graph!

Perfect — let’s now dive into **code-level representation of each graph type in Java**, aimed at someone with **5 years
of experience**. I’ll focus on flexible, scalable design using **OOP principles**, **Java collections**, and
performance-aware choices like adjacency lists/matrices.

---

## 🧩 1. **Undirected Graph (Unweighted)** – Adjacency List

```java
class UndirectedGraph {
    private final Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int u, int v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // undirected
    }

    public List<Integer> getNeighbors(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }
}
```

---

## 🧩 2. **Directed Graph (Unweighted)** – Adjacency List

```java
class DirectedGraph {
    private final Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int from, int to) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public List<Integer> getOutgoingEdges(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }
}
```

---

## 🧩 3. **Weighted Graph (Directed/Undirected)** – Adjacency List with Edge Class

```java
class WeightedGraph {
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private final Map<Integer, List<Edge>> adjList = new HashMap<>();
    private final boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(int from, int to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        if (!directed) {
            adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight));
        }
    }

    public List<Edge> getEdges(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }
}
```

---

## 🧩 4. **Graph using Adjacency Matrix** (e.g., for Dense Graphs)

```java
class GraphMatrix {
    private final int[][] matrix;
    private final boolean directed;

    public GraphMatrix(int size, boolean directed) {
        this.matrix = new int[size][size];
        this.directed = directed;
    }

    public void addEdge(int from, int to, int weight) {
        matrix[from][to] = weight;
        if (!directed) matrix[to][from] = weight;
    }

    public int getEdgeWeight(int from, int to) {
        return matrix[from][to];
    }
}
```

---

## 🧩 5. **Tree Representation** (Assuming Rooted Tree)

```java
class Tree {
    private final Map<Integer, List<Integer>> tree = new HashMap<>();

    public void addChild(int parent, int child) {
        tree.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
    }

    public List<Integer> getChildren(int node) {
        return tree.getOrDefault(node, new ArrayList<>());
    }
}
```

---

## 🧩 6. **DAG (Directed Acyclic Graph)** + Topological Sort

```java
class DAG {
    private final Map<Integer, List<Integer>> graph = new HashMap<>();
    private final Set<Integer> visited = new HashSet<>();
    private final Stack<Integer> stack = new Stack<>();

    public void addEdge(int from, int to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public List<Integer> topologicalSort() {
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private void dfs(int node) {
        visited.add(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
        stack.push(node);
    }
}
```

---

## 🧩 7. **Bipartite Graph Checker (BFS coloring)**

```java
class BipartiteChecker {
    public boolean isBipartite(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> color = new HashMap<>();

        for (int node : graph.keySet()) {
            if (!color.containsKey(node)) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(node);
                color.put(node, 0);

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    for (int neighbor : graph.get(curr)) {
                        if (!color.containsKey(neighbor)) {
                            color.put(neighbor, 1 - color.get(curr));
                            queue.offer(neighbor);
                        } else if (color.get(neighbor) == color.get(curr)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
```

---

## 🧩 8. **Complete Graph Generator**

```java
class CompleteGraph {
    private final Map<Integer, List<Integer>> graph = new HashMap<>();

    public CompleteGraph(int n) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (i != j) graph.get(i).add(j);
            }
        }
    }

    public List<Integer> getNeighbors(int node) {
        return graph.getOrDefault(node, new ArrayList<>());
    }
}
```

---

## ✅ Summary Table

| Graph Type     | Representation Style              | Suitable Java Structure          |
|----------------|-----------------------------------|----------------------------------|
| Undirected     | Adjacency List                    | `Map<Integer, List<Integer>>`    |
| Directed       | Adjacency List                    | `Map<Integer, List<Integer>>`    |
| Weighted       | Edge class + Adjacency List       | `Map<Integer, List<Edge>>`       |
| Dense          | Adjacency Matrix                  | `int[][]`                        |
| Tree           | Rooted parent-child map           | `Map<Integer, List<Integer>>`    |
| DAG            | Topological sort + directed edges | Stack + DFS + visited map        |
| Bipartite      | BFS coloring                      | `Map<Integer, Integer> colorMap` |
| Complete Graph | Full connectivity                 | Generate all pairs `i ≠ j`       |

---

Let me know if you want:

* A **visualizer** for these graphs.
* Conversion to other data formats like JSON or DOT (for Graphviz).
* Performance comparisons of these representations for real-world datasets.

## Algorithms Included

| Algorithm                  | Type                                | Description                                 |
|----------------------------|-------------------------------------|---------------------------------------------|
| DFS (Depth-First Search)   | Traversal                           | Explore graph depth-wise                    |
| BFS (Breadth-First Search) | Traversal                           | Explore graph level-wise                    |
| Dijkstra’s Algorithm       | Shortest Path                       | Shortest path on weighted graphs            |
| Bellman-Ford Algorithm     | Shortest Path                       | Handles negative weights                    |
| Floyd-Warshall Algorithm   | All-Pairs Shortest                  | Finds shortest paths between all nodes      |
| Kruskal’s Algorithm        | MST (Minimum Spanning Tree)         | Greedy MST using edge sorting               |
| Prim’s Algorithm           | MST                                 | Greedy MST using vertex selection           |
| Topological Sort           | Ordering                            | Linear order of DAG vertices                |
| Cycle Detection            | Detection                           | Detect cycles in directed/undirected graphs |
| Kosaraju’s Algorithm       | SCC (Strongly Connected Components) | 2 DFS passes on directed graph              |
| Tarjan’s Algorithm         | SCC                                 | Single DFS pass using low-link values       |

---

## Time and Space Complexity Overview

| Algorithm        | Time Complexity  | Space Complexity                       |
|------------------|------------------|----------------------------------------|
| DFS              | O(V + E)         | O(V) (for recursion stack and visited) |
| BFS              | O(V + E)         | O(V) (for queue and visited)           |
| Dijkstra         | O((V + E) log V) | O(V) (distance array + priority queue) |
| Bellman-Ford     | O(V * E)         | O(V) (distance array)                  |
| Floyd-Warshall   | O(V³)            | O(V²) (distance matrix)                |
| Kruskal          | O(E log E)       | O(V) (for union-find)                  |
| Prim             | O((V + E) log V) | O(V) (priority queue and MST set)      |
| Topological Sort | O(V + E)         | O(V) (stack or queue + visited)        |
| Cycle Detection  | O(V + E)         | O(V) (visited and recursion stack)     |
| Kosaraju         | O(V + E)         | O(V) (stack and visited arrays)        |
| Tarjan           | O(V + E)         | O(V) (stack, visited, low-link arrays) |

*V = Number of vertices, E = Number of edges*

---

---

## Detailed Algorithm Descriptions

### DFS (Depth-First Search)

- Explores as far as possible along each branch before backtracking.
- Useful for pathfinding, cycle detection, topological sorting.

### BFS (Breadth-First Search)

- Explores neighbors level-by-level.
- Used in shortest path for unweighted graphs, and level order traversal.

### Dijkstra’s Algorithm

- Finds shortest path from source to all vertices on weighted graphs without negative weights.

### Bellman-Ford Algorithm

- Finds shortest path with possible negative weights and detects negative cycles.

### Floyd-Warshall Algorithm

- Dynamic programming approach for shortest paths between every pair of vertices.

### Kruskal’s Algorithm

- Builds MST by sorting edges and adding edges avoiding cycles.

### Prim’s Algorithm

- Builds MST by growing tree from a starting vertex.

### Topological Sort

- Orders vertices linearly respecting directed edges (for DAGs).

### Cycle Detection

- Detects if a graph contains cycles (important in dependency graphs).

### Kosaraju’s Algorithm

- Finds SCCs by running DFS twice (original and transposed graph).

### Tarjan’s Algorithm

- Finds SCCs in one DFS pass using low-link values.

---

## Applications

- Network Routing & Connectivity
- Task Scheduling & Build Systems
- Social Network Analysis
- Compiler Optimization & Deadlock Detection
- Geographic Mapping & GPS Systems

---

## References

- [GeeksforGeeks - Graph Algorithms](https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/)
- [CLRS Textbook](https://en.wikipedia.org/wiki/Introduction_to_Algorithms)
- [Tarjan’s Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)
- [Kruskal’s Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm)

---