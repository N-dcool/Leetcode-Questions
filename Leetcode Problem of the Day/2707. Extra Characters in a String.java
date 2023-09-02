/*
You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings 
such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.

Return the minimum number of extra characters left over if you break up s optimally.

 

Example 1:
    Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
    Output: 1
    Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

Example 2:
    Input: s = "sayhelloworld", dictionary = ["hello","world"]
    Output: 3
    Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 

Constraints:
    1 <= s.length <= 50
    1 <= dictionary.length <= 50
    1 <= dictionary[i].length <= 50
    dictionary[i] and s consists of only lowercase English letters
    dictionary contains distinct words
*/

class Solution {
    HashSet<String> set;
    int n;
    Integer[] dp;
    public int minExtraChar(String s, String[] dictionary) {
        this.n = s.length();
        this.set = new HashSet<>();
        this.dp = new Integer[n+1];
        for(String d : dictionary) set.add(d);
        
        
        return helper(0, s);
    }
    
    public int helper(int idx, String s){
        if(idx==n) return 0;
        if(dp[idx]!=null)
            return dp[idx];
        
        int ans = 1 + helper(idx+1, s);
        for(int i=idx+1; i<=n; i++){
            if(set.contains(s.substring(idx, i)))
                ans = Math.min(ans, helper(i, s));
        }
        
        return dp[idx] = ans;
    }
    
    
}
