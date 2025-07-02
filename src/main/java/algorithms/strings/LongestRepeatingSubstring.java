package algorithms.strings;

import java.util.HashSet;
import java.util.Set;

public class LongestRepeatingSubstring {

    private static final int BASE = 256;
    private static final int MOD = 1_000_000_007;

    public static String longestRepeatingSubstring(String s) {
        int left = 1, right = s.length();
        String result = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String dup = getDuplicate(s, mid);

            if (dup != null) {
                result = dup;
                left = mid + 1; // try to find a longer one
            } else {
                right = mid - 1; // no repeat of this length
            }
        }

        return result;
    }

    // Rabin-Karp check for duplicate substring of given length
    private static String getDuplicate(String s, int len) {
        long hash = 0, h = 1;
        Set<Long> seen = new HashSet<>();

        for (int i = 0; i < len - 1; i++) {
            h = (h * BASE) % MOD;
        }

        for (int i = 0; i < len; i++) {
            hash = (hash * BASE + s.charAt(i)) % MOD;
        }

        seen.add(hash);

        for (int i = 1; i <= s.length() - len; i++) {
            hash = (BASE * (hash - s.charAt(i - 1) * h) + s.charAt(i + len - 1)) % MOD;
            if (hash < 0) hash += MOD;

            if (seen.contains(hash)) {
                return s.substring(i, i + len);
            }
            seen.add(hash);
        }

        return null;
    }

    public static void main(String[] args) {
        String input = "banana";
        System.out.println("Longest Repeating Substring: " + longestRepeatingSubstring(input));
    }
}
