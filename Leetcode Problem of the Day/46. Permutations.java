/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
    Input: nums = [0,1]
    Output: [[0,1],[1,0]]

Example 3:
    Input: nums = [1]
    Output: [[1]]
 

Constraints:
    1 <= nums.length <= 6
    -10 <= nums[i] <= 10
    All the integers of nums are unique.
*/

class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        
        helper(0, nums);
    
        return res;
    }
    
    public void helper(int idx, int[] nums){
        if(idx == nums.length-1){
            List<Integer> cur = new ArrayList<>();
            for(int num : nums) cur.add(num);
            res.add(cur);
            return;
        }
        
        for(int i=idx; i < nums.length; i++){
            swap(idx, i, nums);
            helper(idx+1, nums);
            swap(i, idx, nums);
        }
    }
    
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
