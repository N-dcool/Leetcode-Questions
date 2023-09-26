/*
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the 
smallest in lexicographical order among all possible results.

Example 1:
    Input: s = "bcabc"
    Output: "abc"

Example 2:
    Input: s = "cbacdcbc"
    Output: "acdb"
 

Constraints:
    1 <= s.length <= 104
    s consists of lowercase English letters.
 
Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/

class Solution {
    public String removeDuplicateLetters(String str) {
        int n = str.length();
        int[] position = new int[26];
        
        int j = 0;
        for(char c : str.toCharArray())
            position[c-'a'] = j++;
        
        int mask = 0;
        Stack<Character> s = new Stack<>();
        
      
        for(int i=0; i<n; i++){
            char c = str.charAt(i);
            
            if((mask&(1<<(c-'a'))) != 0) continue;
            
            while(!s.isEmpty() && s.peek() > c && i < position[s.peek()-'a']){
                mask^=1<<(s.pop()-'a');
            }
            s.add(c);
            mask|=1<<(c-'a');
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!s.isEmpty())
            sb.append(s.pop());
        
        return sb.reverse().toString();
    }
}
