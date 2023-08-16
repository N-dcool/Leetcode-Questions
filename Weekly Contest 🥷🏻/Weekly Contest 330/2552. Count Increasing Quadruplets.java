/*
Given a 0-indexed integer array nums of size n containing all numbers from 1 to n, return the number of increasing quadruplets.

A quadruplet (i, j, k, l) is increasing if:

0 <= i < j < k < l < n, and
nums[i] < nums[k] < nums[j] < nums[l].
 

Example 1:
    Input: nums = [1,3,2,4,5]
    Output: 2
    Explanation: 
    - When i = 0, j = 1, k = 2, and l = 3, nums[i] < nums[k] < nums[j] < nums[l].
    - When i = 0, j = 1, k = 2, and l = 4, nums[i] < nums[k] < nums[j] < nums[l]. 
    There are no other quadruplets, so we return 2.

Example 2:
    Input: nums = [1,2,3,4]
    Output: 0
    Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, l = 3, but since nums[j] < nums[k], we return 0.
 

Constraints:
    4 <= nums.length <= 4000
    1 <= nums[i] <= nums.length
    All the integers of nums are unique. nums is a permutation.
*/

class Solution {
    public long countQuadruplets(int[] nums) {
        long res = 0;
        int n = a.length;
        int [][]left = new int[n][n+1];
        int [][]right = new int[n][n+1];
        for (int i = 1; i < n; ++i) {
            // new array will based on the old array
            for (int j = 0; j <= n; ++j) 
                left[i][j] = left[i-1][j];
            // update all the elements greater than a[i-1]
            for (int j = a[i-1] + 1; j <= n; ++j)
                left[i][j]++;
        }
        for (int i = n-2; i >= 0; --i) {
            for (int j = 0; j <= n; ++j) 
                right[i][j] = right[i+1][j];
            for (int j = 0; j < a[i+1]; ++j) 
                right[i][j]++;
        }
        for (int j = 0; j < n; ++j) {
            for (int k = j+1; k < n; ++k) {
                if (a[j] <= a[k]) continue;
                // left[j][a[k]] is the count of feasible i
                // right[k][a[j]] is the count of feasible l
                res += left[j][a[k]] * right[k][a[j]];
            }
        }
        return res;
    }
}

class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        long res = 0L;
        for (int x = 2; x < n; x++) {
            int smallerThanK = 0; // k always means k in the quadruplets
            for (int y = 0; y < x; y++) {
                if (nums[x] > nums[y]) { 
                    smallerThanK++;
                    res += dp[y];
                } else if (nums[x] < nums[y]) { 
                    dp[y] += smallerThanK;
                }
            }
        }
        
        return res;
    }
}


