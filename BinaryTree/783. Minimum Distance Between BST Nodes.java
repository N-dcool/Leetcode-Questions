/*
Given the root of a Binary Search Tree (BST), return the minimum difference between the 
values of any two different nodes in the tree.

 
Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1

Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1
 

Constraints:
    The number of nodes in the tree is in the range [2, 100].
    0 <= Node.val <= 105
*/


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
    public int minDiffInBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
         
        int min = Integer.MAX_VALUE;
        
        for (int i = 1; i < inorder.size(); i++) {
            min = Math.min(min, inorder.get(i) - inorder.get(i-1));
        }
        
        return min;
    }
    
    public void inorderTraversal(TreeNode root, List<Integer> inorder){
        if(root == null)
            return;
        
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }
}
