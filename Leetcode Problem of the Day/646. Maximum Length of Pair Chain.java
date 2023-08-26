/*
You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.

 

Example 1:
    Input: pairs = [[1,2],[2,3],[3,4]]
    Output: 2
    Explanation: The longest chain is [1,2] -> [3,4].

Example 2:
    Input: pairs = [[1,2],[7,8],[4,5]]
    Output: 3
    Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 

Constraints:
    n == pairs.length
    1 <= n <= 1000
    -1000 <= lefti < righti <= 1000
*/


class Solution {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        if(n==1) return 1;
        Arrays.sort(pairs, (a,b)->a[1]-b[1]);
        
        int ans = 0;
        int cur = -1001;
        for(int[] pair : pairs){
            if(pair[0] > cur){
                cur = pair[1];
                ans++;
            }
        }
        
        return ans;
    }
}



class Solution {
    int[][] pairs;
    int n;
    Integer[][] dp;
    public int findLongestChain(int[][] pairs) {
        this.pairs = pairs;
        this.n = pairs.length;
        dp = new Integer[n+1][2002];
        Arrays.sort(pairs,(a,b)->a[0]-b[0]);
        
        return helper(0,-1001);
    }
    
    public int helper(int i, int prev){
        if(i==n) return 0;
        if(dp[i][prev+1001]!=null)
            return dp[i][prev+1001];
        int take = -1;
        
        if(pairs[i][0] > prev)
            take = 1 + helper(i+1, pairs[i][1]);
        int notTake = 0 + helper(i+1, prev);
        
        return dp[i][prev+1001] = Math.max(take, notTake);
    }
}


