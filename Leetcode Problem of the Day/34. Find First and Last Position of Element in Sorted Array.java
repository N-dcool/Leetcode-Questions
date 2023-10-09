/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

 
Example 1:
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]

Example 2:
    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]

Example 3:
    Input: nums = [], target = 0
    Output: [-1,-1]
 

Constraints:
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums is a non-decreasing array.
    -109 <= target <= 109
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int idx = Arrays.binarySearch(nums,target);
        
        if(idx<0) return new int[]{-1,-1};
        
        int i=idx;
        while(i>=0 && nums[i]==target) i--;
        int j = idx;
        while(j<nums.length && nums[j]==target) j++;
        
        return new int[]{i+1,j-1};
    }
}
