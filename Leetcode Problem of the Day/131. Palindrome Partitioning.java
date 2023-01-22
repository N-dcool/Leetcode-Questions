/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

 

Example 1:
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]

Example 2:
    Input: s = "a"
    Output: [["a"]]
 

Constraints:
    1 <= s.length <= 16
    s contains only lowercase English letters.
*/


class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        List<String> ds = new ArrayList<>();
        
        dfs(s, 0, ds, result);
        
        return result;
    }
    
    public void dfs(String s, int idx, List<String> ds, List<List<String>> result){
        if(idx >= s.length()){
            result.add(new ArrayList(ds)); 
        }
        
        for(int i=idx; i<s.length(); i++){
            if(isPalindrome(s, idx, i)){
                ds.add(s.substring(idx, i+1));
                dfs(s, i+1, ds, result);
                ds.remove(ds.size()-1);
            }
        }
    }
    
    public boolean isPalindrome(String s, int start, int end){
        while(start < end)
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        return true;
    }
}
