package design.easy;

import java.util.LinkedList;
import java.util.Queue;

class MyStackDoubleQueue {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public MyStackDoubleQueue() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // Push element x onto stack
    public void push(int x) {
        queue1.offer(x);  // O(1)
    }

    // Removes the element on the top of the stack and returns that element
    public int pop() {
        // Move all but the last element from queue1 to queue2
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        // The last element in queue1 is the top of the stack
        int top = queue1.poll();

        // Swap the references of the queues
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return top;  // O(n) for the movement of elements, O(1) for the poll
    }

    // Get the top element
    public int top() {
        // Move all but the last element from queue1 to queue2
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        int top = 0;

        // The last element in queue1 is the top of the stack
        if (!queue1.isEmpty())
            top = queue1.peek();

        // Move it to queue2 to maintain the stack order
        queue2.offer(queue1.poll());

        // Swap the references of the queues
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return top;  // O(n) for the movement of elements, O(1) for the peek
    }

    // Returns whether the stack is empty
    public boolean empty() {
        return queue1.isEmpty();
    }
}