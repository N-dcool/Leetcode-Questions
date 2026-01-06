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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int maxLvl = 0;
        int lvl = 1;
        int maxSum = Integer.MIN_VALUE;

        while(!q.isEmpty()){
            int sum = 0;
            int size = q.size();

            while(size-- > 0){
                TreeNode node = q.remove();
                sum += node.val;
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }

            if(sum > maxSum){
                maxSum = sum;
                maxLvl = lvl;
            }

            lvl++;
        }

        return maxLvl;
    }
}