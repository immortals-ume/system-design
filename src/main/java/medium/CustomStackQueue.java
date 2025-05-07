package medium;

import java.util.*;

class CustomStackQueue {
    private final Deque<Integer> stack;
    private final int maxSize;

    public CustomStackQueue(int maxSize) {
        this.stack = new ArrayDeque<>();
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (stack.size() < maxSize) {
            stack.push(x); // push to front (top of stack)
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.pop(); // remove from front (top of stack)
    }

    public void increment(int k, int val) {
        List<Integer> temp = new ArrayList<>();

        // Pop elements to a temporary list (reverse the stack)
        while (!stack.isEmpty()) {
            temp.add(stack.pop());
        }

        // Reverse the list to get bottom to top order
        Collections.reverse(temp);

        // Increment bottom k elements
        for (int i = 0; i < Math.min(k, temp.size()); i++) {
            temp.set(i, temp.get(i) + val);
        }

        // Reverse again and push back to the stack
        Collections.reverse(temp);
        for (int num : temp) {
            stack.push(num);
        }
    }
}