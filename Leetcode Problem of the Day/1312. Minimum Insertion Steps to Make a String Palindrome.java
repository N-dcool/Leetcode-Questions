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

class Solution {
    Integer[][] dp;
    public int minInsertions(String s) {
        int n = s.length();
        dp = new Integer[n+1][n+1];
        
        return n - longestPalindrome(0, n-1, s);
    }
    
    public int longestPalindrome(int i, int j, String s){
        if(i==j)
            return 1;
        if(i>j)
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(s.charAt(i) == s.charAt(j))
            return dp[i][j] = 2 + longestPalindrome(i+1, j-1, s);
        
        return dp[i][j] = 0 + Math.max(longestPalindrome(i+1, j, s), longestPalindrome(i, j-1, s));
    }
}
