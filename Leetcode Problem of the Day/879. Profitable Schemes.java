/*
There is a group of n members, and a list of various crimes they could commit. 
The ith crime generates a profit[i] and requires group[i] members to participate in it.
If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, 
and the total number of members participating in that subset of crimes is at most n.

Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.

 
Example 1:
    Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
    Output: 2
    Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
    In total, there are 2 schemes.

Example 2:
    Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
    Output: 7
    Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
    There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 

Constraints:
    1 <= n <= 100
    0 <= minProfit <= 100
    1 <= group.length <= 100
    1 <= group[i] <= 100
    profit.length == group.length
    0 <= profit[i] <= 100
*/

class Solution {
    Integer[][][] dp;
    int mod = 1000000007;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        dp = new Integer[m+1][n+1][minProfit+1];
        
        return helper(m-1, n, minProfit, group, profit);
    }
    
    public int helper(int m, int n, int minProfit, int[] group, int[] profit){
        // base condition:
        if(m==-1){
            return minProfit <= 0 ? 1 : 0;
        }
        if(dp[m][n][minProfit]!=null)
            return dp[m][n][minProfit];
        
        // do something:
        long take = 0;
        if(n-group[m] >=0)
            take += helper(m-1, n-group[m], Math.max(minProfit-profit[m], 0), group, profit);
        long notTake = helper(m-1, n, minProfit, group, profit);
        
        // return recursion:
        
        return dp[m][n][minProfit] = (int)((take + notTake)%mod);
    }
}
