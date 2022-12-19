/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] 
denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, 
and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if
there is a valid path from source to destination, or false otherwise.

 

Example 1:
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2
    
Example 2:
    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
    Output: false
    Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:
      1 <= n <= 2 * 105
      0 <= edges.length <= 2 * 105
      edges[i].length == 2
      0 <= ui, vi <= n - 1
      ui != vi
      0 <= source, destination <= n - 1
      There are no duplicate edges.
      There are no self edges.
*/

//          BSF : 

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] edge: edges){
            int a = edge[0];
            int b = edge[1];
            
            if(!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            if(!graph.containsKey(b))
                graph.put(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        boolean[] visited = new boolean[n];
        visited[source] = true;
        
        while(!q.isEmpty()){
            int curNode = q.poll();
            if(destination == curNode)
                return true;
            for(int nextNode : graph.get(curNode)){
                if(!visited[nextNode]){
                    q.add(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
        
        return false;
    }
}


//.         Union and Find 

class Solution {
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DisjoinSet ds = new DisjoinSet(n);
        
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            if(ds.findUPar(u) != ds.findUPar(v))
                ds.unionByRank(u,v);
        }
        
        return ds.findUPar(source) == ds.findUPar(destination);
        
    }
}
class DisjoinSet{
    int[] parent;
    int[] rank;
    int[] size;
    public DisjoinSet(int n){
        parent = new int[n+1];
        rank = new int[n+1];
        size = new int[n+1];
        for(int i=0; i<=n; i++){
            parent[i]=i;
            size[i]=1;
        }
    }
    
    public int findUPar(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = findUPar(parent[x]);
    }
    
    public void unionByRank(int u, int v){
        int x = findUPar(u);
        int y = findUPar(v);
        
        if(x==y) return;
        if(rank[x] > rank[y])
            parent[y] = x;
        else if(rank[x] < rank[y])
            parent[x] = y;
        else{
            parent[x] = y;
            rank[y]++;
        }
    }
    
    public void unionBySize(int u, int v){
        int x = findUPar(u);
        int y = findUPar(v);
        
        if(x==y) return;
        if(size[x] > size[y]){
            parent[y] = x;
            size[x]+=size[y];
        }
        else{
            parent[x] = y;
            size[y]+=size[x];
        }
    }
}
