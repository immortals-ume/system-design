package design.easy;

import java.util.Stack;

class MyQueueSingleStack {
    private final Stack<Integer> stack;

    public MyQueueSingleStack() {
        stack = new Stack<>();
    }

    // Push element x to the back of queue
    public void push(int x) {
        stack.push(x); // O(1) operation
    }

    // Removes the element from in front of queue and returns that element
    public int pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return popHelper(stack);
    }

    // Helper function to reverse the stack and pop the front element
    private int popHelper(Stack<Integer> stack) {
        // Base case: if only one element is left, return it
        if (stack.size() == 1) {
            return stack.pop();
        }
        // Recursively pop elements
        int top = stack.pop();
        int res = popHelper(stack);
        stack.push(top); // Put the elements back after popping the front one
        return res;
    }

    // Get the front element
    public int peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return peekHelper(stack);
    }

    // Helper function to reverse the stack and peek the front element
    private int peekHelper(Stack<Integer> stack) {
        // Base case: if only one element is left, return it
        if (stack.size() == 1) {
            return stack.peek();
        }
        // Recursively pop elements
        int top = stack.pop();
        int res = peekHelper(stack);
        stack.push(top); // Put the elements back after peeking the front one
        return res;
    }

    // Returns whether the queue is empty
    public boolean empty() {
        return stack.isEmpty();
    }
}