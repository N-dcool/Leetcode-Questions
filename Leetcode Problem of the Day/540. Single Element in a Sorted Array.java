/*
ou are given a sorted array consisting of only integers where every element appears exactly twice,
except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

 
Example 1:
    Input: nums = [1,1,2,3,3,4,4,8,8]
    Output: 2

Example 2:
    Input: nums = [3,3,7,7,10,11,11]
    Output: 10
 

Constraints:
    1 <= nums.length <= 105
    0 <= nums[i] <= 105
*/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = 1;
        
        int n = nums.length-1;
        
        if(n==0)
            return nums[0];
        
        n = n/2;
        //System.out.println(n);
        
        while(n>0){
            if(nums[left]!=nums[right])
                return nums[left];
            left += 2;
            right += 2;
            n--;
        }
        
        return nums[nums.length-1];
        
    }
}
