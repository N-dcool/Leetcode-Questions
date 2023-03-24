/*
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
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetry(root.left, root.right);
    }
    
    public boolean checkSymmetry(TreeNode Left, TreeNode Right){
        //base condition:
        if(Left==null || Right==null)
            return Left == Right;
        
        // do something
        if(Left.val != Right.val)
            return false;
        
        //recursion:
        return checkSymmetry(Left.left, Right.right) && checkSymmetry(Left.right, Right.left);
    }
}
*/
