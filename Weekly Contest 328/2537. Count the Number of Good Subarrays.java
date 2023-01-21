/*
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if it there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i], k <= 109
*/


class Solution {
        public long countGood(int[] nums, int k) {
        long ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int j = 0;
        long countPairs = 0;

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {

                int val = map.get(nums[i]);
                //to replace the  the previous pair with new pairs
                countPairs -= ((long) val * (val - 1) / 2);
                map.put(nums[i], map.get(nums[i]) + 1);
                 val = map.get(nums[i]);
                countPairs += ((long) val * (val - 1) / 2);

            } else{
                map.put(nums[i], 1);
            }
            
            // System.out.println(countPairs);
             //sliding the window to right 
            while (j <= i && countPairs >= k) {
                int cur = map.get(nums[j]);
                countPairs -= (long) cur * (cur - 1) / 2;
                map.put(nums[j], cur - 1);
                cur = map.get(nums[j]);
                countPairs += (long) (cur ) * (cur -1) / 2;
                j++;
                ans += (nums.length - i);
            }

        }
        return ans;
 
    }
}
