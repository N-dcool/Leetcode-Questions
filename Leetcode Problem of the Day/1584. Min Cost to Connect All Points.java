/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the 
absolute value of val.

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
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
        int n = points.length;
        
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int x = points[i][0];
                int y = points[i][1];
                int x1 = points[j][0];
                int y1 = points[j][1];
                
                int dis = Math.abs(x-x1) + Math.abs(y-y1);
                
                pq.add(new int[]{i,j,dis});
                
            }
        }
        
        int min = 0;
        UnionFind ds = new UnionFind(n);
        
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            
            if(ds.findParent(node[0]) != ds.findParent(node[1])){
                min += node[2];
                ds.union(node[0], node[1]);
            }
        }
        
        return min;
    }
    
}

class UnionFind{
    int[] parent;
    public UnionFind(int n){
        parent = new int[n];
        
        for(int i=0; i<n; i++)
            parent[i] = i;
    }
    
    public int findParent(int i){
        if(parent[i]==i)
            return i;
        return parent[i] = findParent(parent[i]);
    }
    
    public void union(int i, int j){
        parent[findParent(i)] = parent[findParent(j)];
    }
}
