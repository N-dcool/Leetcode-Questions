/*
Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

 

Example 1:
    Input: s1 = "sea", s2 = "eat"
    Output: 231
    Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
    Deleting "t" from "eat" adds 116 to the sum.
    At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

Example 2:
    Input: s1 = "delete", s2 = "leet"
    Output: 403
    Explanation: Deleting "dee" from "delete" to turn the string into "let",
    adds 100[d] + 101[e] + 101[e] to the sum.
    Deleting "e" from "leet" adds 101[e] to the sum.
    At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
    If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 

Constraints:
    1 <= s1.length, s2.length <= 1000
    s1 and s2 consist of lowercase English letters.
*/

class Solution {
    Integer[][] dp;
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
 
        dp = new Integer[n+1][m+1];
        
        return helper(n-1, m-1, s1.toCharArray(), s2.toCharArray());
    }
    
    public int helper(int i, int j, char[] s1, char[] s2){
        if(i<0){
            int rem = 0;
            for(int k=0; k<=j; k++)
                rem += s2[k];
            
            return rem;
        }
        if(j<0){
            int rem = 0;
            for(int k=0; k<=i; k++)
                rem += s1[k];
            
            return rem;
        }
             
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(s1[i] == s2[j])
            return dp[i][j] = 0 + helper(i-1, j-1, s1, s2);
        
        int a = s1[i] + helper(i-1, j, s1, s2);
        int b = s2[j] + helper(i, j-1, s1, s2);
        int c = s1[i] + s2[j] + helper(i-1, j-1, s1, s2);

        return dp[i][j] = Math.min(a, Math.min(b,c));
    }
}
