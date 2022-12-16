/*
Problem Statement:
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.


Example 1:
    Input: nums = [1,1,1,1,1], target = 3
    Output: 5
    Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
    -1 + 1 + 1 + 1 + 1 = 3
    +1 - 1 + 1 + 1 + 1 = 3
    +1 + 1 - 1 + 1 + 1 = 3
    +1 + 1 + 1 - 1 + 1 = 3
    +1 + 1 + 1 + 1 - 1 = 3

*/


class Solution {
    Integer[][] dp ;
    public int findTargetSumWays(int[] nums, int target) {
        /*
        to get target:
             s1 - s2 = target
        but, s1 + s2 = totSum  
             ------------------
             2s1 = target + totSum
             
        Therefore, subsetSum must be (target + totSum)/2 (:>)  
        
        to have a subsetSum (target + totSum)/2 must me integer 
        ie. subsetSum % 2 == 0
        else return 0 .
        */
        // if(target<0)
        //     return Math.abs(target)==nums[0] ? 1 : 0;
        
        int n = nums.length;
        int totSum = 0;
        for(int num : nums)
            totSum+=num;
        
        //Checking for edge cases
        if(totSum-target<0) return 0;           //if target>totSum not possible return 0. 
        if((totSum-target)%2!=0) return 0;      //nums array should possible to divide into two parts hence newtarget must be even
    
        target = (totSum-target)/2;
        
        dp = new Integer[n+1][target+1];
        
        return helper(n-1, nums, target);
    }
    
    public int helper(int i, int[] nums, int target){
        if(i==0){
            if(nums[0]==0 && target==0)
                return 2;
            else if(target==0 || target==nums[0])
                return 1;
            return 0;
        }
        if(dp[i][target]!=null)
            return dp[i][target];
        
        int notTake = helper(i-1, nums, target);
        int take = 0;
        if(target >= nums[i])
            take = helper(i-1, nums, target-nums[i]);
        
        return dp[i][target] = take + notTake;
    }
}
