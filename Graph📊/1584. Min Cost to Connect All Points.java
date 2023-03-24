/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:
    Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
    Output: 20
    Explanation: 
        We can connect the points as shown above to get the minimum cost of 20.
        Notice that there is a unique path between every pair of points.

Example 2:
    Input: points = [[3,12],[-2,5],[-4,1]]
    Output: 18
 

Constraints:
    1 <= points.length <= 1000
    -106 <= xi, yi <= 106
    All pairs (xi, yi) are distinct.
*/

class Solution {
    record Tuple(int distance, int x, int y){}
    
    public int minCostConnectPoints(int[][] points) {
        int n = points.length, ans = 0;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(
            (a,b) -> a.distance - b.distance
        );
        
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                pq.add(new Tuple(dis(points, i, j), i, j));
            }
        }
        
        UnionFind ds = new UnionFind(n);
        
        while(!pq.isEmpty()){
            Tuple t = pq.poll();
            if(ds.find(t.x) != ds.find(t.y)){
                ans += t.distance;
                ds.union(t.x, t.y);
            }
        }
        
        return ans;
    }
    
    public int dis(int[][] points, int a, int b){
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }
}

class UnionFind {
    int[] parent;
    
    public UnionFind(int n){
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
    }
    
    public void union(int a, int b){
        parent[find(a)] = parent[find(b)];
    }
    
    public int find(int x){
        if(parent[x]!=x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
}
