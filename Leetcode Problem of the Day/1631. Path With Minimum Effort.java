/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] 
represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, 
(rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

 
Example 1:
    Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
    Output: 2
    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
    Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
    Output: 1
    Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].


Example 3:
    Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
    Output: 0
    Explanation: This route does not require any effort.
 

Constraints:
    rows == heights.length
    columns == heights[i].length
    1 <= rows, columns <= 100
    1 <= heights[i][j] <= 106
*/

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int[][] dist = new int[n][m];
        for(int[] dis : dist)
            Arrays.fill(dis, 1000000);
        dist[0][0] = 0;
        
        pq.add(new int[]{0,0,0});
        
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int x = node[1];
            int y = node[2];
            int d = node[0];
            
            if(x==n-1 && y==m-1) return d;
            
            for(int[] dir : dirs){
                int i = x + dir[0];
                int j = y + dir[1];
                
                if(i<0 || j<0 || j>=m || i>=n) continue;
                
                int curAbsDiff = Math.abs(heights[i][j] - heights[x][y]);
                int newEffort = Math.max(d, curAbsDiff);
                
                if(newEffort < dist[i][j]){
                    dist[i][j] = newEffort;
                    pq.add(new int[]{newEffort,i,j});
                }
                
            }
        }
        
        return dist[n-1][m-1];
    }
}
