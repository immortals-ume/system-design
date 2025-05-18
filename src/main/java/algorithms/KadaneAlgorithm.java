package algorithms;

public class KadaneAlgorithm {

    public static int maxSubArraySum(int[] nums) {
        int maxSum = nums[0];           // Overall max sum
        int currentSum = nums[0];       // Sum ending at current index

        for (int i = 1; i < nums.length; i++) {
            // Either start a new subarray at i or extend previous one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum is: " + maxSubArraySum(nums));
    }
}

