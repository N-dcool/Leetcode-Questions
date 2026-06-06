class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int prefixSum = Arrays.stream(nums).parallel().sum();
        int rightSum = prefixSum;
        int leftSum = 0;

        for(int i=0; i<n; i++) {
            rightSum -= nums[i];
            res[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }

        return res;
    }
}