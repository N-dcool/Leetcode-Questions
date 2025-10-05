class Solution {
    int n,m;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        n = heights.length;
        m = heights[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];

        // for 0th row and nth row
        for(int j=0; j<m; j++){
            dfs(0, j, Integer.MIN_VALUE, pacific, heights);
            dfs(n-1, j, Integer.MIN_VALUE, atlantic, heights);
        }

        // for 0th col and mth col
        for(int i=0; i<n; i++){
            dfs(i, 0, Integer.MIN_VALUE, pacific, heights);
            dfs(i, m-1, Integer.MIN_VALUE, atlantic, heights);
        }

        List<List<Integer>> res = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    res.add(Arrays.asList(i,j));
                }
            }
        }

        return res;
    }

    public void dfs(int i, int j, int prev, boolean[][] vis ,int[][] ocean){
        if(i<0 || j<0 || i==n || j==m || vis[i][j]) return;

        if(prev > ocean[i][j]) return;

        vis[i][j] = true;

        dfs(i, j+1, ocean[i][j], vis, ocean);
        dfs(i, j-1, ocean[i][j], vis, ocean);
        dfs(i+1, j, ocean[i][j], vis, ocean);
        dfs(i-1, j, ocean[i][j], vis, ocean);

    }
}