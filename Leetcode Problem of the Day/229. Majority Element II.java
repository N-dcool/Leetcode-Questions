/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:
    Input: nums = [3,2,3]
    Output: [3]

Example 2:
    Input: nums = [1]
    Output: [1]

Example 3:
    Input: nums = [1,2]
    Output: [1,2]
 
Constraints:
    1 <= nums.length <= 5 * 104
    -109 <= nums[i] <= 109
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = nums.length;
        int size = n/3;
        
        for(int num : nums){
            map.put(num, map.getOrDefault(num,0)+1);
            if(map.get(num) == size+1)
                res.add(num);
        }
        
        return res;
    }
}
