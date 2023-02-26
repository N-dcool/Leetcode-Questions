/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation: 
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')

Example 2:
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation: 
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')
 

Constraints:
    0 <= word1.length, word2.length <= 500
    word1 and word2 consist of lowercase English letters.
*/

class Solution {
    int n,m;
    Integer[][] dp;
    public int minDistance(String word1, String word2) {
        n = word1.length();
        m =  word2.length();
        
        if(n==0 || m==0)
            return Math.max(n,m);
        
        dp = new Integer[n+1][m+1];
        
        return dfs(0,0,word1, word2);
    }
    
    public int dfs(int i, int j, String a, String b){
        //Base Case:
        if(i == n)
            return m-j;
        if(j == m)
            return n-i;
        if(dp[i][j]!=null)
            return dp[i][j];
        int res;
        if(a.charAt(i)==b.charAt(j))
            res =  dfs(i+1, j+1, a ,b);
        else{
            int insert = dfs(i, j+1, a, b);
            int delete = dfs(i+1, j, a, b);
            int replace = dfs(i+1, j+1, a, b);
            
            res = 1 + Math.min(replace, Math.min(delete, insert));
        }
        
        return dp[i][j] = res;
    }
}
