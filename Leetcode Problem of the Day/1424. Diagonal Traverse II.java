/*
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

 

Example 1:


Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]
Example 2:


Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i].length <= 105
1 <= sum(nums[i].length) <= 105
1 <= nums[i][j] <= 105
*/

class Solution {
    record Pair(int x, int y) {}
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();
        List<Integer> ans = new ArrayList<>();
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-->0){
                Pair node = q.poll();
                int row = node.x;
                int col = node.y;
                ans.add(nums.get(row).get(col));
                
                if(col==0 && row+1<n) q.add(new Pair(row+1, col));
                if(col+1 < nums.get(row).size()) q.add(new Pair(row, col+1));
            }
        }
        
        int[] res = new int[ans.size()];
        
        int i = 0;
        for(int a : ans)
            res[i++] = a;
        
        return res;
    }
}
