/*
Given a 2D grid consists of 0s (land) and 1s (water).  
An island is a maximal 4-directionally connected group of 0s and a closed island is an 
island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 
Example 1:
    Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
    Output: 2
    Explanation: 
    Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
      Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
      Output: 1
      Example 3:

      Input: grid = [[1,1,1,1,1,1,1],
                     [1,0,0,0,0,0,1],
                     [1,0,1,1,1,0,1],
                     [1,0,1,0,1,0,1],
                     [1,0,1,1,1,0,1],
                     [1,0,0,0,0,0,1],
                     [1,1,1,1,1,1,1]]
      Output: 2
 
 
Constraints:
    1 <= grid.length, grid[0].length <= 100
    0 <= grid[i][j] <=1
*/


class Solution {
    int n,m;
    int[][] dirs = new int[][]{{1,0},{-1,0}, {0,-1}, {0,1}};
    public int closedIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        
        for(int i=0; i<n; i++){
            bfs(i, 0, grid);
            bfs(i, m-1, grid);
        }
        for(int j=0; j<m; j++){
            bfs(0, j, grid);
            bfs(n-1, j, grid);
        }
        
        int ans = 0;
        for(int i=1; i<n-1; i++){
            for(int j=1; j<m-1; j++){
                if(grid[i][j]==0){
                    ans++;
                    bfs(i, j, grid);
                }
            }
        }
        
        
        return ans;
    }
    
    public void bfs(int i, int j, int[][] grid){
        if(i<0 || i>=n || j<0 || j>=m)
            return;
        if(grid[i][j]==1)
            return;
        
        grid[i][j] = 1;
        
        for(int[] d : dirs){
            int x = i + d[0];
            int y = j + d[1];
            
            bfs(x, y, grid);
        }
    }
}
