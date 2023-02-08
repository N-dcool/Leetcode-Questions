/*
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i],
you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

 

Example 1:
    Input: nums = [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
    Input: nums = [2,3,0,1,4]
    Output: 2
 

Constraints:
    1 <= nums.length <= 104
    0 <= nums[i] <= 1000
*/


// 1 . Recursion + memoization:

	 class Solution {
    Integer[] dp;
    public int jump(int[] nums) {
        int n = nums.length;
        dp = new Integer[n+1];
        return helper(0, nums, n);
    }
    
    public int helper(int idx, int[] nums, int n){
        if(idx >= n-1)
            return 0;
        if(dp[idx]!=null)
            return dp[idx];
        
        int minJump = 100000;
        
        for(int i=1; i<=nums[idx]; i++){
            minJump = Math.min(minJump, 1 + helper(idx+i, nums, n));
        }
        
        return dp[idx] = minJump;
    }
}


// 2.Tabulation :

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 100000);
        dp[n-1] = 0;
        
        for(int i=n-2; i>=0; i--){
            for(int jumpLen = 1; jumpLen<= nums[i]; jumpLen++){
                dp[i] = Math.min( dp[i], 1 + dp[Math.min(n-1, i+jumpLen)] );
            }
        }
        
        return dp[0];
    }
}



// 3.Greedy (most optimal solution):

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int farthest=0, jumps=0, end=0;
        
        for(int i=0; i<n; i++){
            if(i > end){
                end=farthest;
                jumps++;
            }
            farthest=Math.max(farthest, i+nums[i]);
        }
        
        return jumps;
    }
}
