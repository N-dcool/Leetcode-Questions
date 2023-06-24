/*
You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. 
Each steel support must be an equal height.

You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them
together to make a support of length 6.

Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.

 

Example 1:
    Input: rods = [1,2,3,6]
    Output: 6
    Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.

Example 2:
    Input: rods = [1,2,3,4,5,6]
    Output: 10
    Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.

Example 3:
    Input: rods = [1,2]
    Output: 0
    Explanation: The billboard cannot be supported, so we return 0.
 

Constraints:
    1 <= rods.length <= 20
    1 <= rods[i] <= 1000
    sum(rods[i]) <= 5000
*/

class Solution {
    int[] rods;
    int n;
    Integer[][] dp;
    public int tallestBillboard(int[] rods) {
        this.rods = rods; 
        this.n = rods.length;
        this.dp = new Integer[n+1][2*5000 + 1];
        
        return helper(0, 0);
    }
    
    public int helper(int i, int diff){
        if(i==n){
            if(diff==0) return 0;
            return Integer.MIN_VALUE;
        }  
        if(dp[i][diff + 5000] != null)
            return dp[i][diff+5000];
        
        int a = helper(i+1, diff);
        int b = rods[i] + helper(i+1, diff + rods[i]);
        int c = helper(i+1, diff - rods[i]);
        
        return dp[i][diff+5000] = Math.max(a, Math.max(b,c));
    }
}
