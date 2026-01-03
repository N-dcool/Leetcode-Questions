class Solution {
    public long maximumScore(int[] nums) {
        int n = nums.length;

        long[] preSum = new long[n];
        long[] minSuf = new long[n];

        long sum = 0;
        long min = Long.MAX_VALUE;

        for(int i=0; i<n; i++){
            sum += nums[i];
            preSum[i] = sum;
            min = Math.min(min, nums[n-1-i]);
            minSuf[n-1-i] = min;
        }
        long res = Long.MIN_VALUE;
        for(int i=0; i<n-1; i++){
            res = Math.max(res, preSum[i] - minSuf[i+1]);
        }

        return res;
    }
}