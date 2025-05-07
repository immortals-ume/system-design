package easy;

import java.util.Stack;

class MyQueue {
    private final Stack<Integer> stackIn;
    private final Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public static void main(String[] args) {
        String[] operations = {"MyQueue", "push", "push", "peek", "pop", "empty"};
        int[][] inputs = {{}, {1}, {2}, {}, {}, {}};

        MyQueue myQueue = null;

        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "MyQueue" -> {
                    myQueue = new MyQueue();
                    System.out.println("null");
                }
                case "push" -> {
                    int val = inputs[i][0];
                    myQueue.push(val);
                    System.out.println("null");
                }
                case "peek" -> System.out.println(myQueue.peek());
                case "pop" -> System.out.println(myQueue.pop());
                case "empty" -> System.out.println(myQueue.empty());
            }
        }
    }

    // Push element x to the back of queue
    public void push(int x) {
        stackIn.push(x);
    }

    // Removes the element from in front of queue and returns that element
    public int pop() {
        moveInToOutIfNeeded();
        return stackOut.pop();
    }

    // Get the front element
    public int peek() {
        moveInToOutIfNeeded();
        return stackOut.peek();
    }

    // Returns whether the queue is empty
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // Helper to shift elements only when stackOut is empty
    private void moveInToOutIfNeeded() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }
}