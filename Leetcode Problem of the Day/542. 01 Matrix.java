/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:
    Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
    Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
    Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
    Output: [[0,0,0],[0,1,0],[1,2,1]]
 

Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 104
    1 <= m * n <= 104
    mat[i][j] is either 0 or 1.
    There is at least one 0 in mat.
*/


class Solution {
    
    public record Pair(int row, int col){}
    
    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int max = m+n+1;
        
        Queue<Pair> q = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j]==0) q.add(new Pair(i,j));
                else mat[i][j] = max;
            }
        }
        
        int dis = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Pair p = q.poll();
                int x = p.row;
                int y = p.col;
                
                for(int[] d : dirs){
                    int row = x+d[0];
                    int col = y+d[1];
                    
                    if(row<0 || col<0 || row>=n || col>=m || mat[row][col] <= m+n) continue;
                    
                    mat[row][col] = 1 + mat[x][y];
                    q.add(new Pair(row,col));
                }
            }
        }
        
        
        return mat;
    }
}
