package algorithms.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//["startingNode,"ending_node" ,"weight (0,1,-1)"]
//[1,0,2]  [2,3,4]

///
public class bipartite {

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1); // -1 = unvisited

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                color[i] = 0;

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == -1) {
                            color[neighbor] = 1 - color[node];
                            queue.offer(neighbor);
                        } else if (color[neighbor] == color[node]) {
                            return false; // same color on both ends
                        }
                    }
                }
            }
        }
        return true;
    }

}
