/*
Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3: 

Input: n = 5
Output: 68
 

Constraints:

1 <= n <= 2 * 10^4
*/

class Solution {
    HashMap<Character, char[]> store;
    int MOD = (int)1e9+7;
    Integer[][] dp;
    public int countVowelPermutation(int n) {
        store = new HashMap<>();
        dp = new Integer[n+1][7];
        store.put('a', new char[]{'e'});
        store.put('e', new char[]{'a', 'i'});
        store.put('i', new char[]{'a','e','o','u'});
        store.put('o', new char[]{'i', 'u'});
        store.put('u', new char[]{'a'});
        store.put('x', new char[]{'a','e','i','o','u'});
        
        return solve(n,'x');
    }
    
    public int solve(int i, char c){
        if(i==0) return 1;
        int key = getKey(c);
        if(dp[i][key] != null)
            return dp[i][key];
        
        long res = 0;
        for(char next : store.get(c)){
            res = (res + solve(i-1, next))%MOD;
        }
        
        return dp[i][key] = (int)res;
    }
    public int getKey(char c){
        if(c=='a') return 0;
        if(c=='e') return 1;
        if(c=='i') return 2;
        if(c=='o') return 3;
        if(c=='u') return 4;
        return 5;
    }
}
