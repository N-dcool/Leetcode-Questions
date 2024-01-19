/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Constraints:

1 <= n <= 45
*/

class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return helper(n, dp);
    }
    
    public int helper(int i, int[] dp){
        if(i==0)
            return 1;
        if(i<0)
            return 0;
        if(dp[i]!=0)
            return dp[i];
        
        int oneStep = helper(i-1, dp);
        int twoStep = helper(i-2, dp);
        
        return dp[i] = oneStep + twoStep;
    }
}
