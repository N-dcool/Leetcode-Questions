class Solution {
    int n,m;
    Integer[][] dp;
    public int maxDotProduct(int[] nums1, int[] nums2) {
        n = nums1.length;
        m = nums2.length;
        int[][] mat = new int[n][m];
        dp = new Integer[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                mat[i][j] = nums1[i]*nums2[j];
            }
        }
        // for(int[] ma : mat){
        //     for(int m : ma){
        //         System.out.print(m + " ");
        //     }
        //     System.out.println();
        // }
        
        return solve(0,0, mat);
    }

    public int solve(int i, int j, int[][] mat){
        if(i ==n || j==m) return -1000000;

        if(dp[i][j] != null) return dp[i][j];

        int cur = mat[i][j];

        int take = Math.max( cur , cur + solve(i+1, j+1, mat));
        int notTake = Math.max(solve(i+1, j, mat), solve(i, j+1, mat));

        return dp[i][j] = Math.max(take, notTake);
    }
}