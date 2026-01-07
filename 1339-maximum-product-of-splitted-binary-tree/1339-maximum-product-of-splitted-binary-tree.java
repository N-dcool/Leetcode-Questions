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
    int sub;
    int total = 0;
    public int maxProduct(TreeNode root) {
        
        total = calculateSum(root);
        
        calculateSum(root);
        
        return (int)(res%1000000007);
    }
    
    public int calculateSum(TreeNode root){
        if(root==null)
            return 0;
        
        sub = root.val + calculateSum(root.left) + calculateSum(root.right);
        res = Math.max(res, 1l*sub*(total-sub));
        
        return sub;
    }
}