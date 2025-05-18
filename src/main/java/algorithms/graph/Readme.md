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