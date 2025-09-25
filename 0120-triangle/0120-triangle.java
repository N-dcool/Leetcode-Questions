class Solution {
    int n,m;
    Integer[][] dp;
    public int minimumTotal(List<List<Integer>> triangle) {
        n = triangle.size();
        m = triangle.get(n-1).size();
        dp = new Integer[n][m];

        return solve(0,0,triangle);
    }

    public int solve(int i, int j, List<List<Integer>> triangle){
        if(i == n-1) return triangle.get(i).get(j);
        if(dp[i][j] != null) return dp[i][j];

        int down = triangle.get(i).get(j) + solve(i+1, j, triangle);
        int downRight = triangle.get(i).get(j) + solve(i+1, j+1, triangle);


        return dp[i][j] = Math.min(down, downRight);
    }
}