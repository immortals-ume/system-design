package algorithms.strings;

import java.util.HashSet;
import java.util.Set;

public class RepeatedSubstrings {
    private static final int BASE = 256;
    private static final int MOD = 1_000_000_007;

    public static Set<String> findRepeatedSubstrings(String s, int k) {
        Set<Long> seenHashes = new HashSet<>();
        Set<String> result = new HashSet<>();

        if (s.length() < k || k <= 0) return result;

        long hash = 0, h = 1;
        for (int i = 0; i < k - 1; i++) {
            h = (h * BASE) % MOD;
        }

        // Compute hash for first window
        for (int i = 0; i < k; i++) {
            hash = (hash * BASE + s.charAt(i)) % MOD;
        }

        seenHashes.add(hash);

        for (int i = 1; i <= s.length() - k; i++) {
            // Slide the window
            hash = (BASE * (hash - s.charAt(i - 1) * h) + s.charAt(i + k - 1)) % MOD;
            if (hash < 0) hash += MOD;

            String sub = s.substring(i, i + k);
            if (seenHashes.contains(hash)) {
                result.add(sub); // Only add confirmed repeating substring
            } else {
                seenHashes.add(hash);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String input = "banana";
        int k = 3;
        System.out.println("Repeated substrings of length " + k + ": " + findRepeatedSubstrings(input, k));
    }
}
