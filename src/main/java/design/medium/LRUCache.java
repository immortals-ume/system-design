package design.medium;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        cache = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LRUCache l = new LRUCache(2);
        l.put(1, 1);
        l.put(2, 2);
        System.out.println(l.get(1));
        l.put(3, 3);
        System.out.println(l.get(2));
        l.put(4, 4);
        System.out.println(l.get(1));
        System.out.println(l.get(3));
        System.out.println(l.get(4));
    }

    public synchronized int get(int key) {
        if (!cache.containsKey(key)) {

            return -1;
        }

        Node node = cache.get(key);
        moveToHead(node);

        return node.value;
    }

    public synchronized void put(int key, int value) {
        if (cache.containsKey(key)) {

            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);

            cache.put(key, newNode);

            addHead(newNode);

            if (cache.size() > capacity) {
                Node n = tail.prev;
                removeNode(n);
                cache.remove(n.key);

            }
        }
    }

    void moveToHead(Node node) {
        removeNode(node);
        addHead(node);
    }

    void addHead(Node node) {

        node.next = head.next;
        node.prev = head;
        head.next.prev = node;

        head.next = node;

    }

    void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class Node {
        private final int key;
        private volatile int value;
        private Node prev;
        private Node next;

        Node(int key, int value) {

            this.key = key;
            this.value = value;
        }
    }
}



