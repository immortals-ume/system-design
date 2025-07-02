package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class BFSMatrix {

    // Directions for neighbors: up, down, left, right
    private static final int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void bfs(int[][] grid, int startRow, int startCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            System.out.println("Visiting: (" + r + ", " + c + ")");

            // Explore neighbors
            for (int[] dir : directions) {
                int newRow = r + dir[0];
                int newCol = c + dir[1];

                // Check boundaries and if already visited
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        !visited[newRow][newCol]) {

                    // If you want, add condition to check cell value here
                    // e.g. grid[newRow][newCol] == 1 for land cells only

                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 0, 1, 1}
        };

        bfs(matrix, 0, 0);
    }
}
