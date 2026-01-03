class Solution {
    int n;
    Integer[][] dp;
    int MOD = 1000000007;

    public int numOfWays(int n) {
        this.n = n;
        dp = new Integer[n][334];

        return solve(0,"333");
    }

    public int solve(int i, String cur){
        if(i == n) return 1;
        int colorCode = Integer.parseInt(cur);

        if(dp[i][colorCode] != null) return dp[i][colorCode];
        int ans = 0;

        for(String next : new String[]{"010","012","020","021","101","102","120","121","201","202","210","212"}){
            if(isValid(cur, next)){
                ans = (ans + solve(i+1, next))%MOD;
            }
        }
        return dp[i][colorCode] = ans;
    }

    public boolean isValid(String cur, String next){
        return  cur.charAt(0) != next.charAt(0) && 
                cur.charAt(1) != next.charAt(1) && 
                cur.charAt(2) != next.charAt(2);
    }
}