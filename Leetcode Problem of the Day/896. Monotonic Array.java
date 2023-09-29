/*
An array is monotonic if it is either monotone increasing or monotone decreasing.

An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all
i <= j, nums[i] >= nums[j].

Given an integer array nums, return true if the given array is monotonic, or false otherwise.

 
Example 1:
    Input: nums = [1,2,2,3]
    Output: true

Example 2:
    Input: nums = [6,5,4,4]
    Output: true

Example 3:
    Input: nums = [1,3,2]
    Output: false
 

Constraints:
    1 <= nums.length <= 105
    -105 <= nums[i] <= 105
*/

class Solution {
    public boolean isMonotonic(int[] nums) {
        if (nums[0] < nums[nums.length-1]) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i-1]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i-1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean isMonotonic(int[] nums) {
        int n = nums.length;
        if(n<=2) return true;
        
        int i = 1;
        int diff = 0;
        while(i<n && diff==0){
            diff = nums[i] - nums[i-1];
            i++;
        }
        
        for(; i<n; i++){
            if(diff<0 && nums[i-1]<nums[i]) return false;
            if(diff>0 && nums[i-1]>nums[i]) return false;
        }
        
        return true;
    }
}
