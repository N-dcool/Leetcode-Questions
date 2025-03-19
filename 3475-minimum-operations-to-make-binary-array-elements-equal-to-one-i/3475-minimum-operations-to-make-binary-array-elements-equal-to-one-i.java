class Solution {
    public int minOperations(int[] nums) {
        int count = 0;
        int n = nums.length;

        for(int i=0; i<n-2; i++){
            if(nums[i] == 0){
                count++;
                flip(nums, i);
            }
        }

        return nums[n-2] == 0 || nums[n-1] == 0 ? -1 : count;
    }

    public void flip(int[] nums, int idx){
        nums[idx] = 1;
        nums[idx+1] = nums[idx+1] == 0 ? 1 : 0; 
        nums[idx+2] = nums[idx+2] == 0 ? 1 : 0; 
    }
}