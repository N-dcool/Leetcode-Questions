/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    long res = 0;
    int total = 0;
    int MOD = 1000000007;
    public int maxProduct(TreeNode root) {
        total = calculate(root);
        System.out.println(total);
        calculate(root);

        return (int)(res%MOD);
    }

    public int calculate(TreeNode root){
        if(root == null) return 0;

        int sub = root.val + calculate(root.left) + calculate(root.right);

        res = Math.max(res, 1l*sub*(total-sub));

        return sub;
    }
}
