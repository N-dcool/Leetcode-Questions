/*
Given a string s, 
find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or 
no elements without changing the order of the remaining elements.

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

class Explanation😄 {
      /*
      1st reversing the given string and then because if you reverse the string and then find the longest common 
      subsequence rather than finding longest palindromic subseq.
      lets take an example :

       str =    b b a b c b c a b         (longest palindrome = "babcbab") 
                |   | | | |   | |
            
     revStr =   b a c b c b a b b
                | |   | | | |   |  
                
            
       now, the question become similar to that of 
       finding " LONGEST COMMON SUBSEQUENCE ".
     
      */
}
  
//using BottomUp approach (Recursive) :
  class Solution {
    Integer[][] dp;
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        
        return longestCommonSubseq(s, sb.toString(), s.length());
    }
    
    public int longestCommonSubseq(String s1, String s2, int n){
        dp = new Integer[n+1][n+1];
        
        return helper(n-1, n-1, s1, s2);
    }
    
    public int helper(int i, int j, String s1, String s2){
        if(i<0 || j<0)
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(s1.charAt(i)==s2.charAt(j))
            return dp[i][j] = 1 + helper(i-1, j-1, s1, s2);
        else
            return dp[i][j] = Math.max(helper(i, j-1, s1, s2), helper(i-1, j, s1, s2));
    }
}

//.         Index shifting :

class Solution {
    Integer[][] dp;
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        
        return longestCommonSubseq(s, sb.toString(), s.length());
    }
    
    public int longestCommonSubseq(String s1, String s2, int n){
        dp = new Integer[n+1][n+1];
        
        return helper(n, n, s1, s2);
    }
    
    public int helper(int i, int j, String s1, String s2){
        if(i==0 || j==0)
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(s1.charAt(i-1)==s2.charAt(j-1))
            return dp[i][j] = 1 + helper(i-1, j-1, s1, s2);
        else
            return dp[i][j] = Math.max(helper(i, j-1, s1, s2), helper(i-1, j, s1, s2));
    }
}

//Using TopDown Tabulation (Iterative)

class Solution {
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        
        return longestCommonSubseq(s, sb.toString(), s.length());
    }
    
    public int longestCommonSubseq(String s1, String s2, int n){
        int[][] dp = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        
        return dp[n][n];
    }
}


//Space optimisation :

class Solution {
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        
        return longestCommonSubseq(s, sb.toString(), s.length());
    }
    
    public int longestCommonSubseq(String s1, String s2, int n){
        int[] prev = new int[n+1];
        
        for(int i=1; i<=n; i++){
            int[] cur = new int[n+1];
            
            for(int j=1; j<=n; j++){
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    cur[j] = 1 + prev[j-1];
                else
                    cur[j] = Math.max(cur[j-1], prev[j]);
            }
            
            prev = cur;
        }
        
        return prev[n];
    }
}

//Actual BottomUp (Proper solution 🫢):

class Solution{
    public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length()][s.length()];

            return longestPalindromeSubseq(0, s.length()-1, s, dp); 
        }

    public int longestPalindromeSubseq(int l, int r, String s, int[][] dp) {
            if (l == r) return 1;
            if (l > r) return 0;
            if (dp[l][r] != 0) return dp[l][r];

            if(s.charAt(l)==s.charAt(r))
                return dp[l][r] = 2 + longestPalindromeSubseq(l+1, r-1, s, dp);
            else
                return dp[l][r] = Math.max(longestPalindromeSubseq(l+1, r, s, dp), longestPalindromeSubseq(l, r-1, s, dp));

        

    }
}


// converted to tabulation :

class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] c = s.toCharArray();
        int n = c.length, max = 0;
        int[] dp = new int[n];
        for(int j = 0; j < n; j++) {
            dp[j] = 1;
            max = 0;
            for(int i = j-1; i >= 0; i--) {
                int len = dp[i];
                if(c[i] == c[j]) {
                    dp[i] = 2 + max;
                }
                max = Math.max(max, len);
            }
        }
        for(int len: dp) {
            max = Math.max(max, len);
        }
        return max;
    }
}
