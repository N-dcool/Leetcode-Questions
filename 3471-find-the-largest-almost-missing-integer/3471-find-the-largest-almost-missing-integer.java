class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if(n==k) {
            int max = nums[0];
            for(int num : nums){
                max = Math.max(max, num);
            }

            return max;
        }

        int[] freq = new int[51];

        for(int num : nums) {
            freq[num]++;
        }

        if(k==1) {
            for(int i=50; i>=0; i--) {
                if(freq[i] == 1) return i;
            }
            return -1;
        }

        int a = nums[0];
        int b = nums[n-1];

        if(freq[a]==1 && freq[b]==1) return Math.max(a,b);

        if(freq[a]==1) return a;
        if(freq[b]==1) return b;

        return -1;
    }
}