/*
There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

 

Example 1:
    Input: s = "aaabbb"
    Output: 2
    Explanation: Print "aaa" first and then print "bbb".

Example 2:
    Input: s = "aba"
    Output: 2
    Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 

Constraints:
    1 <= s.length <= 100
    s consists of lowercase English letters.
*/

class Solution {
    Integer[][] dp;
    public int strangePrinter(String s) {
        int n = s.length();
        
        dp = new Integer[n+1][n+1];
        
        return helper(0, n-1, s.toCharArray());
    }
    
    public int helper(int i, int j, char[] s){
        if(i == j)
            return 1;
        if(dp[i][j] != null)
            return dp[i][j];
        
        int min = Integer.MAX_VALUE;
        
        for(int k=i; k<j; k++){
            min = Math.min(min, helper(i,k, s) + helper(k+1, j, s));
        }
        
        return dp[i][j] = (s[i] == s[j]) ? min-1 : min;
    }
}

class Solution {
    int dp[][];

    private int solve(String s, int n, int left, int right) {
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        dp[left][right] = n;
        int j = -1;
        for (int i = left; i < right; i++) {
            if (s.charAt(i) != s.charAt(right) && j == -1) {
                j = i;
            }
            
            if (j != -1) {
                dp[left][right] = Math.min(dp[left][right], 1 + solve(s, n, j, i) + solve(s, n, i + 1, right));
            }
        }
        
        if (j == -1) {
            dp[left][right] = 0;
        }
        
        return dp[left][right];
    }

    public int strangePrinter(String s) {
        int n = s.length();
        dp = new int[n][n];
        for (int left = 0; left < n; left++) {
            for (int right = 0; right < n; right++) {
                dp[left][right] = -1;
            }
        }

        return solve(s, n, 0, n - 1) + 1;
    }
}
