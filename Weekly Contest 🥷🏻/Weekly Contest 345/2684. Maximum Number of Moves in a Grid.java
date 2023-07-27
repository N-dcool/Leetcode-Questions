/*
You are given a 0-indexed m x n matrix grid consisting of positive integers.

You can start at any cell in the first column of the matrix, and traverse the grid in the following way:

From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such 
that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

 

Example 1:
    Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
    Output: 3
    Explanation: We can start at the cell (0, 0) and make the following moves:
    - (0, 0) -> (0, 1).
    - (0, 1) -> (1, 2).
    - (1, 2) -> (2, 3).
    It can be shown that it is the maximum number of moves that can be made.

Example 2:
    Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
    Output: 0
    Explanation: Starting from any cell in the first column we cannot perform any moves.
 

Constraints:
    m == grid.length
    n == grid[i].length
    2 <= m, n <= 1000
    4 <= m * n <= 105
    1 <= grid[i][j] <= 106
*/

class Solution {
    int n;
    int m;
    Integer[][] dp;
    public int maxMoves(int[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        dp = new Integer[n+1][m+1];
        
        int ans = 0;
        
        for(int i=0; i<n; i++){
            int a = helper(i, 0, grid);
            ans = Math.max(ans, a);
        }
        
        return ans;
        
    }
    
    public int helper(int i, int j, int[][] grid){
        if(!isValid(i,j))
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        int one = 0;
        int two = 0;
        int three = 0;
        
        if(isValid(i-1, j+1) && grid[i-1][j+1] > grid[i][j])
            one = 1 + helper(i-1, j+1, grid);
        if(isValid(i, j+1) && grid[i][j+1] > grid[i][j])
            two = 1 + helper(i, j+1, grid);
        if(isValid(i+1, j+1) && grid[i+1][j+1] > grid[i][j])
            three = 1 + helper(i+1, j+1, grid);
        
        return dp[i][j] =  Math.max(one, Math.max(two , three));
        
    }
    
    public boolean isValid(int i, int j){
        return i>=0 && j>=0 && i<n && j<m;
    }
}
