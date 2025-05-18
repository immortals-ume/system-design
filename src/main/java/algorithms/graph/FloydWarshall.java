package algorithms.graph;

public class FloydWarshall {
    final static int INF = Integer.MAX_VALUE; // Use a big number to represent "infinity"
    final static int V = 4; // Number of vertices (places)

    public static void floydWarshall(int[][] graph) {
        // Step 1: Create a distance matrix and copy graph values
        int[][] dist = new int[V][V];

        // Copy initial values (direct distances)
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, V);
        }

        // Step 2: Try all possible intermediate nodes one by one
        for (int k = 0; k < V; k++) {
            // Pick all source nodes one by one
            for (int i = 0; i < V; i++) {
                // Pick all destination nodes one by one
                for (int j = 0; j < V; j++) {
                    // If going through vertex k is shorter, update it
                    if (dist[i][k] != INF && dist[k][j] != INF
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Step 3: Print final shortest distances
        printSolution(dist);
    }

    static void printSolution(int[][] dist) {
        System.out.println("Shortest distances between every pair of places:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Input graph as adjacency matrix
        // INF means no direct road between places
        int[][] graph = {
                {0, 3, INF, 7},
                {8, 0, 2, INF},
                {5, INF, 0, 1},
                {2, INF, INF, 0}
        };

        floydWarshall(graph);
    }
}
