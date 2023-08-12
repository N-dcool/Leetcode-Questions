/*
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:
    Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
    Output: 2
    Explanation: There is one obstacle in the middle of the 3x3 grid above.
    There are two ways to reach the bottom-right corner:
    1. Right -> Right -> Down -> Down
    2. Down -> Down -> Right -> Right

Example 2:
    Input: obstacleGrid = [[0,1],[0,0]]
    Output: 1
 

Constraints:
    m == obstacleGrid.length
    n == obstacleGrid[i].length
    1 <= m, n <= 100
    obstacleGrid[i][j] is 0 or 1.
*/

class Solution {
    Integer[][] dp;
    int n,m;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.n = obstacleGrid.length;
        this.m = obstacleGrid[0].length;
        dp = new Integer[n+1][m+1];
        if(obstacleGrid[0][0]==1 || obstacleGrid[n-1][m-1]==1)
            return 0;
        
        return helper(0,0,obstacleGrid);
    }
    
    public int helper(int i, int j, int[][] grid){
        if(i==n-1 && j==m-1)
            return 1;
        if(!isValid(i,j,grid)) return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        grid[i][j] = 1;
        
        int a = helper(i+1, j, grid);
        int b = helper(i, j+1, grid);
        
        grid[i][j] = 0;
        
        return dp[i][j] = a+b;
    }
    
    public boolean isValid(int i, int j, int[][] obstacleGrid){
        return i>=0 && j>=0 && i<n && j<m && obstacleGrid[i][j]!=1;
    }
}
