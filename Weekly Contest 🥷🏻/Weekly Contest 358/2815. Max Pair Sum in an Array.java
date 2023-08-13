/*
You are given a 0-indexed integer array nums. You have to find the maximum sum of a pair of numbers from nums such that the 
maximum digit in both numbers are equal.

Return the maximum sum or -1 if no such pair exists.

 
Example 1:
    Input: nums = [51,71,17,24,42]
    Output: 88
    Explanation: 
    For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a pair sum of 71 + 17 = 88. 
    For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a pair sum of 24 + 42 = 66.
    It can be shown that there are no other pairs with equal maximum digits, so the answer is 88.

Example 2:
    Input: nums = [1,2,3,4]
    Output: -1
    Explanation: No pair exists in nums with equal maximum digits.
 

Constraints:
    2 <= nums.length <= 100
    1 <= nums[i] <= 104
*/

class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        
        int[] maxDigitAtEachIndex = new int[n];
        
        int i = 0;
        for (int num : nums) {
            char[] ds = Integer.toString(num).toCharArray();
            char maxDigit = '0';
            for (char d : ds) {
                maxDigit = (char) Math.max(maxDigit, d);
            }
            maxDigitAtEachIndex[i++] = maxDigit;
        }
                
        int ans = -1;
        for(i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isValid(i, j, maxDigitAtEachIndex))
                    ans = Math.max(ans, nums[i] + nums[j]);
            }
        }
        
        return ans;
    }
    
    public boolean isValid(int i, int j, int[] arr){
        return arr[i] == arr[j];
    }
}
