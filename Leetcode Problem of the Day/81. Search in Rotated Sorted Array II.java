/*
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array 
is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at 
pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

 

Example 1:
    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true

Example 2:
    Input: nums = [2,5,6,0,0,1,2], target = 3
    Output: false
 

Constraints:
    1 <= nums.length <= 5000
    -104 <= nums[i] <= 104
    nums is guaranteed to be rotated at some pivot.
    -104 <= target <= 104
 

Follow up: This problem is similar to Search in Rotated Sorted Array, 
but nums may contain duplicates. Would this affect the runtime complexity? How and why?

As we can see, by having duplicate elements in the array, we often miss the opportunity to apply binary search in certain search spaces.
Hence, we get 
O(N)
O(N) worst case (with duplicates) vs O(log N)
O(logN) best case complexity (without duplicates).
*/

class Solution {
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        
        while(l <= r && nums[l] == nums[r]) {
            if(nums[l] == target) {
                return true;
            }
            l++;
            r--;
        }
        
        return binarySearch(l, r, nums, target);
    }
    
    public boolean binarySearch(int l, int r, int[] nums, int target){
        while(l <= r){
            int mid = (l+r)/2;
            int cur = nums[mid];
            if(cur == target)
                return true;
            //left sorted : 
            if(nums[l] <= cur){
                if(target>=nums[l] && target<cur)
                    r = mid-1;
                else
                    l = mid+1;
            }
            // right sorted :
            else{
                if(target>cur && target<=nums[r])
                    l = mid+1;
                else
                    r = mid-1;
            }
        }
        
        return false;
    }
}
