/*
You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes 
connected with node i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:
    Input: graph = [[1,2,3],[0],[0],[0]]
    Output: 4
    Explanation: One possible path is [1,0,2,0,3]

Example 2:
    Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
    Output: 4
    Explanation: One possible path is [0,1,4,2,3]
 

Constraints:
    n == graph.length
    1 <= n <= 12
    0 <= graph[i].length < n
    graph[i] does not contain i.
    If graph[a] contains b, then graph[b] contains a.
    The input graph is always connected.
*/

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if(n<2)
            return 0;
        int ans = 0, finalstate = (1<<n)-1;
        boolean vis[][] = new boolean[n][finalstate+1];
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i<n; i++)
        {
            q.add(new int[]{i, 1<<i});
        }

        while(!q.isEmpty())
        {
            int size = q.size();
            ans++;
            while(size-- > 0)
            {
                int node[] = q.remove();
                for(int i = 0; i<graph[node[0]].length; i++)
                {
                    int neighbor = graph[node[0]][i];
                    int newmask = node[1] | (1<<neighbor);
                    if(vis[neighbor][newmask])
                        continue;
                    if(newmask == finalstate)
                        return ans;    
                    vis[neighbor][newmask] = true;
                    q.add(new int[]{neighbor, newmask});    
                }
            }
        }
        return ans;
    }
}

class Solution {
    public record Tuple(int i, int dis, int mask){}
    
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int allNode = (1<<n)-1;
        
        HashSet<Pair<Integer,Integer>> set = new HashSet<>();
        Queue<Tuple> q = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            int mask = (1<<i);
            q.add(new Tuple(i,0,mask));
            set.add(new Pair(i,mask));
        }
        
        while(!q.isEmpty()){
            Tuple node = q.poll();
            
            int i = node.i;
            int curMask = node.mask;
            int dist = node.dis;
            for(int next : graph[i]){
                int newMask = curMask | (1<<next);
                
                if(newMask == allNode) return dist+1;
                else if(set.contains(new Pair(next,newMask))) continue;
                q.add(new Tuple(next, dist+1, newMask));
                set.add(new Pair(next, newMask));
            }
        }
        
        return 0;
    }
}
