package algorithms;

import java.util.Arrays;
import java.util.logging.Logger;

public class Knapsack01 {

    static Logger log = Logger.getLogger(Knapsack01.class.getName());

    public static void main(String[] args) {
        int[] value = {60, 50, 70, 40};
        int[] weight = {6, 4, 5, 3};
        int capacity = 10;
        int n = value.length;

        int[][] memo = new int[n + 1][capacity + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int finalValueMemo = knapsack(capacity, weight, value, n, memo);
        log.info(String.valueOf(finalValueMemo));

    }

    //recursive solution
    public static int knapsack(int W, int[] weight, int[] value, int n) {
        //if there are no items, no bag
        if (n == 0 || W == 0) return 0;

        // If the weight of the current item is more than the remaining capacity, we can't include it
        if (weight[n - 1] > W) {
            return knapsack(W, weight, value, n - 1);
        } else {
            // Recursively decide whether to include the current item or not
            int includeItem = value[n - 1] + knapsack(W - weight[n - 1], weight, value, n - 1);
            int excludeItem = knapsack(W, weight, value, n - 1);
            return Math.max(includeItem, excludeItem);
        }
    }

    //top-down approach
    public static int knapsack(int W, int[] weight, int[] value, int n, int[][] memo) {
        //if there are no items, no bag
        if (n == 0 || W == 0) return 0;

        // If already calculated, return the stored value
        if (memo[n][W] != -1) {
            return memo[n][W];
        }

        // If the weight of the current item is more than the remaining capacity, we can't include it
        if (weight[n - 1] > W) {
            memo[n][W] = knapsack(W, weight, value, n - 1, memo);
        } else {
            // Recursively decide whether to include the current item or not
            int includeItem = value[n - 1] + knapsack(W - weight[n - 1], weight, value, n - 1, memo);
            int excludeItem = knapsack(W, weight, value, n - 1, memo);
            memo[n][W] = Math.max(includeItem, excludeItem);
        }
        return memo[n][W];
    }

    //bottom-up approach
    public static int knapSack(int W, int[] weight, int[] value, int n) {
        // Create a 2D table to store the maximum value for each weight and number of items
        int[][] dp = new int[n + 1][W + 1];

        // Build the table in a bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or no capacity
                } else if (weight[i - 1] <= w) {
                    // Include the item or exclude it
                    dp[i][w] = Math.max(value[i - 1] + dp[i - 1][w - weight[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w]; // Item can't be included
                }
            }
        }

        return dp[n][W]; // The maximum value is stored in dp[n][W]
    }

}
