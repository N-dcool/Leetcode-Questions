/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


 

Example 1:
    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
    Input: digits = ""
    Output: []

Example 3:
    Input: digits = "2"
    Output: ["a","b","c"]
 

Constraints:
    0 <= digits.length <= 4
    digits[i] is a digit in the range ['2', '9'].
*/

class Solution {
    int n;
    List<String> ans;
    public List<String> letterCombinations(String digits) {
        n = digits.length();
        ans = new ArrayList<>();
        if(n==0)
            return ans;
        
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', ("abc").toCharArray());
        map.put('3', ("def").toCharArray());
        map.put('4', ("ghi").toCharArray());
        map.put('5', ("jkl").toCharArray());
        map.put('6', ("mno").toCharArray());
        map.put('7', ("pqrs").toCharArray());
        map.put('8', ("tuv").toCharArray());
        map.put('9', ("wxyz").toCharArray());
        
        helper(0, digits, map, new StringBuilder());
        
        return ans;
    }
    
    public void helper(int idx, String digits, HashMap<Character, char[]> map, StringBuilder sb){
        if(idx==n){
            ans.add(sb.toString());
            return;
        }
        
        for(char c : map.get(digits.charAt(idx))){
            helper(idx+1, digits, map, sb.append(c));
            sb.setLength(sb.length()-1);
        }
    }
}
