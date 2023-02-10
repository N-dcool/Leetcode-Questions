/*
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, 
find a water cell such that its distance to the nearest land cell is maximized, and return the distance. 
If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

Example 1:
    Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
    Output: 2
    Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
    Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
    Output: 4
    Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

Constraints:
    n == grid.length
    n == grid[i].length
    1 <= n <= 100
    grid[i][j] is 0 or 1
*/


class Solution {
    
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1)
                    q.offer(new int[]{i,j});
            }
        }
        
        if(q.size()==0 || q.size()==n*m)
            return -1;
        
        int ans = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                int[] curCell = q.poll();
                
                for(int[] dir: dirs){
                    int x = curCell[0] + dir[0];
                    int y = curCell[1] + dir[1];
                    
                    if(x>=0 && y>=0 && x<n && y<m && grid[x][y]==0){
                        int[] nextCell = new int[]{x,y};
                        grid[x][y] = 1;
                        q.offer(nextCell);
                    }
                }
            }
            ans++;
        }
        
        return ans-1;
    }
}
