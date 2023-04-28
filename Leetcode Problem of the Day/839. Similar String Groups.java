/*
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. 
Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar,
but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}. 
Notice that "tars" and "arts" are in the same group even though they are not similar. 
Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?

 
Example 1:
    Input: strs = ["tars","rats","arts","star"]
    Output: 2

Example 2:
    Input: strs = ["omv","ovm"]
    Output: 1
 

Constraints:
    1 <= strs.length <= 300
    1 <= strs[i].length <= 300
    strs[i] consists of lowercase letters only.
    All words in strs have the same length and are anagrams of each other.
*/

class Solution {
    int n;
    public int numSimilarGroups(String[] strs) {
        n = strs.length;
        int ans = 0;
        
        HashSet<String> set = new HashSet<>();
        for(String s : strs){
            if(!set.contains(s)){
                dfs(s, strs, set);
                ans++;
            }
        }
        
        return ans;
    }

    public boolean areSimilar(String s1, String s2){
        int count = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) count++;
            if(count > 2)
                return false;
        }
        
        return count==2 || count==0;
    }
    
    public void dfs(String curr, String[] strs, HashSet<String> set){
        if(set.contains(curr))
            return;
        set.add(curr);
        
        for(int i=0; i<n; i++){
            if(areSimilar(curr, strs[i])){
                dfs(strs[i], strs, set);
            }
        }
    }
}
