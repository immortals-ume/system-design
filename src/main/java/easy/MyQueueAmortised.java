package easy;

import java.util.Stack;

class MyQueueAmortised {
    private final Stack<Integer> stackIn;
    private final Stack<Integer> stackOut;

    public MyQueueAmortised() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    // Push element x to the back of queue
    public void push(int x) {
        stackIn.push(x);  // Always O(1)
    }

    // Removes the element from the front of queue and returns that element
    public int pop() {
        // Transfer elements only when stackOut is empty
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop()); // O(n) transfer, but only done once
            }
        }
        return stackOut.pop();  // O(1)
    }

    // Get the front element
    public int peek() {
        // Transfer elements only when stackOut is empty
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());  // O(n) transfer, but only done once
            }
        }
        return stackOut.peek();  // O(1)
    }

    // Returns whether the queue is empty
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}
