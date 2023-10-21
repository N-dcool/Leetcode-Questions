/*
Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in 
the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

Example 1:
    Input: nums = [10,2,-10,5,20], k = 2
    Output: 37
    Explanation: The subsequence is [10, 2, 5, 20].

Example 2:
    Input: nums = [-1,-2,-3], k = 1
    Output: -1
    Explanation: The subsequence must be non-empty, so we choose the largest number.

Example 3:
    Input: nums = [10,-2,-10,-5,20], k = 2
    Output: 23
    Explanation: The subsequence is [10, -2, -5, 20].
 

Constraints:
    1 <= k <= nums.length <= 105
    -104 <= nums[i] <= 104
*/

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1]-a[1]);
        pq.add(new int[]{0, nums[0]});
        
        int max = nums[0];
        
        for(int i=1; i<n; i++){
            
            while(!pq.isEmpty() && pq.peek()[0] + k < i) pq.poll();
            
            int cur = Math.max(0, pq.peek()[1]) + nums[i];
            max = Math.max(max, cur);
            pq.add(new int[]{i, cur});
            
        }
        
        return max;
    }
}
