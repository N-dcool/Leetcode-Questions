/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        //int[] dp = new int[n];
        int prev = nums[0];
        int prev2 = 0;
        
        for(int i=1; i<n; i++){
            int notTake = 0 + prev;
            int take = nums[i];
            if(i>1) take += prev2;
            
            int curr = Math.max(notTake, take);
            prev2 = prev;
            prev = curr;
        }
        
        return prev;
    }
}


/*

=======> TopDown approach 

class Solution {
    Integer[] dp;
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new Integer[n+1];
        
        return helper(n-1, nums);
    }
    
    public int helper(int i, int[] nums){
        if(i==0)
            return nums[0];
        if(i<0)
            return 0;
        if(dp[i]!=null)
            return dp[i];
        
        int notTake = 0 + helper(i-1, nums);
        int take = nums[i] + helper(i-2, nums);
        
        return dp[i] = Math.max(take, notTake);
    }
}


=======> BottomUp approach:


class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        
        for(int i=1; i<n; i++){
            int notTake = 0 + dp[i-1];
            int take = nums[i];
            if(i>1) take += dp[i-2];
            
            dp[i] = Math.max(notTake, take);
        }
        
        return dp[n-1];
    }
}

*/
