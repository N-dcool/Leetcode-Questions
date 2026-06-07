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
    public TreeNode createBinaryTree(int[][] descriptions) {

        HashSet<Integer> parentSet = new HashSet<>();
        HashSet<Integer> childrenSet = new HashSet<>();
        HashMap<Integer, List<int[]>> parentToChild = new HashMap<>();

        for(int[] des : descriptions) {
            int parent = des[0];
            int[] child = new int[]{des[1], des[2]};
            parentToChild.computeIfAbsent(parent, k-> new ArrayList<>()).add(child);

            parentSet.add(parent);
            parentSet.add(des[1]);
            childrenSet.add(des[1]);
        }
        
        parentSet.removeAll(childrenSet);

        TreeNode root = new TreeNode(parentSet.iterator().next());
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            TreeNode cur = q.poll();

            for(int[] child : parentToChild.getOrDefault(cur.val, Collections.emptyList())){
                int childVal = child[0];
                boolean flag = child[1]==1;

                TreeNode children = new TreeNode(childVal);
                q.add(children);

                if(flag){
                    cur.left = children;
                } else {
                    cur.right = children;
                }
            }
        }

        return root;
    }
}