package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TimeMap {
    // Key -> List of (timestamp, value) pairs
    private final Map<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        List<Pair> list = map.get(key);
        int left = 0;
        int right = list.size() - 1;
        String result = "";

        // Binary search for the largest timestamp <= given timestamp
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).timestamp <= timestamp) {
                result = list.get(mid).value;
                left = mid + 1; // try to find a later one
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // Custom class for timestamp-value pair
    private static class Pair {
        int timestamp;
        String value;

        Pair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
}