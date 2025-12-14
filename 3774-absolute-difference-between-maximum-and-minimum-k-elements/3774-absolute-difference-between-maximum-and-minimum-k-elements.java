class Solution {
    public int absDifference(int[] nums, int k) {
        int n = nums.length;

        if(n == k) return 0;

        Arrays.sort(nums);

        int res = 0;

        for(int i=0; i<k; i++){
            res += (nums[n-1-i] - nums[i]);
        }

        return res;
    }
}