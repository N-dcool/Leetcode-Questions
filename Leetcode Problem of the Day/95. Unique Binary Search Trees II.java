/*
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of 
unique values from 1 to n. Return the answer in any order.

 

Example 1:
    Input: n = 3
    Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
    Input: n = 1
    Output: [[1]]
 

Constraints:
    1 <= n <= 8
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
    public List<TreeNode> generateTrees(int n) {
        
        return allPossibleBST(1,n);
    }
    
    public List<TreeNode> allPossibleBST(int left, int right){
        if(left==right){
            List<TreeNode> cur = new ArrayList<>();
            cur.add(new TreeNode(left));
            return cur;
        }
        if(left > right){
            List<TreeNode> cur = new ArrayList<>();
            cur.add(null);
            return cur;
        }
        
        List<TreeNode> res = new ArrayList<>();
        
        for(int i=left; i<=right; i++){
            for(TreeNode l : allPossibleBST(left,i-1)){
                for(TreeNode r : allPossibleBST(i+1, right)){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    
                    res.add(root);
                }
            }
        }
        return res;
    }
}
