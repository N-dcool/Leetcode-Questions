class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int[] edgeCount = new int[n];
        int res = 0;

        for(int[] e : edges){
            edgeCount[e[0]]++;
            edgeCount[e[1]]++;
            uf.unionBySize(e[0], e[1]);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            int curParent = uf.findParent(i);
            map.put(curParent, map.getOrDefault(curParent, 0) + edgeCount[i]);
        }

        System.out.println(map);

        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            int parent = e.getKey();
            int totalEdges = e.getValue();
            int size = uf.size[parent];

            // System.out.println(parent + " " + totalEdges + " "+ size );

            if(totalEdges == size * (size-1)){
                res++;
            }
        }

        return res;
    }
}

class UnionFind{
    int n;
    int[] parent;
    int[] size;

    public UnionFind(int n){
        this.n = n;
        this.parent = new int[n];
        this.size = new int[n];

        for(int i=0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findParent(int node){
        if(parent[node] == node) return node;

        return parent[node] = findParent(parent[node]);
    }

    public void unionBySize(int u, int v){
        int pU = findParent(u);
        int pV = findParent(v);

        if(pU == pV) return;
        if(size[pU] < size[pV]){
            parent[pU] = pV;
            size[pV] += size[pU];
        } else{
            parent[pV] = pU;
            size[pU]+= size[pV];
        }
    }
}