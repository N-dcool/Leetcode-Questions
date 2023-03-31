/*
Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts. 

For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.

 
Example 1:
    Input: pizza = ["A..","AAA","..."], k = 3
    Output: 3 
    Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.

Example 2:
    Input: pizza = ["A..","AA.","..."], k = 3
    Output: 1

Example 3:
    Input: pizza = ["A..","A..","..."], k = 1
    Output: 1

Constraints:
    1 <= rows, cols <= 50
    rows == pizza.length
    cols == pizza[i].length
    1 <= k <= 10
    pizza consists of characters 'A' and '.' only.
*/

class Solution {
    Integer[][][] dp;
    int n,m;
    int mod = (int)(1e9 + 7);
    public int ways(String[] pizza, int k) {
        n = pizza.length;
        m = pizza[0].length();
        
        int[][] preSum = new int[n + 1][m+1];
        dp = new Integer[k][n][m];
        
        for(int i=n-1; i>=0; i--){
            for(int j=m-1; j>=0; j--){
                preSum[i][j] = (pizza[i].charAt(j) == 'A'? 1 : 0) + preSum[i+1][j] + preSum[i][j+1] - preSum[i+1][j+1];
            }
        }
        
        return dfs(k-1, 0, 0, preSum);
    }
    
    public int dfs(int k, int r, int c, int[][] preSum){
        if(preSum[r][c] == 0) return 0;
        if(k==0) return 1;
        if(dp[k][r][c] != null) return dp[k][r][c];
        
        int ans = 0;
        for(int i=r+1; i<n; i++){
            if(preSum[r][c] - preSum[i][c] > 0)
                ans = (ans + dfs(k-1, i, c, preSum)) % mod;
        }
        for(int j=c+1; j<m; j++){
            if(preSum[r][c] - preSum[r][j]>0)
                ans = (ans + dfs(k-1, r, j, preSum)) % mod;
        }
        
        return dp[k][r][c] = ans;
    }
}
 
