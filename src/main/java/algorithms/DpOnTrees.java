package algorithms;

public class DpOnTrees {

    // Returns an array of two values: [max if not robbed, max if robbed]
    public int[] dfs(TreeNode node) {
        if (node == null) return new int[2];

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int rob = node.val + left[0] + right[0];

        return new int[]{notRob, rob};
    }

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
