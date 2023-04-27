/*
There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer 
array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.

The price sum of a given path is the sum of the prices of all nodes lying on that path.

Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start the ith trip from the 
node starti and travel to the node endi by any path you like.

Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.

Return the minimum total price sum to perform all the given trips.

 

Example 1:
    Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
    Output: 23
    Explanation: The diagram above denotes the tree after rooting it at node 2. 
        The first part shows the initial tree and the second part shows the tree after choosing nodes 0, 2, and 3, and making their price half.
        For the 1st trip, we choose path [0,1,3]. The price sum of that path is 1 + 2 + 3 = 6.
        For the 2nd trip, we choose path [2,1]. The price sum of that path is 2 + 5 = 7.
        For the 3rd trip, we choose path [2,1,3]. The price sum of that path is 5 + 2 + 3 = 10.
        The total price sum of all trips is 6 + 7 + 10 = 23.
        It can be proven, that 23 is the minimum answer that we can achieve.

Example 2:
    Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
    Output: 1
    Explanation: The diagram above denotes the tree after rooting it at node 0. 
        The first part shows the initial tree and the second part shows the tree after choosing node 0, and making its price half.
        For the 1st trip, we choose path [0]. The price sum of that path is 1.
        The total price sum of all trips is 1. It can be proven, that 1 is the minimum answer that we can achieve.
 

Constraints:
    1 <= n <= 50
    edges.length == n - 1
    0 <= ai, bi <= n - 1
    edges represents a valid tree.
    price.length == n
    price[i] is an even integer.
    1 <= price[i] <= 1000
    1 <= trips.length <= 100
    0 <= starti, endi <= n - 1
*/

class Solution {
    List<Integer>[] tree;
    int n;
    Integer[][] dp;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        n = n;
        buildTree(n, edges);
        dp = new Integer[n+1][2];
        
        int[] nodeToCost = new int[n];
        for(int[] trip : trips){
            int[] nodeCount = new int[n];
            dfsForPathCost(trip[0], trip[1], -1, nodeCount);
            
            for(int i=0; i<n; i++){
                nodeToCost[i] += nodeCount[i]*price[i];
            }
        }
        
        return dfsAndDP(0, 0, -1, nodeToCost);
    }
    
    public int dfsAndDP(int i, int flag, int parent, int[] nodeToCost){
        int a = 0, b = 0;
        
        if(dp[i][flag]!=null)
            return dp[i][flag];
        
        for(int node : tree[i]){
            if(node != parent){
                a += dfsAndDP(node, 0, i, nodeToCost);
            }
        }
        
        for(int node : tree[i]){
            if(node != parent)
                b += dfsAndDP(node, 1, i, nodeToCost);
        }
        
        int ans = a + nodeToCost[i];
        if(flag == 0){
            ans = Math.min(ans, b + nodeToCost[i]/2);
        }
        
        return dp[i][flag] = ans;
    }
    
    public boolean dfsForPathCost(int cur, int target, int parent, int[] nodeCount){
        if(cur == target){
            nodeCount[target]++;
            return true;
        }
        
        for(int child : tree[cur]){
            if(child == parent) continue;
            if(dfsForPathCost(child, target, cur, nodeCount)){
                nodeCount[cur]++;
                return true;
            }
        }
        
        return false;
    }
    
    public void buildTree(int n, int[][] edges){
        tree = new ArrayList[n];
        
        for(int i=0; i<n; i++)
            tree[i] = new ArrayList<>();
        
        for(int[] edge : edges){
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
    }
    
}
