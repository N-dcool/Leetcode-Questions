class Solution {
    int maxSum = 0;
    int m,k;
    long[][] dp;

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        this.m = n-k+1;
        this.k = k;
        this.dp = new long[m+1][4];

        for(long[] d : dp) {
            Arrays.fill(d, -1);
        }

        long[] windowSum = new long[m];

        long prefixSum = 0;
        for(int i=0; i<k; i++) {
            prefixSum += nums[i];
        }

        windowSum[0] = prefixSum;
        for(int i=1; i<m; i++) {
            prefixSum -= nums[i-1];
            prefixSum += nums[i+k-1];
            windowSum[i] = prefixSum;
        }

        solve(0, 0, windowSum);

        int[] res = new int[3];
        dfs(0, 0, windowSum, res);

        return res;
    }

    public long solve(int i, int count, long[] windowSum) {
        if(count == 3) return 0;
        if(i>=m) return 0;
        if(dp[i][count] != -1) return dp[i][count];

        long notTake = solve(i+1, count, windowSum);
        long take = windowSum[i] + solve(i+k, count+1, windowSum);

        return dp[i][count] = Math.max(take, notTake);
    }

    public void dfs(int i, int count, long[] windowSum, int[] res) {
        if(count == 3) return;
        if(i>=m) return;

        long notTake = solve(i+1, count, windowSum);
        long take = windowSum[i] + solve(i+k, count+1, windowSum);

        if(take >= notTake) {
            res[count] = i;
            dfs(i+k, count+1, windowSum, res);
        } else {
            dfs(i+1, count, windowSum, res);
        }
    }
}