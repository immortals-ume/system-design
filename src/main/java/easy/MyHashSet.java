package easy;

class MyHashSet {
    private static final int SIZE = 1009; // A prime number for better hashing
    private final Node[] buckets;

    public MyHashSet() {
        buckets = new Node[SIZE];
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();

        // Test edge: key = 10^6
        set.add(1_000_000);
        System.out.println(set.contains(1_000_000)); // true

        // Remove and verify
        set.remove(1_000_000);
        System.out.println(set.contains(1_000_000)); // false

        // Insert 10^4 keys
        for (int i = 0; i < 10_000; i++) {
            set.add(i);
        }

        // Verify specific keys
        System.out.println(set.contains(0));     // true
        System.out.println(set.contains(9999));  // true
        set.remove(5000);
        System.out.println(set.contains(5000));  // false

        // Duplicate add test
        set.add(9999);
        System.out.println(set.contains(9999));  // true (still true)
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void add(int key) {
        int index = hash(key);
        Node curr = buckets[index];
        while (curr != null) {
            if (curr.key == key) return; // Already exists
            curr = curr.next;
        }
        Node node = new Node(key);
        node.next = buckets[index];
        buckets[index] = node;
    }

    public void remove(int key) {
        int index = hash(key);
        Node curr = buckets[index];
        Node prev = null;

        while (curr != null) {
            if (curr.key == key) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public boolean contains(int key) {
        int index = hash(key);
        Node curr = buckets[index];

        while (curr != null) {
            if (curr.key == key) return true;
            curr = curr.next;
        }
        return false;
    }

    private static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
        }
    }
}