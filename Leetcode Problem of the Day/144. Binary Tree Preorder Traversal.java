/*
Given the root of a binary tree, return the preorder traversal of its nodes' values.


Example 1:
    Input: root = [1,null,2,3]
    Output: [1,2,3]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [1]
    Output: [1]
 

Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100
*/

//           morris Methode                   //

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        TreeNode curr = root;
        
        while(curr!=null){
            if(curr.left==null){
                preorder.add(curr.val);
                curr = curr.right;
            }
            else{
                TreeNode pred = curr.left;
                while(pred.right!=null && pred.right!=curr)
                    pred = pred.right;
                
                if(pred.right==null){
                    pred.right = curr;
                    preorder.add(curr.val);
                    curr = curr.left;
                }
                else{
                    pred.right=null;
                    
                    curr = curr.right;
                }
            }
        }
        
        return preorder;
    }
}


//            ittarative  methode             //


class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        List<Integer> ans = new LinkedList<>();
        
        if(root==null) 
            return ans;
        
        s.push(root);
        
        while(!s.isEmpty()){
            TreeNode tree = new TreeNode();
            tree = s.pop();
            
            ans.add(tree.val);
            
            if(tree.right!=null)
                s.push(tree.right);
            
            if(tree.left!=null)
                s.push(tree.left);
            
        }
        
        
        return ans;
    }
}
