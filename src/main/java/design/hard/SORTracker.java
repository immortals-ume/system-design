package design.hard;

import java.util.PriorityQueue;

public class SORTracker {

    // Comparator: higher score first, then lexicographically smaller name
    private final PriorityQueue<Location> leftHeap;  // max heap for top i locations
    private final PriorityQueue<Location> rightHeap; // min heap for remaining locations
    private int queryCount;

    public SORTracker() {
        // max-heap: best i locations
        leftHeap = new PriorityQueue<>((a, b) -> {
            if (a.score != b.score) return a.score - b.score;
            return b.name.compareTo(a.name); // reverse lex
        });

        // min-heap: rest of locations
        rightHeap = new PriorityQueue<>((a, b) -> {
            if (a.score != b.score) return b.score - a.score;
            return a.name.compareTo(b.name); // normal lex
        });

        queryCount = 0;
    }

    public void add(String name, int score) {
        Location loc = new Location(name, score);
        leftHeap.offer(loc);

        // Balance: move the worst of the best i into the right heap
        rightHeap.offer(leftHeap.poll());
    }

    public String get() {
        // Increase query count, move one from rightHeap to leftHeap
        queryCount++;
        leftHeap.offer(rightHeap.poll());
        return leftHeap.peek().name;
    }

    private static class Location {
        String name;
        int score;

        Location(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}