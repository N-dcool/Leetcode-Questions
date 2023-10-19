/*
Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

 
Example 1:
    Input: s = "ab#c", t = "ad#c"
    Output: true
    Explanation: Both s and t become "ac".

Example 2:
    Input: s = "ab##", t = "c#d#"
    Output: true
    Explanation: Both s and t become "".

Example 3:
    Input: s = "a#c", t = "b"
    Output: false
    Explanation: s becomes "c" while t becomes "b".
 

Constraints:
    1 <= s.length, t.length <= 200
    s and t only contain lowercase letters and '#' characters.
     

    Follow up: Can you solve it in O(n) time and O(1) space?
*/

class Solution {
    public boolean backspaceCompare(String s, String t) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        int n = s.length();
        int m = t.length();
        int max = Math.max(n,m);
        int i = 0;
        while(i<max){
            if(i<n){
                if(s.charAt(i) == '#'){
                    sb1.setLength(Math.max(0, sb1.length()-1));
                }else sb1.append(s.charAt(i));
            }
            if(i<m){
                if(t.charAt(i) == '#'){
                    sb2.setLength(Math.max(0, sb2.length()-1));
                }else sb2.append(t.charAt(i));
            }
            i++;
        }
        
        
        return sb1.compareTo(sb2) == 0;
    }
}
