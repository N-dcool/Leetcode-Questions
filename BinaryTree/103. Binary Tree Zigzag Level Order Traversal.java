/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
(i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[20,9],[15,7]]

Example 2:
    Input: root = [1]
    Output: [[1]]

Example 3:
    Input: root = []
    Output: []
 

Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -100 <= Node.val <= 100
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null)
            return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        boolean flag = true;
        
        q.offer(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> lvl = new ArrayList<>();
            
            for(int i=0; i<size ; i++){
                TreeNode node = q.poll();
                
                if(flag)
                    lvl.add(node.val);
                else
                    lvl.add(0,node.val);
     
                
                
                if(node.left!=null)
                    q.offer(node.left);
                if(node.right!=null)
                    q.offer(node.right);
                
            }
            flag = !flag;
            res.add(lvl);
            
        }
        return res;
    }
}
