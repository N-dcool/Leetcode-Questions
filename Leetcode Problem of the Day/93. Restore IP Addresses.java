/*
A valid IP address consists of exactly four integers separated by single dots. Each integer is 
between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" 
and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by 
inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 

Example 1:
    Input: s = "25525511135"
    Output: ["255.255.11.135","255.255.111.35"]
Example 2:
    Input: s = "0000"
    Output: ["0.0.0.0"]
Example 3:
    Input: s = "101023"
    Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

Constraints:
    1 <= s.length <= 20
    s consists of digits only.
*/


class Solution {
    String s;
    List<String> ans = new ArrayList<>();
    public List<String> restoreIpAddresses(String str) {
        s = str;
        
        helper(0, 0,"");
        
        return ans;
    }
    
    public void helper(int i, int dots, String sb){
        if(dots>4)
            return;
        if(i == s.length() && dots == 4){
            ans.add(sb.substring(0, sb.length()-1));
            return;
        }
        
        for(int length=1; length<=3 && length+i <=s.length(); length++){
            
            if(isValid(s, i, length))
                helper(i+length, dots+1, sb + s.substring(i, i+length) + "." );
        }
        
    }
    
    public boolean isValid(String s, int i, int length){
        if(length == 1)
            return true;
        if(length == 2 && s.charAt(i)!='0')
            return true;
        if(length == 3 && s.charAt(i)!='0' && s.substring(i, i+3).compareTo("255")<=0)
            return true;
        
        return false;
    }
}
