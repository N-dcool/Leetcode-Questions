/*
The beauty of a subarray is the xth smallest integer in the subarray if it is negative, or 0 if there are fewer than x negative integers.

Return an integer array containing n - k + 1 integers, which denote the beauty of the subarrays in order from the first index in the array.

A subarray is a contiguous non-empty sequence of elements within an array.

 
Example 1:
    Input: nums = [1,-1,-3,-2,3], k = 3, x = 2
    Output: [-1,-2,-2]
    Explanation: There are 3 subarrays with size k = 3. 
    The first subarray is [1, -1, -3] and the 2nd smallest negative integer is -1. 
    The second subarray is [-1, -3, -2] and the 2nd smallest negative integer is -2. 
    The third subarray is [-3, -2, 3] and the 2nd smallest negative integer is -2.

Example 2:
    Input: nums = [-1,-2,-3,-4,-5], k = 2, x = 2
    Output: [-1,-2,-3,-4]
    Explanation: There are 4 subarrays with size k = 2.
    For [-1, -2], the 2nd smallest negative integer is -1.
    For [-2, -3], the 2nd smallest negative integer is -2.
    For [-3, -4], the 2nd smallest negative integer is -3.
    For [-4, -5], the 2nd smallest negative integer is -4. 

Example 3:
    Input: nums = [-3,1,2,-3,0,-3], k = 2, x = 1
    Output: [-3,0,-3,-3,-3]
    Explanation: There are 5 subarrays with size k = 2.
    For [-3, 1], the 1st smallest negative integer is -3.
    For [1, 2], there is no negative integer so the beauty is 0.
    For [2, -3], the 1st smallest negative integer is -3.
    For [-3, 0], the 1st smallest negative integer is -3.
    For [0, -3], the 1st smallest negative integer is -3.
 
Constraints:
    n == nums.length 
    1 <= n <= 105
    1 <= k <= n
    1 <= x <= k 
    -50 <= nums[i] <= 50  count of -49,
    
Solution Explain: 
    • It would be nice if you could always keep the current window sorted, then it would be easy to find the xth smallest value at each window move. This kind of sorting is expensive in general cases but notice that we already know the range of values that the array can have, [-50, 50]. so we can just use Counting Sort!
        a[] represents the count of the current window values. but since arrays don't have negative indices,
        a[0] means count of -50,
        a[1] meansGiven an integer array nums containing n integers, find the beauty of each subarray of size k.
        .
        .
        a[49] means count of -1.
    • Initialize a[] with the count of the values in the initial window [0..k). For every window move, this step only takes O(1) because you only update the count of new num added to the window at right and the num removed at the left.
    • Now at every window slide, you essentially have the window values sorted in ascending order in a[]. so find the xth smallest value in it.
*/

class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] freqOfNeg = new int[50];
        int[] ans = new int[n-k+1];
        
        for(int i=0; i<n; i++){
            if(nums[i] < 0)
                freqOfNeg[50 + nums[i]]++;
            if(i+1 >= k){
                int count = 0;
                for(int j=0; j<50; j++){
                    count += freqOfNeg[j];
                    if(count >= x){
                        ans[i-k+1] = j - 50;
                        break;
                    }
                }
                if(nums[i-k+1] < 0)
                    freqOfNeg[50 + nums[i-k+1]]--;
            }
        }
        
        return ans;
    }
}
