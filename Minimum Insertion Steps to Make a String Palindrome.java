/*
Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

  Input: s = "zzazz"
  Output: 0
  Explanation: The string "zzazz" is already palindrome we do not need any insertions.
  
Example 2:

  Input: s = "mbadm"
  Output: 2
  Explanation: String can be "mbdadbm" or "mdbabdm".
  
Example 3:

  Input: s = "leetcode"
  Output: 5
  Explanation: Inserting 5 characters the string becomes "leetcodocteel".
  
 
Constraints:
    1 <= s.length <= 500
    s consists of lowercase English letters.
    
*/

// TopDown aprroach:

class Solution {
    Integer[][] dp;
    public int minInsertions(String s) {
        int n = s.length();
        dp = new Integer[n+1][n+1];
        return n - longestPalindromeSubseq(0, n-1, s);
    }
    
    public int longestPalindromeSubseq( int i, int j, String s){
        if(i==j)
            return 1;
        if(i>j)
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(s.charAt(i)==s.charAt(j))
            return dp[i][j] = 2 + longestPalindromeSubseq(i+1, j-1, s);
        
        return dp[i][j] = 0 + Math.max(longestPalindromeSubseq(i, j-1, s), longestPalindromeSubseq(i+1, j, s));
    }
    
}

//BottomUp (Tabulation):

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i < n; ++i){
             for (int j = 0; j < n; ++j){
                 if(s.charAt(i) == s.charAt(n - 1 - j))
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                else
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
             }
                
        }
           
        return n - dp[n][n];
    }
}
