/*
Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

 

Example 1:
    Input: s = "abab"
    Output: true
    Explanation: It is the substring "ab" twice.

Example 2:
    Input: s = "aba"
    Output: false

Example 3:
    Input: s = "abcabcabcabc"
    Output: true
    Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 

Constraints:
    1 <= s.length <= 104
    s consists of lowercase English letters.
*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        
        for(int k=1; k<n; k++){
            if(n%k==0 && isPossible(s, k, n))
                return true;
        }
        
        return false;
    }
    
    public boolean isPossible(String s, int k, int n){
        if(k==n) return false;
        int i = 0;
        int j = k;
        // System.out.println(s);
        String ref = s.substring(i,j);
        i = j;
        j+=k;
        
        while(i<n){
            if(!ref.equals(s.substring(i,j)))
                return false;
            i = j;
            j+=k;
        }
        
        return true;
    }
}
