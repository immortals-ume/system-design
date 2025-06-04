package algorithms.heap;

public class HeapSort {

    // Heapify subtree rooted at index i (for max heap)
    static void heapify(int[] arr, int size, int i) {
        int largest = i;        // Assume current is largest
        int left = 2 * i + 1;   // Left child
        int right = 2 * i + 2;  // Right child

        // If left child is larger
        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        // If root is not largest, swap and continue heapifying
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, size, largest);
        }
    }

    // Main heap sort function
    static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify reduced heap
            heapify(arr, i, 0);
        }
    }

    // Utility to print array
    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main
    public static void main(String[] args) {
        int[] arr = {5, 3, 10, 1, 7, 6};

        System.out.println("Original array:");
        printArray(arr);

        heapSort(arr);

        System.out.println("Sorted array (Heap Sort):");
        printArray(arr);
    }
}
