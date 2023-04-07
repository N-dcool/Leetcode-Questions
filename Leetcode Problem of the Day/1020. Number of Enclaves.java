/*
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:
    Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
    Output: 3
    Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
    
Example 2:
    Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
    Output: 0
    Explanation: All 1s are either on the boundary or can reach the boundary.
 

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 500
    grid[i][j] is either 0 or 1.
*/

class Solution {
    int n, m;
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    
    public int numEnclaves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        
        for(int i=0; i<n; i++){
            bfs(i, 0, grid);
            bfs(i, m-1, grid);
        }
        for(int j=1; j<m-1; j++){
            bfs(0, j, grid);
            bfs(n-1, j, grid);
        }
        
        
        int ans = 0;
        for(int i=1; i<n-1; i++){
            for(int j=1; j<m-1; j++){
                if(grid[i][j] == 1)
                    ans++;
            }
        }
        return ans;
    }
    
    public void bfs(int i, int j, int[][] grid){
        if(i<0 || i>=n || j<0 || j>=m)
            return;
        if(grid[i][j]==0)
            return;
        
        grid[i][j] = 0;
        
        for(int[] d : dirs){
            int x = i + d[0];
            int y = j + d[1];
            
            bfs(x, y, grid);
        }    
    }
}
