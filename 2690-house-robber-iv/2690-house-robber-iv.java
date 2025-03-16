class Solution {
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int left = Integer.MAX_VALUE;
        int right = 1;

        for(int num : nums){
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        int ans = left;

        while(left <= right){
            int mid = left + (right-left)/2;
            // System.out.println("left : " + left +" right : " + right); 
            if(isPossible(nums, mid, k)){
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public boolean isPossible(int[] nums, int capability, int min){
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] <= capability){
                // System.out.println(nums[i] +" "+ capability);
                min--;
                i++;
            }
            if(min == 0) return true;
        }
        
        return false;
    }


}