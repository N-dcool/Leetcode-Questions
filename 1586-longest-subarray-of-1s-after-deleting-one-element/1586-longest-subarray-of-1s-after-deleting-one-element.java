class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int prevSubSum = 0;
        int max = 0;

        for(int i=0; i<n; i++){
            int j = i;
            int curSubSum = 0;
            while(j<n && nums[j] == 1){
                curSubSum++;
                j++;
            }
            i = j;
            max = Math.max(max, prevSubSum + curSubSum);
            prevSubSum = curSubSum;
        }

        return n == max ? max-1 : max;
    }
}