package algorithms;

import java.util.Arrays;
import java.util.logging.Logger;

public class LongestCommonSubsequence {

    static Logger log = Logger.getLogger(LongestCommonSubsequence.class.getName());

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(lcs(s1, s2, s1.length(), s2.length()));

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int result = lcsMemo(s1, s2, s1.length(), s2.length(), dp);

        System.out.println(result);
    }

    // Recursive
    //	•	Compare the last letters.
    //	•	If they match → +1 and recurse on smaller substrings.
    //	•	If not → recurse by skipping one letter from either string and take the max.
    public static int lcs(String s1, String s2, int i, int j) {

        if (i == 0 || j == 0) return 0;

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return 1 + lcs(s1, s2, i - 1, j - 1);
        } else {
            return Math.max(lcs(s1, s2, i - 1, j), lcs(s1, s2, i, j - 1));
        }
    }

    //Top-Down Approach
    public static int lcsMemo(String s1, String s2, int i, int j, int[][] dp) {
        if (i == 0 || j == 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            dp[i][j] = 1 + lcsMemo(s1, s2, i - 1, j - 1, dp);
        } else {
            dp[i][j] = Math.max(
                    lcsMemo(s1, s2, i - 1, j, dp),
                    lcsMemo(s1, s2, i, j - 1, dp)
            );
        }

        return dp[i][j];
    }

    //Bottom Up
    public int lcsBottomUp(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

}
