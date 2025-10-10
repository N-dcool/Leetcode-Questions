class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int max = Integer.MIN_VALUE;
        int n = energy.length;
        int[] dp = new int[n+k+1];

        for(int i=n-1; i>=0; i--){
            dp[i] = energy[i] + dp[i+k];

            max = Math.max(max, dp[i]);
        }

        return max;
        
    }
}