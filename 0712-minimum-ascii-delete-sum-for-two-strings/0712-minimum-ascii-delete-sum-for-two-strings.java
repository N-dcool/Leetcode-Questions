class Solution {
    int n,m;
    Integer[][] dp;
    public int minimumDeleteSum(String s1, String s2) {
        n = s1.length();
        m = s2.length();

        dp = new Integer[n+1][m+1];

        return solve(0, 0, s1.toCharArray(), s2.toCharArray());
    }

    public int solve(int i, int j, char[] s1, char[] s2){
        if(dp[i][j] != null){
            return dp[i][j];
        }
        if(i==n && j==m){
            return 0;
        }
        if(i==n){
            int totalAdd = 0;
            while(j<m){
                totalAdd+=s2[j++];
            }
            return totalAdd;
        }
        if(j==m){
            int totalDelete = 0;
            while(i<n){
                totalDelete+=s1[i++];
            }
            return totalDelete;
        }

        if(s1[i] == s2[j]){
            return solve(i+1, j+1, s1, s2);
        }

        int delete = s1[i] + solve(i+1, j, s1, s2);
        int add = s2[j] + solve(i, j+1, s1, s2);

        return dp[i][j] = Math.min(delete, add);
    }
}