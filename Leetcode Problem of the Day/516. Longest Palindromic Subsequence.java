/*
Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no 
elements without changing the order of the remaining elements.

 

Example 1:
    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".
 

Constraints:
    1 <= s.length <= 1000
    s consists only of lowercase English letters.
*/

class Solution {
    Integer[][] dp;
    public int longestPalindromeSubseq(String s) {
        int i = 0;
        int j = s.length()-1;
        dp = new Integer[j+1][j+1];
        
        return helper(i, j, s);
    }
    
    public int helper(int i, int j, String s){
        if(i > j)
            return 0;
        if(i==j)
            return 1;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(s.charAt(i)==s.charAt(j))
            return dp[i][j] = 2 + helper(i+1, j-1, s);
        
        return dp[i][j] = Math.max(helper(i+1, j, s), helper(i, j-1, s));
        
    }
}



//                     LTE : 


class Solution {
    int ans = 0;
    int n;
    public int longestPalindromeSubseq(String s) {
        n = s.length();
        helper(0, new StringBuilder(), s.toCharArray());
        
        return ans;
    }
    
    public void helper(int i, StringBuilder sb,char[] s){
        if(i==n && isPalindrome(sb) && sb.length() > ans){
            ans = sb.length();
            return;
        }
        if(i==n)
            return;
        
        helper(i+1, sb, s);
        sb.append(s[i]);
        helper(i+1, sb, s);
        sb.setLength(sb.length()-1);
    }
    
    public boolean isPalindrome(StringBuilder sb){
        int l = 0;
        int r = sb.length()-1;
        
        while(l<r){
            if(sb.charAt(l)!=sb.charAt(r))
                return false;
            l++;
            r--;
        }
        
        return true;
    }
}
