/*
Given the root of a binary tree, the value of a target node target, and an integer k, 
return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

 
Example 1:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
    Output: [7,4,1]
    Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
    Input: root = [1], target = 1, k = 3
    Output: []

 
Constraints:
    The number of nodes in the tree is in the range [1, 500].
    0 <= Node.val <= 500
    All the values Node.val are unique.
    target is the value of one of the nodes in the tree.
    0 <= k <= 1000
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if(k==0){
            ans.add(target.val);
            return ans;
        }
        
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        markParents(root, parentMap);
        
        HashSet<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(target);
        visited.add(target);
        
        int d = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            
            if(d==k)
                break;
            
            while(size-->0){
                TreeNode curr = q.poll();
                
                if(curr.left!=null && !visited.contains(curr.left)){
                    visited.add(curr.left);
                    q.offer(curr.left);
                    if(d==k-1)
                    ans.add(curr.left.val);
                }
                if(curr.right!=null && !visited.contains(curr.right)){
                    visited.add(curr.right);
                    q.offer(curr.right);
                    if(d==k-1)
                    ans.add(curr.right.val);
                }
                if(parentMap.containsKey(curr) && !visited.contains(parentMap.get(curr))){
                    visited.add(parentMap.get(curr));
                    q.offer(parentMap.get(curr));
                    if(d==k-1)
                    ans.add(parentMap.get(curr).val);
                }
            }
            
            d++;
        }
        
        
        
        return ans;
    }
    
    public void markParents(TreeNode root, HashMap<TreeNode, TreeNode> map){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-->0){
                TreeNode curr = q.poll();
                
                if(curr.left!=null){
                    q.offer(curr.left);
                    map.put(curr.left, curr);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                    map.put(curr.right, curr);
                }
            }
        }
        
    }
}
