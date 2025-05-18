package design.easy;

import java.util.LinkedList;
import java.util.Queue;


class RecentCounter {

    private final Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public static void main(String[] args) {
        RecentCounter rc = new RecentCounter();

        // Edge test: very small value
        System.out.println("Ping at t=1: " + rc.ping(1));

        // Edge test: large t values
        System.out.println("Ping at t=1_000_000_000: " + rc.ping(1_000_000_000));

        // Stress test: simulate 10,000 calls
        RecentCounter stressTest = new RecentCounter();
        int base = 1_000_000_000;
        for (int i = 0; i < 10_000; i++) {
            int t = base + i * 100;  // strictly increasing by 100 ms
            stressTest.ping(t);
        }
        System.out.println("Final size after 10,000 pings: " + stressTest.queue.size());  // should be ≤ 3000/100 = 30
    }

    public int ping(int t) {
        queue.offer(t);
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}


