class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int res = 0;
        int n = nums.length;

        for(int i=0; i<n; i++) {
            int count = 0;
            for(int j=i; j<n; j++) {
                if(nums[j] == target) count++;
                int len = j-i+1;
                if(len/2 < count) res++;
            }
        }

        return res;
    }
}