package algorithms.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class HeapExample {

    public static void main(String[] args) {

        // 🔸 MIN HEAP (default in Java)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(5);
        minHeap.add(2);
        minHeap.add(8);
        minHeap.add(1);

        System.out.println("Min Heap (Smallest on top):");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // prints in ascending order
        }

        System.out.println("\n");

        // 🔸 MAX HEAP (using custom comparator)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.add(5);
        maxHeap.add(2);
        maxHeap.add(8);
        maxHeap.add(1);

        System.out.println("Max Heap (Largest on top):");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // prints in descending order
        }
    }
}
