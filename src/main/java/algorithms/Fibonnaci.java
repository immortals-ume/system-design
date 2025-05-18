package algorithms;

import java.util.logging.Logger;

public class Fibonnaci {

    static Logger log = Logger.getLogger(Fibonnaci.class.getName());

    public static void main(String[] args) {
        Fibonnaci fibonnaci = new Fibonnaci();
        int value = fibonnaci.fib(10, new int[11]);
        log.info(String.valueOf(value));
        log.info(String.valueOf(fibonnaci.fib2(10)));
        log.info(String.valueOf(fibonnaci.fib(10)));
    }

    //recursive
    private int fib(int n) {
        if (n <= 2) return 1;

        return fib(n - 1) + fib(n - 2);
    }


    //top down
    private int fib(int n, int[] memo) {
        if (n <= 2) return 1;
        if (memo[n] != 0) return memo[n];
        return fib(n - 1, memo) + fib(n - 2, memo);
    }

    //bottom up
    private int fib2(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
