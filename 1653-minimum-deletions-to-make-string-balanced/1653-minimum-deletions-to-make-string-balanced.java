class Solution {
    Integer[][] dp;
    public int minimumDeletions(String s) {
        dp = new Integer[s.length()][2];
        return solve(0,0,s);
    }

    public int solve(int i, int b, String s){
        if(i == s.length()) return 0;
        if(dp[i][b] != null) return dp[i][b];

        char c = s.charAt(i);
        if(b==1){
            int eval = solve(i+1, b, s);
            return dp[i][b] = c == 'a' ? 1 + eval: eval;
        }

        if(c == 'a') return solve(i+1, b, s); 

        int add = solve(i+1, 1, s);
        int delete = 1 + solve(i+1, 0, s);

        return dp[i][b] = Math.min(add, delete);
    }
}