package algorithms;

import java.util.Arrays;
import java.util.logging.Logger;

public class UnboundKnapSack {

    static Logger log = Logger.getLogger(UnboundKnapSack.class.getName());

    public static void main(String[] args) {
        int[] value = {60, 50, 70, 40, 54};
        int[] weight = {6, 4, 5, 3, 9};
        int capacity = 10;
        int n = value.length;

        int[][] memo = new int[n + 1][capacity + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int finalValueMemo = unboundedKnapsackRecursive(capacity, weight, value, n);
        int f = unboundedKnapsackMemo(weight, value, capacity, n, memo);
        log.info(String.valueOf(finalValueMemo));
        log.info(String.valueOf(f));

    }

    public static int unboundedKnapsackRecursive(int cap, int[] wt, int[] val, int n) {
        if (n == 0 || cap == 0) return 0;

        if (wt[n - 1] <= cap) {
            // You can take it multiple times → don't reduce `n`
            return Math.max(
                    val[n - 1] + unboundedKnapsackRecursive(cap - wt[n - 1], wt, val, n),
                    unboundedKnapsackRecursive(cap, wt, val, n - 1)
            );
        } else {
            return unboundedKnapsackRecursive(cap, wt, val, n - 1);
        }
    }

    public static int unboundedKnapsackMemo(int[] wt, int[] val, int cap, int n, int[][] dp) {
        if (n == 0 || cap == 0) return 0;

        if (dp[n][cap] != -1) return dp[n][cap];

        if (wt[n - 1] <= cap) {
            dp[n][cap] = Math.max(
                    val[n - 1] + unboundedKnapsackMemo(wt, val, cap - wt[n - 1], n, dp),
                    unboundedKnapsackMemo(wt, val, cap, n - 1, dp)
            );
        } else {
            dp[n][cap] = unboundedKnapsackMemo(wt, val, cap, n - 1, dp);
        }

        return dp[n][cap];
    }

    public static int unboundedKnapsackTab(int[] wt, int[] val, int cap) {
        int n = wt.length;
        int[][] dp = new int[n + 1][cap + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= cap; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i][w - wt[i - 1]],  // stay at same i (re-use)
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][cap];
    }
}
