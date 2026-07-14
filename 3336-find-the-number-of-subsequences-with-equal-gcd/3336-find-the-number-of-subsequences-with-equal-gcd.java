class Solution {
    private int[] nums;
    private int n;
    private static final int MOD = 1000000007;
    private Integer[][][] dp;
    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        dp = new Integer[n][201][201];

        return solve(0,0,0);
    }

    private int solve(int i, int gcd1, int gcd2) {
        if(i==n){
            if(gcd1==0 || gcd2==0) return 0;

            return gcd1==gcd2 ? 1 : 0;
        }
        if(dp[i][gcd1][gcd2] != null) return dp[i][gcd1][gcd2];

        long seq1 = solve(i+1, gcd(gcd1, nums[i]), gcd2);
        long seq2 = solve(i+1, gcd1, gcd(gcd2, nums[i]));
        long skip = solve(i+1, gcd1, gcd2);

        return dp[i][gcd1][gcd2] = (int)((seq1+seq2+skip)%MOD);
    }

    private int gcd(int a, int b) {
        while(b != 0) {
            int rem = a%b;
            a = b;
            b = rem;
        }

        return a;
    }
}