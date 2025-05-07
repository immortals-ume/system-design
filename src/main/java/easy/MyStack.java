package easy;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private final Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    // Push element x onto stack
    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        // Rotate the queue to move the new element to the front
        for (int i = 1; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    // Removes the element on top of the stack and returns it
    public int pop() {
        return queue.poll(); // Front of queue is top of stack
    }

    // Get the top element
    public int top() {
        return queue.peek(); // Front of queue is top of stack
    }

    // Returns whether the stack is empty
    public boolean empty() {
        return queue.isEmpty();
    }
}
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */