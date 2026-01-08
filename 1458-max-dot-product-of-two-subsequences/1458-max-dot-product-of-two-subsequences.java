class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int[][] dp = new int[505][505];
        for(int i=0; i<=501; i++)
            dp[0][i] = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int i=1; i<=nums1.length; i++){
            for(int j=1; j<=nums2.length; j++){
                if(i==1)
                    dp[j][i] = Math.max(dp[j-1][i], nums1[i-1]*nums2[j-1]);
                else if(j==1)
                    dp[j][i] = Math.max(dp[j][i-1],nums1[i-1]*nums2[j-1]);
                else
                    dp[j][i] = Math.max(dp[j-1][i],Math.max(dp[j][i-1], Math.max(nums1[i-1]*nums2[j-1],Math.max(dp[j-1][i-1],nums1[i-1]*nums2[j-1] + dp[j-1][i-1]))));
                max = Math.max(max,dp[j][i]);
            }
        }
        
        return max;
    }
}   