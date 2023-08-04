/*
You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1.
The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.

You are also given a string s of length n, where s[i] is the character assigned to the edge between i and parent[i]. s[0] can be ignored.

Return the number of pairs of nodes (u, v) such that u < v and the characters assigned to edges on the path from u to v can be rearranged to form a palindrome.

A string is a palindrome when it reads the same backwards as forwards.

 

Example 1:
    Input: parent = [-1,0,0,1,1,2], s = "acaabc"
    Output: 8
    Explanation: The valid pairs are:
    - All the pairs (0,1), (0,2), (1,3), (1,4) and (2,5) result in one character which is always a palindrome.
    - The pair (2,3) result in the string "aca" which is a palindrome.
    - The pair (1,5) result in the string "cac" which is a palindrome.
    - The pair (3,5) result in the string "acac" which can be rearranged into the palindrome "acca".

Example 2:
    Input: parent = [-1,0,0,0,0], s = "aaaaa"
    Output: 10
    Explanation: Any pair of nodes (u,v) where u < v is valid.
 

Constraints:
    n == parent.length == s.length
    1 <= n <= 105
    0 <= parent[i] <= n - 1 for all i >= 1
    parent[0] == -1
    parent represents a valid tree.
    s consists of only lowercase English letters.
*/


class Solution {
    HashMap<Integer, Integer> map;
    HashMap<Integer, List<Pair>> graph;
    int[] xr;
    
    public record Pair(int node, char ch){}
    
    public long countPalindromePaths(List<Integer> parent, String s) {
        map = new HashMap<>();
        graph = new HashMap<>();
        int n = parent.size();
        xr = new int[n];
        
        for(int i=1; i<n; i++){
            int p = parent.get(i);
            int child = i;
            
            graph.computeIfAbsent(p, k-> new ArrayList<>()).add(new Pair(child, s.charAt(i)));
        }
        
         // System.out.println(graph);
        
        dfs(0,0);
        
        // System.out.println(map);
        
        long ans = 0;
        
        for(int x : xr){
            for(int j=0; j<=26; j++){
                int mask = x ^ (1 << j);
                ans += map.getOrDefault(mask,0);
            }
            ans += map.get(x) - 1;
        }
        
        return ans/2;
    }
    public void dfs(int curNode, int mask){
        map.put(mask, map.getOrDefault(mask, 0)+1);
        xr[curNode] = mask;
        
        // System.out.println(curNode);
        if(!graph.containsKey(curNode)) return;
        
        for(Pair child : graph.get(curNode)){
            int id = child.ch - 'a';
            
            dfs(child.node , mask ^ (1 << id));
        }
    }
}
