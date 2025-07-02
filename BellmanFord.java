// Bellman‑Ford Algorithm in Java – based on edge list
class BellmanFord {
    static class Edge {
        int src, dest, weight;
        Edge() {}
    }

    int V, E;
    Edge[] edges;

    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < E; i++)
            edges[i] = new Edge();
    }

    void run(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges up to V-1 times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                Edge edge = edges[j];
                if (dist[edge.src] != Integer.MAX_VALUE
                    && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (int j = 0; j < E; j++) {
            Edge edge = edges[j];
            if (dist[edge.src] != Integer.MAX_VALUE
                && dist[edge.src] + edge.weight < dist[edge.dest]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        // Print results
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t\t" + dist[i]);
    }

    public static void main(String[] args) {
        int V = 5, E = 8;
        BellmanFord bf = new BellmanFord(V, E);

        bf.edges[0].src = 0;
        bf.edges[0].dest = 1;
        bf.edges[0].weight = -1;
        bf.edges[1].src = 0; bf.edges[1].dest = 2; bf.edges[1].weight = 4;
        bf.edges[2].src = 1; bf.edges[2].dest = 2; bf.edges[2].weight = 3;
        bf.edges[3].src = 1; bf.edges[3].dest = 3; bf.edges[3].weight = 2;
        bf.edges[4].src = 1; bf.edges[4].dest = 4; bf.edges[4].weight = 2;
        bf.edges[5].src = 3; bf.edges[5].dest = 2; bf.edges[5].weight = 5;
        bf.edges[6].src = 3; bf.edges[6].dest = 1; bf.edges[6].weight = 1;
        bf.edges[7].src = 4; bf.edges[7].dest = 3; bf.edges[7].weight = -3;

        bf.run(0);
    }
}
