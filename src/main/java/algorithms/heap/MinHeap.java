package algorithms.heap;

public class MinHeap {
    private final int[] heap;
    private int size;
    private final int maxSize;

    // Constructor
    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = new int[maxSize + 1];
        heap[0] = Integer.MIN_VALUE; // sentinel at index 0
    }

    // Main function to test
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        minHeap.print(); // shows internal structure

        System.out.println("Removed: " + minHeap.remove()); // 3
        minHeap.print();
    }

    // Get parent, left, right positions
    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    // Check if node is a leaf
    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    // Swap nodes
    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    // Heapify (min-heapify down from pos)
    private void minHeapify(int pos) {
        if (isLeaf(pos)) return;

        int left = leftChild(pos);
        int right = rightChild(pos);

        int smallest = pos;

        if (left <= size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right <= size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != pos) {
            swap(pos, smallest);
            minHeapify(smallest);
        }
    }

    // Insert new element
    public void insert(int element) {
        if (size >= maxSize) {
            System.out.println("Heap is full!");
            return;
        }
        heap[++size] = element;
        int current = size;

        // Bubble up
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Remove and return the min element (root)
    public int remove() {
        if (size == 0) throw new IllegalStateException("Heap is empty!");

        int popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    // Print the heap array
    public void print() {
        System.out.print("Heap Array: ");
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
