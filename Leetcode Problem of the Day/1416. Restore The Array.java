/*
A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of
digits s and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.

Given the string s and the integer k, return the number of the possible arrays that can be printed as s using the mentioned program. 
Since the answer may be very large, return it modulo 109 + 7.

Example 1:
    Input: s = "1000", k = 10000
    Output: 1
    Explanation: The only possible array is [1000]

Example 2:
    Input: s = "1000", k = 10
    Output: 0
    Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.

Example 3:
    Input: s = "1317", k = 2000
    Output: 8
    Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
 

Constraints:
    1 <= s.length <= 105
    s consists of only digits and does not contain leading zeros.
    1 <= k <= 109
      
*/
      
 class Solution {
    int mod = 1_000_000_007;

    private int dfs(int[] dp, int start, String s, int k) {
        if (dp[start] != 0)
            return dp[start];

        if (start == s.length())
            return 1;

        if (s.charAt(start) == '0')
            return 0;

        int count = 0;
        for (int end = start; end < s.length(); ++end) {
            String currNumber = s.substring(start, end + 1);
            
            if (currNumber.length()>9 || Long.parseLong(currNumber) > k)
                break;
            count = (count + dfs(dp, end + 1, s, k)) % mod;
        }

        dp[start] = count;
        return count;
    }
    
    public int numberOfArrays(String s, int k) {
        int m = s.length();
        int[] dp = new int[m + 1];
        return dfs(dp, 0, s, k);
    }
}


/*
class Solution {
    int n;
    Integer[] dp;
    int mod = 1000000007;
    
    public int numberOfArrays(String s, int k) {
        n = s.length();
        dp = new Integer[n+1];

        return helper(0, s, k);
    }
    
    public int helper(int i, String s, int k){
        if(i==n)
            return 1;
        if(dp[i]!=null)
            return dp[i];
        
        int ans = 0;
        for(int j=i; j<n; j++){
            if(isPossible(s.substring(i,j+1), k))
                ans = (ans + helper(j+1, s, k))%mod;
        }
        
        return dp[i] = (ans)%mod;
    }
    
    public boolean isPossible(String s, int k){
        if(s.charAt(0) == '0')
            return false;
        if(s.length() > 9)
            return false;
        
        return k >= Long.parseLong(s);
    }
}
*/
