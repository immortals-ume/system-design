package design.easy;

class MyHashMap {
    private static final int SIZE = 1009; // A prime number to reduce collisions
    private final Node[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();

        // Edge test: max key/value
        map.put(1_000_000, 1_000_000);
        System.out.println(map.get(1_000_000)); // should return 1_000_000

        // Test update
        map.put(1_000_000, 42);
        System.out.println(map.get(1_000_000)); // should return 42

        // Remove and get again
        map.remove(1_000_000);
        System.out.println(map.get(1_000_000)); // should return -1

        // Insert 10^4 entries
        for (int i = 0; i < 10_000; i++) {
            map.put(i, i + 1);
        }

        // Check a few
        System.out.println(map.get(9999)); // should return 10_000
        System.out.println(map.get(5000)); // should return 5001
        map.remove(5000);
        System.out.println(map.get(5000)); // should return -1
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new Node(key, value);
            return;
        }

        Node curr = buckets[index];
        while (true) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }
            if (curr.next == null) break;
            curr = curr.next;
        }
        curr.next = new Node(key, value);
    }

    public int get(int key) {
        int index = hash(key);
        Node curr = buckets[index];
        while (curr != null) {
            if (curr.key == key) return curr.value;
            curr = curr.next;
        }
        return -1;
    }

    public void remove(int key) {
        int index = hash(key);
        Node curr = buckets[index];
        if (curr == null) return;

        if (curr.key == key) {
            buckets[index] = curr.next;
            return;
        }

        Node prev = null;
        while (curr != null) {
            if (curr.key == key) {
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    private static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}