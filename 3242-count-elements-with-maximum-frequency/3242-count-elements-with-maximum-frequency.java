class Solution {
    public int maxFrequencyElements(int[] nums) {
        int n = nums.length;
        int[] freq = new int[101];
        int maxF = 1;

        for(int num : nums){
            freq[num]++;
            maxF = Math.max(maxF, freq[num]);
        }

        int res = 0;

        for(int f : freq){
            if(f == maxF) res += f;
        }

        return res;
        
    }
}