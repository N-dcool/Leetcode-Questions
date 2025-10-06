class Solution {
    int n;
    public int swimInWater(int[][] grid) {
        n = grid.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                min = Math.min(min, grid[i][j]);
                max = Math.max(max, grid[i][j]);
            }
        }
    
        return binarySearch(grid, min, max);
    }

    public int binarySearch(int[][] grid, int left, int right){
        int res = right;

        while(left <= right){
            int mid = (left+right)/2;

            // System.out.println(left +" "+mid+" "+right);

            if(dfs(0, 0, mid, new boolean[n][n], grid)){
                res = mid;
                right = mid-1;
            } else{
                left = mid+1;
            }
        }

        return res;
    }

    public boolean dfs(int i, int j, int max, boolean[][] vis, int[][] grid){
        if(i<0 || j<0 || i==n || j==n || vis[i][j] || grid[i][j] > max) return false;
        if(i==n-1 && j==n-1) return true;

        vis[i][j] = true;

        return dfs(i, j+1, max, vis, grid) ||
               dfs(i, j-1, max, vis, grid) ||
               dfs(i+1, j, max, vis, grid) ||
               dfs(i-1, j, max, vis, grid);

    }
}