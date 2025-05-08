package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {

    private final Map<String, Integer> keyCount;
    private final Map<Integer, Bucket> countBucket;
    private final Bucket head;
    private final Bucket tail;

    public AllOne() {
        keyCount = new HashMap<>();
        countBucket = new HashMap<>();

        head = new Bucket(-1); // dummy head
        tail = new Bucket(-1); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        int count = keyCount.getOrDefault(key, 0);
        keyCount.put(key, count + 1);

        Bucket currBucket = countBucket.get(count);
        Bucket newBucket = countBucket.get(count + 1);

        if (newBucket == null) {
            newBucket = new Bucket(count + 1);
            countBucket.put(count + 1, newBucket);
            insertBucketAfter(newBucket, currBucket == null ? head : currBucket);
        }

        newBucket.keys.add(key);

        if (currBucket != null) {
            currBucket.keys.remove(key);
            if (currBucket.keys.isEmpty()) {
                removeBucket(currBucket);
                countBucket.remove(count);
            }
        }
    }

    public void dec(String key) {
        int count = keyCount.get(key);
        Bucket currBucket = countBucket.get(count);

        if (count == 1) {
            keyCount.remove(key);
        } else {
            keyCount.put(key, count - 1);
            Bucket newBucket = countBucket.get(count - 1);
            if (newBucket == null) {
                newBucket = new Bucket(count - 1);
                countBucket.put(count - 1, newBucket);
                insertBucketAfter(newBucket, currBucket.prev);
            }
            newBucket.keys.add(key);
        }

        currBucket.keys.remove(key);
        if (currBucket.keys.isEmpty()) {
            removeBucket(currBucket);
            countBucket.remove(count);
        }
    }

    public String getMaxKey() {
        return tail.prev != head && !tail.prev.keys.isEmpty() ? tail.prev.keys.iterator().next() : "";
    }

    public String getMinKey() {
        return head.next != tail && !head.next.keys.isEmpty() ? head.next.keys.iterator().next() : "";
    }

    private void insertBucketAfter(Bucket newBucket, Bucket prevBucket) {
        newBucket.prev = prevBucket;
        newBucket.next = prevBucket.next;
        prevBucket.next.prev = newBucket;
        prevBucket.next = newBucket;
    }

    private void removeBucket(Bucket bucket) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
    }

    private static class Bucket {
        int count;
        Set<String> keys;
        Bucket prev;
        Bucket next;

        Bucket(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }
}