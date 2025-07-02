package algorithms.strings;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpMatcher {
    private static final int BASE = 256; // Number of characters in input alphabet
    private static final int MOD = 1_000_000_007; // Large prime to reduce collisions and overflows

    /**
     * Rabin-Karp string matching algorithm.
     *
     * @param text    The main string to search within
     * @param pattern The pattern string to search for
     * @return List of starting indices where pattern is found
     */
    public static List<Integer> rabinKarp(String text, String pattern) {
        List<Integer> result = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();
        if (m > n || m == 0) return result;

        long patternHash = 0, textHash = 0, h = 1;

        // Precompute BASE^(m-1) % MOD for rolling hash
        for (int i = 0; i < m - 1; i++) {
            h = (h * BASE) % MOD;
        }

        // Initial hash values for pattern and first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            textHash = (textHash * BASE + text.charAt(i)) % MOD;
        }

        // Slide the pattern over text one character at a time
        for (int i = 0; i <= n - m; i++) {
            // If hash values match, do character check to confirm
            if (patternHash == textHash && text.substring(i, i + m)
                    .equals(pattern)) {
                result.add(i);
            }

            // Compute rolling hash for next window
            if (i < n - m) {
                textHash = (BASE * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % MOD;
                if (textHash < 0) textHash += MOD; // Ensure non-negative
            }
        }

        return result;
    }

    // Test the function
    public static void main(String[] args) {
        String text = "Banana";
        String pattern = "ana";

        List<Integer> matches = rabinKarp(text, pattern);

        System.out.println("Pattern found at indices: " + matches);
    }
}
