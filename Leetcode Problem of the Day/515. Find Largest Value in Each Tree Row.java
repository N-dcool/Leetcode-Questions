/*
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:
    Input: root = [1,3,2,5,3,null,9]
    Output: [1,3,9]

Example 2:
    Input: root = [1,2,3]
    Output: [1,3]
 

Constraints:
    The number of nodes in the tree will be in the range [0, 104].
    -231 <= Node.val <= 231 - 1
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            int max = Integer.MIN_VALUE;
            while(size-->0){
                TreeNode curr = q.poll();
                max = Math.max(max, curr.val);
                
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
            
            res.add(max);
        }
        
        return res;
    }
}
