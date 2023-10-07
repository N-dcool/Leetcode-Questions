/*
You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:


You should build the array arr which has the following properties:

arr has exactly n integers.
1 <= arr[i] <= m where (0 <= i < n).
After applying the mentioned algorithm to arr, the value search_cost is equal to k.
Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large,
the answer must be computed modulo 109 + 7.

Example 1:
    Input: n = 2, m = 3, k = 1
    Output: 6
    Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]

Example 2:
    Input: n = 5, m = 2, k = 3
    Output: 0
    Explanation: There are no possible arrays that satisify the mentioned conditions.

Example 3:
    Input: n = 9, m = 1, k = 1
    Output: 1
    Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
 

Constraints:
    1 <= n <= 50
    1 <= m <= 100
    0 <= k <= n
*/

class Solution {
    int n,m,k;
    Integer[][][] dp = new Integer[51][51][101];
    public int numOfArrays(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        
        return solve(0,0,0);
    }
    
    public int solve(int idx, int LIS, int max){
        if(idx==n)
            return LIS==k ? 1 : 0;
        if(dp[idx][LIS][max] != null)
            return dp[idx][LIS][max];
        
        long ans = 0;
        
        for(int i=1; i<=m; i++){
            if(i > max){
                ans += solve(idx+1, LIS+1, i);
            }else{
                ans += solve(idx+1, LIS, max);
            }
        }
        
        return dp[idx][LIS][max] = (int)(ans%1000000007);
    }
    
}
