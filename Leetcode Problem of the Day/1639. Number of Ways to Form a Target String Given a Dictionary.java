/*
You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. 
In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.

 
Example 1:
    Input: words = ["acca","bbbb","caca"], target = "aba"
    Output: 6
    Explanation: There are 6 ways to form target.
    "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
    "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
    "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
    "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
    "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
    "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")

Example 2:
    Input: words = ["abba","baab"], target = "bab"
    Output: 4
    Explanation: There are 4 ways to form target.
    "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
    "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
    "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
    "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")


Constraints:
    1 <= words.length <= 1000
    1 <= words[i].length <= 1000
    All strings in words have the same length.
    1 <= target.length <= 1000
    words[i] and target contain only lowercase English letters.
*/

class Solution {
    public int numWays(String[] words, String target) {
        int n = words[0].length();
        int m = target.length();
        int mod = 1000000007;
        int[] dp = new int[m+1];
        dp[0] = 1;
        
        int[][] count = new int[n][26];
        for (String word : words) {
            for (int i = 0; i < n; i++) {
                count[i][word.charAt(i) - 'a']++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = m-1; j >= 0; j--) {
                dp[j+1] += (int)((long)dp[j] * count[i][target.charAt(j) - 'a'] % mod);
                dp[j+1] %= mod;
            }
        }
        
        return dp[m];
    }
}

/*
class Solution {
    int n,m;
    int mod = 1000000007;
    Integer[][] dp;
    
    public int numWays(String[] words, String target) {
        n = words.length;
        m = words[0].length();
        dp = new Integer[n+1][target.length()+1];
        
        int[][] storeFreq = new int[m][26];
        
        int idx = 0;
        for(int j=0; j<m; j++){
            int[] freq = new int[26]; 
            for(int i=0; i<n; i++){
                char c = words[i].charAt(j);
                freq[c - 'a']++;
            }
            storeFreq[idx++] = freq;
            
                // for(int f : freq)
                //     System.out.print(f + "  ");
                // System.out.println();
            
        }
        
        return solve(0, 0, storeFreq, target);
    }
    
    public int solve(int i, int j, int[][] store, String target){
        if(j==target.length())
            return 1;
        if(i==m)
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        
        long ans = 0;
        long ans2 = (store[i][target.charAt(j) - 'a'] * solve(i+1, j+1, store, target));
        long ans1 = solve(i+1, j, store, target);
        System.out.println(ans1 + ans2);
                                                         
        return dp[i][j] = (int)((ans1 + ans2)%mod);                                                
    }
}
*/
