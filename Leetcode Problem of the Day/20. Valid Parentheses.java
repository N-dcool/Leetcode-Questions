/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:
    Input: s = "()"
    Output: true

Example 2:
    Input: s = "()[]{}"
    Output: true

Example 3:
    Input: s = "(]"
    Output: false
 

Constraints:
    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.
*/

class Solution {
    public boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        
        for(char c : str.toCharArray()){
            if(c == '(')
                s.add(')');
            else if(c == '{')
                s.add('}');
            else if(c == '[')
                s.add(']');
            else{
                if(s.isEmpty() || s.peek()!=c)
                    return false;
                s.pop();
            }
        }
        return s.isEmpty();
    }
}
