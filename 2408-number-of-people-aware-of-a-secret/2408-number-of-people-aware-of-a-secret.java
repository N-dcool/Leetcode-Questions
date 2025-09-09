class Solution {
    Integer[] dp;
    int MOD = 1000000007;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        dp = new Integer[n+1];

        return solve(1, n, delay, forget);
    }

    public int solve(int day, int n, int delay, int forget){
        if(day == n) return 1;
        if(dp[day] != null) return dp[day];

        int aware = 1;

        for(int sharedDay = day+delay; sharedDay <= Math.min(n, day+forget); sharedDay++){
            if(sharedDay == day + forget){
                aware--;
                break;
            }

            aware = (aware + solve(sharedDay, n, delay, forget))%MOD;
        }

        return dp[day] = aware;
    }
}