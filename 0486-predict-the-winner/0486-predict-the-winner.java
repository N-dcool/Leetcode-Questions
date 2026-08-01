class Solution {
    public boolean predictTheWinner(int[] A) {
        int n = A.length;
        if ((n & 1) == 0) return true;

        int[][] dp = new int[n][n];
        for (int[] r : dp) 
            Arrays.fill(r, -1);
        
        return maxDiff(0, n - 1, A, dp) >= 0;
    }

    private int maxDiff(int i, int j, int[] A, int[][] dp) {
        if (dp[i][j] != -1) return dp[i][j];        
        if (i == j) return dp[i][j] = A[i];
        
        return dp[i][j] = Math.max(
            A[i] - maxDiff(i + 1, j, A, dp),
            A[j] - maxDiff(i, j - 1, A, dp)
        );
    }
}