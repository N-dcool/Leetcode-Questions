/*
There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

 

Example 1:

Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
Example 2:

Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
Example 3:

Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]
 

Constraints:

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
*/

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        buildAdj(adj, adjacentPairs);
        
        int n = adj.size();
        int[] res = new int[n];
        
        int init = findStartingPoint(adj);
        
        dfs(0,init,Integer.MAX_VALUE,res,adj);
        
        return res;
    }
    
    public void dfs(int i, int cur, int prev, int[] res, HashMap<Integer, List<Integer>> adj){
        if(i==res.length) return;
        
        res[i] = cur;
        for(int next : adj.get(cur)){
            if(next!=prev){
                dfs(i+1, next, cur, res, adj);
            }
        }
    }
    
    public int findStartingPoint(HashMap<Integer, List<Integer>> adj){
        for(Map.Entry<Integer, List<Integer>> e : adj.entrySet())
            if(e.getValue().size() == 1)
                return e.getKey();
        
        return -1;
    }
    
    public void buildAdj(HashMap<Integer, List<Integer>> adj, int[][] adjacentPairs){
        for(int[] a : adjacentPairs){
            adj.computeIfAbsent(a[0], k->new ArrayList<>()).add(a[1]);
            adj.computeIfAbsent(a[1], k->new ArrayList<>()).add(a[0]);
        }
    }
}
