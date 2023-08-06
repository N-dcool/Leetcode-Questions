/*
Given two positive integers n and x.

Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, 
the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.

Since the result can be very large, return it modulo 109 + 7.

For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.



Example 1:
    Input: n = 10, x = 2
    Output: 1
    Explanation: We can express n as the following: n = 32 + 12 = 10.
    It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

Example 2:
    Input: n = 4, x = 1
    Output: 2
    Explanation: We can express n in the following ways:
    - n = 41 = 4.
    - n = 31 + 11 = 4.
 

Constraints:
    1 <= n <= 300
    1 <= x <= 5
*/

class Solution {
    int n,x;
    Integer[][] dp;
    int MOD = 1000000007;
    public int numberOfWays(int n, int x) {
        this.n = n;
        this.x = x;
        
        dp = new Integer[301][301];
        
        return helper(1,0);
    }
    
    public int helper(int i, int sum){
        if(sum == n)
            return 1;
        if(sum > n)
            return 0;
        int pow = (int)Math.pow(i,x);
        if(pow > n)
            return 0;
        
        if(dp[i][sum] != null)
            return dp[i][sum];
            
        
        int take = helper(i+1, sum + pow);
        int notTake = helper(i+1, sum);
        
        return dp[i][sum] = (take%MOD + notTake%MOD)%MOD;
    }
}
