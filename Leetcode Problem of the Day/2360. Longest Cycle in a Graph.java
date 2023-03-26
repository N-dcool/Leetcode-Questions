/*
You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i].
If there is no outgoing edge from node i, then edges[i] == -1.

Return the length of the longest cycle in the graph. If no cycle exists, return -1.

A cycle is a path that starts and ends at the same node.

 

Example 1:
    Input: edges = [3,3,4,2,3]
    Output: 3
    Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
    The length of this cycle is 3, so 3 is returned.

Example 2:
    Input: edges = [2,-1,3,1]
    Output: -1
    Explanation: There are no cycles in this graph.
 

Constraints:
    n == edges.length
    2 <= n <= 105
    -1 <= edges[i] < n
    edges[i] != i
*/

class Solution {
    int ans = -1;
    int n;
    boolean[] visited;
    public int longestCycle(int[] edges) {
        HashMap<Integer, Integer> map = new HashMap<>();
        n = edges.length;
        visited = new boolean[n];
        
        for(int i=0; i<n; i++)
            map.put(i, edges[i]);
        
         for(int i=0; i<n; i++){
             helper(i, i, 0, map);
             visited[i] = true;
         }
        
        return ans;
    }
    
    public void helper(int startNode, int node, int count, HashMap<Integer, Integer> map){
        if(node==-1)
            return;
        if(visited[node])
            return;
        
        if(count!=0 && startNode == node){
            ans = Math.max(ans, count);
            return;
        }
        
        if(count >= n)
            return;
        
        if(map.containsKey(node)){
            count++;
            helper(startNode, map.get(node), count, map);
        }
        
        
    }
}
