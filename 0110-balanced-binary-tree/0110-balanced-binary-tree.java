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
    boolean res = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        check(root);

        return res;
    }

    public int check(TreeNode root){
        if(root == null) return 0;

        int left = 1 + check(root.left);
        int right = 1 + check(root.right);

        if(Math.abs(left-right) > 1){
            res = false;
            return -1;
        }

        return Math.max(left, right);
    }
}