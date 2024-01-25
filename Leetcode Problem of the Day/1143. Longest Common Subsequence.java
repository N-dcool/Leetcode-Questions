/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
*/

class Solution {
    Integer[][] dp;
    public int longestCommonSubsequence(String text1, String text2) {
        dp = new Integer[text1.length()][text2.length()];
        return solve(0,0,text1,text2);
    }
    
    public int solve(int i, int j, String t1, String t2){
        if(i==t1.length() || j==t2.length()) return 0;
        if(dp[i][j]!=null) return dp[i][j];
        int take = 0;
        if(t1.charAt(i) == t2.charAt(j)) take = 1 + solve(i+1, j+1, t1, t2);
        
        return dp[i][j] = Math.max(solve(i+1, j, t1, t2), Math.max(solve(i, j+1, t1, t2), take));
    }
}
