/*
There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, 
return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.

 

Example 1:
    Input: piles = [[1,100,3],[7,8,9]], k = 2
    Output: 101
    Explanation:
    The above diagram shows the different ways we can choose k coins.
    The maximum total we can obtain is 101.

Example 2:
    Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
    Output: 706
    Explanation:
    The maximum total can be obtained if we choose all coins from the last pile.
 

Constraints:
    n == piles.length
    1 <= n <= 1000
    1 <= piles[i][j] <= 105
    1 <= k <= sum(piles[i].length) <= 2000
*/

class Solution {
    int n;
    Integer[][] dp;
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        n = piles.size();
        dp = new Integer[n+1][k+1];
        
        for(List<Integer> pile : piles){
            prefixSum(pile);
        }
        
        return helper(0, k, piles);
    }
    
    public int helper(int i, int k, List<List<Integer>> piles){
        if(i >= n)
            return 0;
        if(dp[i][k]!=null)
            return dp[i][k];
        
        int max = 0;
        max = Math.max(0, helper(i+1, k, piles));
        
        for(int j=0; j<piles.get(i).size(); j++){
            if(j+1<=k)
                max = Math.max(max, piles.get(i).get(j)+ helper(i+1, k-j-1, piles));
        }
        
        return dp[i][k] = max;
    }
    
    public void prefixSum(List<Integer> pile){
        int m = pile.size();
        
        for(int i=1; i<m; i++){
            pile.set(i, pile.get(i-1) + pile.get(i));
        }
    }
}
