package design.easy;

class NumArray {
    private final int[] prefixSums;

    public NumArray(int[] nums) {
        prefixSums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);

        // Example tests
        System.out.println(numArray.sumRange(0, 2)); // Output: 1
        System.out.println(numArray.sumRange(2, 5)); // Output: -1
        System.out.println(numArray.sumRange(0, 5)); // Output: -3

        // Constraint edge test: Single element
        NumArray singleElement = new NumArray(new int[]{100});
        System.out.println(singleElement.sumRange(0, 0)); // Output: 100

        // Large input
        int[] big = new int[10_000];
        for (int i = 0; i < big.length; i++) big[i] = 1;
        NumArray bigArray = new NumArray(big);
        System.out.println(bigArray.sumRange(0, 9999)); // Output: 10000
    }

    public int sumRange(int left, int right) {
        return prefixSums[right + 1] - prefixSums[left];
    }
}