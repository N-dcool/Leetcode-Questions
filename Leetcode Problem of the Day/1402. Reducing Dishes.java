/*
A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied 
by its satisfaction level i.e. time[i] * satisfaction[i].

Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

 
Example 1:
    Input: satisfaction = [-1,-8,0,5,-9]
    Output: 14
    Explanation: After Removing the second and last dish, the maximum total like-time coefficient will be equal to (-1*1 + 0*2 + 5*3 = 14).
    Each dish is prepared in one unit of time.

Example 2:
    Input: satisfaction = [4,3,2]
    Output: 20
    Explanation: Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)

Example 3:
    Input: satisfaction = [-1,-4,-5]
    Output: 0
    Explanation: People do not like the dishes. No dish is prepared.
 

Constraints:
    n == satisfaction.length
    1 <= n <= 500
    -1000 <= satisfaction[i] <= 1000
*/

class Solution {
    Integer[][] dp;
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        
        dp = new Integer[satisfaction.length+1][satisfaction.length+1];
        
        return dfs(0, 1, satisfaction);
    }
    
    public int dfs(int i, int time, int[] sat){
        if(i==sat.length)
            return 0;
        if(dp[i][time]!=null)
            return dp[i][time];
        
        int take = sat[i]*time + dfs(i+1, time+1, sat);
        int notTake = dfs(i+1, time, sat);
        
        
        return dp[i][time] = Math.max(take, notTake);
    }
}
