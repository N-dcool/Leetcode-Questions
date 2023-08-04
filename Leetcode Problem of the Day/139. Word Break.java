/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated 
sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:
    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
    Input: s = "applepenapple", wordDict = ["apple","pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
    Note that you are allowed to reuse a dictionary word.

Example 3:
    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    Output: false
 

Constraints:
    1 <= s.length <= 300
    1 <= wordDict.length <= 1000
    1 <= wordDict[i].length <= 20
    s and wordDict[i] consist of only lowercase English letters.
    All the strings of wordDict are unique.
*/

class Solution {
    Boolean[][] dp;
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(String word : wordDict)
            set.add(word);
    
        int n = s.length();
      
        dp = new Boolean[n+1][n+1];
        
        return helper(0, 0, n, s, set);
    }
    
    public boolean helper(int i, int j, int n, String s, Set<String> set){
        if(j==n){
            return set.contains(s.substring(i,j));
        }
        if(dp[i][j]!=null)
            return dp[i][j];
        
        if(set.contains(s.substring(i,j))){
            return dp[i][j] = helper(i, j+1, n, s, set) || helper(j, j+1, n, s, set);
        }
        return dp[i][j] = helper(i, j+1, n, s, set);
    }
}
