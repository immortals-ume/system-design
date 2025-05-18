package design.easy;

import java.util.HashMap;
import java.util.Map;

public class NeighborSum {
    private final int[][] grid;
    private final Map<Integer, int[]> valueToPosition;

    public NeighborSum(int[][] grid) {
        this.grid = grid;
        this.valueToPosition = new HashMap<>();
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                valueToPosition.put(grid[i][j], new int[]{i, j});
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };

        NeighborSum ns = new NeighborSum(grid);
        System.out.println(ns.adjacentSum(1)); // Output: 6
        System.out.println(ns.adjacentSum(4)); // Output: 16
        System.out.println(ns.diagonalSum(4)); // Output: 16
        System.out.println(ns.diagonalSum(8)); // Output: 4

        int n = 10; // max size for stress test
        int[][] grid1 = new int[n][n];

        // Fill grid with distinct values from 0 to n^2 - 1
        int val = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid1[i][j] = val++;
            }
        }

        NeighborSum ns1 = new NeighborSum(grid1);

        // Make n^2 calls to adjacentSum and diagonalSum (max 2*n^2 total calls)
        int totalCalls = 0;
        for (int i = 0; i < n * n; i++) {
            int adj = ns1.adjacentSum(i);
            int diag = ns1.diagonalSum(i);
            System.out.println("Value: " + i + ", Adjacent Sum: " + adj + ", Diagonal Sum: " + diag);
            totalCalls += 2;
        }

        System.out.println("Total calls made: " + totalCalls); // Should be 200 for n = 10
    }

    public int adjacentSum(int value) {
        int[] pos = valueToPosition.get(value);
        int i = pos[0], j = pos[1];
        int sum = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right

        for (int[] dir : directions) {
            int ni = i + dir[0], nj = j + dir[1];
            if (isInBounds(ni, nj)) {
                sum += grid[ni][nj];
            }
        }
        return sum;
    }

    public int diagonalSum(int value) {
        int[] pos = valueToPosition.get(value);
        int i = pos[0], j = pos[1];
        int sum = 0;
        int[][] diagonals = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // top-left, top-right, bottom-left, bottom-right

        for (int[] dir : diagonals) {
            int ni = i + dir[0], nj = j + dir[1];
            if (isInBounds(ni, nj)) {
                sum += grid[ni][nj];
            }
        }
        return sum;
    }

    private boolean isInBounds(int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
    }
}