/*
You are given two strings s and t.

String t is generated by random shuffling string s and then add one more letter at a random position.

Return the letter that was added to t.

 

Example 1:
    Input: s = "abcd", t = "abcde"
    Output: "e"
    Explanation: 'e' is the letter that was added.

Example 2:
    Input: s = "", t = "y"
    Output: "y"
 

Constraints:
    0 <= s.length <= 1000
    t.length == s.length + 1
    s and t consist of lowercase English letters.
*/

class Solution {
    public char findTheDifference(String s, String t) {
        int total = 0;
        int n = s.length();
        
        for(int i=0; i<n; i++){
            total -= s.charAt(i);
            total += t.charAt(i);
        }
        
        total += t.charAt(n);
        
        return (char)(total);
    }
}

class Solution {
    public char findTheDifference(String s, String t) {
        int[] freq = new int[26];
        int n = s.length();
        
        for(int i=0; i<n; i++){
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        freq[t.charAt(n)-'a']--;
        
        for(int i=0; i<26; i++){
            if(freq[i] == -1)
                return (char)('a' + i);
        }
        
        return 'a';
    }
}
