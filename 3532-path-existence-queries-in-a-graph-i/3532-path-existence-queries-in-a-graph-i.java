class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DisjoinSet ds = new DisjoinSet(n);

        for(int i=1; i<n; i++) {
            if(nums[i-1]+maxDiff >= nums[i]){
                ds.union(i-1, i);
            }
        }

        int m = queries.length;
        boolean[] res = new boolean[m];

        for(int i=0; i<m; i++) {
            res[i] = ds.haveSameParent(queries[i][0], queries[i][1]);
        }

        return res;
        
    }

    class DisjoinSet {
        private int[] parent;
        private int[] rank;

        public DisjoinSet(int n) {
            this.parent = new int[n];
            this.rank = new int[n];

            for(int i=0; i<n; i++) {
                parent[i] = i;
            }
        }

        public void union(int u, int v) {
            int pU = findParent(u);
            int pV = findParent(v);

            if(pU == pV) return;

            if(rank[pU] > rank[pV]) {
                parent[pV] = pU;
                rank[pU]++;
            } else{
                parent[pU] = pV;
                rank[pV]++;
            }
        }

        private int findParent(int child) {
            if(parent[child] == child) return child;

            return parent[child] = findParent(parent[child]);
        }

        public boolean haveSameParent(int u, int v) {
            return findParent(u) == findParent(v);
        }

    }
}