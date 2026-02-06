class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        while(nums[0] * k < nums[n-1-count]){
            count++;
        }

        return count;
    }
}