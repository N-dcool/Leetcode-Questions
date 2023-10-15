/*
You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).

Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. 
Since the answer may be too large, return it modulo 109 + 7.

 
Example 1:
    Input: steps = 3, arrLen = 2
    Output: 4
    Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
    Right, Left, Stay
    Stay, Right, Left
    Right, Stay, Left
    Stay, Stay, Stay

Example 2:
    Input: steps = 2, arrLen = 4
    Output: 2
    Explanation: There are 2 differents ways to stay at index 0 after 2 steps
    Right, Left
    Stay, Stay

Example 3:
    Input: steps = 4, arrLen = 2
    Output: 8
     

Constraints:
    1 <= steps <= 500
    1 <= arrLen <= 106
*/

class Solution {
    int MOD = (int)(1e9+7);
    Integer[][] dp;
    public int numWays(int steps, int arrLen) {
        arrLen = Math.min(arrLen, steps);
        dp = new Integer[arrLen+1][steps+1];
        return solve(0, steps, arrLen);
    }
    
    public int solve(int i, int steps, int n){
        if(i<0 || i>=n) return 0;
        if(steps==0)
            return i==0 ? 1 : 0;
        if(dp[i][steps] != null)
            return dp[i][steps];
        
        long a = solve(i, steps-1, n);
        long b = solve(i+1, steps-1, n);
        long c = solve(i-1, steps-1, n);
        
        return dp[i][steps] = (int)((a+b+c)%MOD);
    }
}
