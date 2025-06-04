package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseSimplify {
    private int minTransactions = Integer.MAX_VALUE;

    // Simple test
    public static void main(String[] args) {
        SplitwiseSimplify solver = new SplitwiseSimplify();

        // Example: A=0, B=1, C=2
        // Transactions: A paid 10 for B, B paid 20 for C, C paid 5 for A
        int[][] transactions = {
                {0, 1, 10},
                {1, 2, 20},
                {2, 0, 5}
        };

        int result = solver.minTransfers(transactions);
        System.out.println("Minimum transactions needed: " + result);
        // Expected output: 2
    }

    /**
     * Given a list of transactions [payer, payee, amount],
     * returns the minimum number of transactions to settle all debts.
     */
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> balanceMap = new HashMap<>();

        // Step 1: Calculate net balances for each person
        for (int[] t : transactions) {
            int payer = t[0], payee = t[1], amount = t[2];
            balanceMap.put(payer, balanceMap.getOrDefault(payer, 0) - amount);
            balanceMap.put(payee, balanceMap.getOrDefault(payee, 0) + amount);
        }

        // Step 2: Extract non-zero balances into a list
        List<Integer> debts = new ArrayList<>();
        for (int bal : balanceMap.values()) {
            if (bal != 0) debts.add(bal);
        }

        // Step 3: Start backtracking from the first unsettled account
        backtrack(debts, 0, 0);

        return minTransactions;
    }

    /**
     * Backtracking helper method:
     * - Tries settling debts recursively starting from 'start' index.
     * - 'count' tracks the number of transactions so far.
     */
    private void backtrack(List<Integer> debts, int start, int count) {
        // Skip settled debts (zero balance)
        while (start < debts.size() && debts.get(start) == 0) {
            start++;
        }

        // All debts settled
        if (start == debts.size()) {
            minTransactions = Math.min(minTransactions, count);
            return;
        }

        int currentDebt = debts.get(start);
        // Try settling currentDebt with subsequent opposite-signed debts
        for (int i = start + 1; i < debts.size(); i++) {
            int nextDebt = debts.get(i);

            // Only settle if opposite signs (one owes, one is owed)
            if (currentDebt * nextDebt < 0) {
                // Settle debt[start] with debt[i]
                debts.set(i, nextDebt + currentDebt);

                // Recurse to settle next debts
                backtrack(debts, start + 1, count + 1);

                // Backtrack: undo settlement
                debts.set(i, nextDebt);
            }
        }
    }
}
