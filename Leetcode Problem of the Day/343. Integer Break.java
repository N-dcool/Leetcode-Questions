/*
Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

Example 1:
    Input: n = 2
    Output: 1
    Explanation: 2 = 1 + 1, 1 × 1 = 1.

Example 2:
    Input: n = 10
    Output: 36
    Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 
Constraints:
    2 <= n <= 58
*/

class Solution {
    Integer[] dp = new Integer[60];
    public int integerBreak(int n) {
        if(n==1)
            return 1;
        if(dp[n]!=null)
            return dp[n];
        int max = 0;
        for(int i=1; i<n; i++){
            max = Math.max(max, Math.max(i*(n-i), i*integerBreak(n-i)));
        }
        
        return dp[n] = max;
    }
}
