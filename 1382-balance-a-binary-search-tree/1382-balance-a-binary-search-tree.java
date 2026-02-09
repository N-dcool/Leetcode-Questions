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
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();


        inorderTrversel(root, inorder);

        return createBalanceBST(inorder, 0, inorder.size()-1);

    }

    public TreeNode createBalanceBST(List<Integer> inorder, int l, int r){
        if(l > r) return null;

        int mid = (l+r)/2;

        TreeNode left = createBalanceBST(inorder, l, mid-1);
        TreeNode right = createBalanceBST(inorder, mid+1, r);

        return new TreeNode(inorder.get(mid), left, right);
    }

    public void inorderTrversel(TreeNode root, List<Integer> inorder){
        if(root == null) return;

        inorderTrversel(root.left, inorder);
        inorder.add(root.val);
        inorderTrversel(root.right, inorder);
    }
}