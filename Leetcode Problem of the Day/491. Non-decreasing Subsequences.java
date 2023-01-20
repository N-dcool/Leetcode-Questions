/*
Given an integer array nums, return all the different possible non-decreasing 
subsequences of the given array with at least two elements. You may return the answer in any order.


Example 1:
    Input: nums = [4,6,7,7]
    Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

Example 2:
    Input: nums = [4,4,3,2,1]
    Output: [[4,4]]
 
Constraints:
    1 <= nums.length <= 15
    -100 <= nums[i] <= 100
*/

class Solution {
    HashSet<List<Integer>> ans;
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        ans = new HashSet<>();
        
        helper(0, nums, new ArrayList<>());
        
        //System.out.print(ans);
        
        for(List<Integer> entry : ans){
            //System.out.println(entry);
            result.add(new ArrayList(entry));
        }
        
        return result;
    }
    
    public void helper(int idx, int[] nums,List<Integer> list){
        if(idx==nums.length){
            // System.out.println(list);
            if(list.size()>1)
                ans.add(new ArrayList(list));
            return;
        }
        if(list.isEmpty() || list.get(list.size()-1)<=nums[idx]){
            list.add(nums[idx]);
            helper(idx+1, nums, list);
            list.remove(list.size()-1);
            
        }
        helper(idx+1, nums, list);
    }
}
