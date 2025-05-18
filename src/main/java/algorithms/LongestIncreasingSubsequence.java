package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] prices = new int[]{10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(lis(prices, 0, -1));

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = nums.length;
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int result = lisMemo(nums, 0, -1, dp);
        System.out.println(result);
    }

    public static int lis(int[] nums, int curr, int prev) {
        if (curr == nums.length) return 0;

        // Not pick
        int notPick = lis(nums, curr + 1, prev);

        // Pick
        int pick = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            pick = 1 + lis(nums, curr + 1, curr);
        }

        return Math.max(pick, notPick);
    }

    public static int lisMemo(int[] nums, int curr, int prev, int[][] dp) {
        if (curr == nums.length) return 0;
        if (dp[curr][prev + 1] != -1) return dp[curr][prev + 1];

        int notPick = lisMemo(nums, curr + 1, prev, dp);
        int pick = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            pick = 1 + lisMemo(nums, curr + 1, curr, dp);
        }

        return dp[curr][prev + 1] = Math.max(pick, notPick);
    }

    //Bottom-Up Approach
    // 1. Initialize an array dp[i] to store the length of the LIS ending at nums[i].
    // 2. Initialize the result variable to 1.
    // 3. For each number of nums[i] in the array, check if nums[i] is greater than nums[j] where j < i.
    // 4. If nums[i] is greater than nums[j], then update dp[i] by adding 1 to the length of the LIS ending at nums[j].
    // 5. Update the result variable with the maximum of dp[i] and result.
    // 6. Return the result variable.
    // Time Complexity: O(n^2)
    public int lisBottomUp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Every number is an LIS of at least 1

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for (int val : dp) {
            maxLen = Math.max(maxLen, val);
        }

        return maxLen;
    }

    public int lisBinarySearch(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int num : nums) {
            int idx = Collections.binarySearch(tails, num);
            if (idx < 0) idx = -(idx + 1);

            if (idx == tails.size()) {
                tails.add(num);
            } else {
                tails.set(idx, num);
            }
        }

        return tails.size();
    }

}
