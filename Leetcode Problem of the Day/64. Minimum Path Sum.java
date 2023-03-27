/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the 
sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 
Example 1:
    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    Output: 7
    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
    Input: grid = [[1,2,3],[4,5,6]]
    Output: 12
 

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    0 <= grid[i][j] <= 100
*/

class Solution {
    int n,m;
    Integer[][] dp ;
    public int minPathSum(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        dp = new Integer[n+1][m+1];
        
        return dfs(n-1, m-1, grid);
    }
    
    public int dfs(int i, int j, int[][] grid){
        if(i==0 && j==0)
            return grid[0][0];
        if(i<0 || j<0)
            return 100000;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        int up = grid[i][j] + dfs(i-1, j, grid);
        int left = grid[i][j] + dfs(i, j-1, grid);
        
        return dp[i][j] = Math.min(up, left);
    }
}
