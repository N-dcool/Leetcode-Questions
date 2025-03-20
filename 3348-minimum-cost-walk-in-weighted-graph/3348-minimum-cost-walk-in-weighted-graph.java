class Solution {
        
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int m = query.length;
        int[] res = new int[m];
        
        UnionFind uf = new UnionFind(n);
        
        for(int[] edge : edges)
            uf.union(edge[0], edge[1]);
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int[] e : edges){
            int a = e[0];
            int b = e[1];
            int w = e[2];
            
            if(uf.findParent(a) == uf.findParent(b)){
                int parent = uf.parent[a];
                map.put(parent, map.getOrDefault(parent, w) & w);
            }
        }
        
        for(int i=0; i<m; i++){
            int a = query[i][0];
            int b = query[i][1];
        
            if(uf.findParent(a) == uf.findParent(b))
                res[i] = map.get(uf.parent[a]);
            else
                res[i] = -1;
        }
        
        
        return res;
        
    }
    
//     public void minWalk(int root, int parent, HashMap<Integer, List<Pair>> map, List<Integer> temp){
//         // if(!map.containsKey(root)) return;
        
//         for(Pair p : map.getOrDefault(root, new ArrayList<>())){
//             int node = p.child;
//             int weight = p.weight;
            
//             if(node != parent){
//                 temp.add(weight);
//                 minWalk(node, root, map, temp);
//             }
//         }
//     }
    
}

class UnionFind{
    int[] parent;
    int[] rank;
    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];
        
        for(int i=0; i<n; i++){
            parent[i] = i;
        } 
    }
    
    public int findParent(int node){
        if(parent[node] == node) return node;
        
        return parent[node] = findParent(parent[node]);
    }
    
    public void union(int a, int b){
        int aParent = findParent(a);
        int bParent = findParent(b);
        
        if(aParent == bParent) return;
        
        int aRank = rank[a];
        int bRank = rank[b];
        
        if(aRank < bRank)
            parent[aParent] = bParent;
        else if(aRank > bRank)
            parent[bParent] = aParent;
        else{
            parent[aParent] = bParent;
            rank[bParent]++;
        }
        
    }
}