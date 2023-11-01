/*
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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
    HashMap<Integer,Integer> map;
    public int[] findMode(TreeNode root) {
        map = new HashMap<>();
        
        dfs(root);
        
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            pq.add(e);
        }
        
        int max = pq.peek().getValue();
        
        List<Integer> res = new ArrayList<>();
        while(!pq.isEmpty() && pq.peek().getValue() == max){
            res.add(pq.poll().getKey());
        }
        
        int n = res.size();
        int[] ans = new int[n];
        for(int i=0; i<n; i++)
            ans[i] = res.get(i);
        return ans;
    }
    
    public void dfs(TreeNode root){
        if(root == null) return;
        
        dfs(root.left);
        map.put(root.val, map.getOrDefault(root.val,0)+1);
        dfs(root.right);
        
    }
}
