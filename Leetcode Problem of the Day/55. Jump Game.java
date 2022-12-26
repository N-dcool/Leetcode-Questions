/*
You are given an integer array nums. You are initially positioned at the array's first index, 
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.


Example 1:
    Input: nums = [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
    Input: nums = [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 
Constraints:
    1 <= nums.length <= 10^4
    0 <= nums[i] <= 10^5
*/


class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        
        for(int i=0; i<nums.length; i++){
            if(reachable < i)
                return false;
            reachable = Math.max(reachable, i+nums[i]);
        }
        return true;
    }
}

//memoisation:

class Solution {
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return canJump(nums, 0, dp);
    }
    
    public boolean canJump(int[] nums, int index, int[] dp) {
        if (index >= nums.length-1) {
            return true;
        } 
        
        if (nums[index] == 0) {
            dp[index] = 0;
            return false;
        }
        
        if (dp[index] != -1){
            return dp[index] == 1;
        } 
        
        int jumps = nums[index];
        for (int i = 1; i <= jumps; i++) {
            if (canJump(nums, index+i, dp)) {
                dp[index] = 1;
                return true;
            }
        }
        
        dp[index] = 0;
        return false;
    }
}
