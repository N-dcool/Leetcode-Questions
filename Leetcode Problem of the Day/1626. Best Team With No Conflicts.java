/*
You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the
highest overall score. The score of the team is the sum of scores of all the players in the team.

However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a
strictly higher score than an older player. A conflict does not occur between players of the same age.

Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, 
respectively, return the highest overall score of all possible basketball teams.

 

Example 1:
    Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
    Output: 34
    Explanation: You can choose all the players.
    
Example 2:
    Input: scores = [4,5,6,5], ages = [2,1,2,1]
    Output: 16
    Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
    
Example 3:
    Input: scores = [1,2,3,5], ages = [8,9,10,1]
    Output: 6
    Explanation: It is best to choose the first 3 players. 
 

Constraints:
    1 <= scores.length, ages.length <= 1000
    scores.length == ages.length
    1 <= scores[i] <= 106
    1 <= ages[i] <= 1000
*/



class Solution {
    Integer[][] dp;
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        dp = new Integer[n+1][n+1];
        int[][] arr = new int[n][2];
        
        for(int i=0; i<n; i++)
            arr[i] = new int[]{scores[i], ages[i]};
        
        Arrays.sort(arr, (a,b)->a[1]==b[1]? a[0]-b[0] : a[1]-b[1]);
        
        return helper(0, -1, arr);
        
    }
    
    public int helper(int i, int prev, int[][] arr){
        if(i == arr.length)
            return 0;
        if(dp[i][prev+1] != null)
            return dp[i][prev+1];
        
        if(prev == -1 || arr[i][0]>=arr[prev][0])
            return dp[i][prev+1] =  Math.max(helper(i+1, prev, arr), arr[i][0] + helper(i+1, i, arr));
        
        return dp[i][prev+1] = helper(i+1, prev, arr);
    }
} 



