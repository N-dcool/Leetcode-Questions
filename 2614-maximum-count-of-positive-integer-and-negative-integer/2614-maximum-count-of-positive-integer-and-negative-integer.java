class Solution {
    public int maximumCount(int[] nums) {
        int n = nums.length;

        int neg = lowerBound(nums, 0);
        int pos = n - lowerBound(nums, 1);


        return Math.max(neg, pos);
    }

    public int lowerBound(int[] nums, int target){
        int left = 0;
        int right = nums.length;

        while(left < right){
            int mid = left + (right-left)/2;

            if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
    public int upperBound(int[] nums, int target){
        int left = 0;
        int right = nums.length;

        while(left < right){
            int mid = left + (right-left)/2;

            if(nums[mid] <= target){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}