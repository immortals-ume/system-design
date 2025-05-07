package easy;

import java.util.PriorityQueue;

class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();

        // Add initial numbers, maintaining only the k largest
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val); // add new value

        // Remove smallest if heap size exceeds k
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // Top of the heap is the kth largest
        return minHeap.peek();
    }
}