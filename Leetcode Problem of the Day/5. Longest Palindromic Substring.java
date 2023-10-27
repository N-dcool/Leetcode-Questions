/*
Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
*/

class Solution {
    int max=0,start=0,end=0;
    public String longestPalindrome(String s) {
        int n = s.length();
        
        for(int i=0; i<n; i++){
            solve(i, i, s, n);
            solve(i,i+1, s, n);
        }
        
        return s.substring(start+1,end);
    }
    
    public void solve(int i, int j, String s, int n){
        while(i>=0 && j<n){
            if(s.charAt(i) == s.charAt(j)){
                i--;
                j++;
            }else break;
        }
        
        int len = j-i+1;
        if(max < len){
            start = i;
            end = j;
            max = len;
        }
    }
}
