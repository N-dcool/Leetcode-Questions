/*
Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
 

Example 1:
    Input: columnNumber = 1
    Output: "A"

Example 2:
    Input: columnNumber = 28
    Output: "AB"

Example 3:
    Input: columnNumber = 701
    Output: "ZY"
 

Constraints:
    1 <= columnNumber <= 231 - 1
*/

class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        
        while(columnNumber > 0){
            columnNumber--;
            
            sb.append((char)('A' + (columnNumber%26)));
            columnNumber /= 26;
        }
        
        return sb.reverse().toString();
    }
}

class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        
        if(columnNumber <= 26){
            sb.append((char)('@' + columnNumber));
            return sb.toString();
        }
        
        int d = columnNumber/26;
        if(columnNumber%26==0){
            sb.append(convertToTitle(d-1));
            sb.append(convertToTitle(26));
            return sb.toString();
        }
        
        sb.append(convertToTitle(d));
        sb.append(convertToTitle(columnNumber - d*26));
        
        return sb.toString();
    }
}
