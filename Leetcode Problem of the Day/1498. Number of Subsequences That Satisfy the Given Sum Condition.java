/*
You are given an array of integers nums and an integer target.

Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. 
Since the answer may be too large, return it modulo 109 + 7.

 
Example 1:
    Input: nums = [3,5,6,7], target = 9
    Output: 4
    Explanation: There are 4 subsequences that satisfy the condition.
    [3] -> Min value + max value <= target (3 + 3 <= 9)
    [3,5] -> (3 + 5 <= 9)
    [3,5,6] -> (3 + 6 <= 9)
    [3,6] -> (3 + 6 <= 9)

Example 2:
    Input: nums = [3,3,6,8], target = 10
    Output: 6
    Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
    [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]

Example 3:
    Input: nums = [2,3,3,4,6,7], target = 12
    Output: 61
    Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
    Number of valid subsequences (63 - 2 = 61).
 

Constraints:
    1 <= nums.length <= 105
    1 <= nums[i] <= 106
    1 <= target <= 106
*/

class Solution {
    int mod = 1000000007;
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = n-1;
        long ans = 0;
        
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; ++i) {
            power[i] = (power[i - 1] * 2) % mod;
        }
        
        while(l<=r){
            int val = nums[l]+nums[r];
            if(val <= target){
                ans = (ans + power[r-l])%mod;
                l++;
            }
            else if(val > target) r--;
            else l++;
        }
        
        return (int)ans;
    }
}


/*
class Solution {
    HashMap<String, Integer> dp;
    int mod = 1000000007;
    public int numSubseq(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Arrays.sort(nums);
        dp = new HashMap<>();
        
        return dfs(0, min, max, target, nums);
    }
    
    public int dfs(int i, int min, int max, int target, int[] nums){
        if(i==nums.length)
            return 0;
        String s = i+"+" +"+"+ min+"+" + max;
        if(dp.containsKey(s))
            return dp.get(s);
        
        int ans = 0;
        ans = dfs(i+1, min, max, target, nums);
        
        max = Math.max(max, nums[i]);
        min = Math.min(min, nums[i]);
        
        if(target >= max + min){
            ans = (ans + 1 + dfs(i+1, min, max, target, nums))%mod;
        }
        
        dp.put(s, ans);
        return ans;
    }
}
*/
