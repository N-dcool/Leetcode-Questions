class Solution {
    
    private record Pair(int node, int val) {}

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Pair[] arr = new Pair[n];

        // 1. Store original node with its value
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(i, nums[i]);
        }

        // 2. Sort by value because edge depends on value difference
        Arrays.sort(arr, (a, b) -> Integer.compare(a.val, b.val));

        // 3. Map original node index -> sorted position
        int[] nodeToIndex = new int[n];

        for (int i = 0; i < n; i++) {
            Pair p = arr[i];
            nodeToIndex[p.node] = i;
        }

        /* logging
        for(Pair p : arr) System.out.print(p + " ");
        System.out.println();
        for(int id : nodeToIndex) System.out.print(id + " ");

        eg.
        Pair[node=2, val=1] Pair[node=1, val=3] Pair[node=0, val=5] Pair[node=3, val=9] Pair[node=4, val=10] 
        2 1 0 3 4 
        */

        int[] comp = new int[n];
        int compId = 0;
        comp[0] = compId;

        for(int i=1; i<n; i++) {
            int gap = arr[i].val - arr[i-1].val;
            if(gap > maxDiff) compId++;

            comp[i] = compId;
        }

        int[] jump = new int[n];
        int right = 0;

        for(int left=0; left<n; left++) {
            while(right<n && arr[right].val - arr[left].val <= maxDiff) {
                right++;
            }
            jump[left] = right-1;
        }

        int LOG = 1;
        while((1<<LOG) <= n){
            LOG++;
        }

        int[][] up = new int[LOG][n];
        for(int i=0; i<n; i++){
            up[0][i] = jump[i];
        }

        for(int k=1; k<LOG; k++) {
            for(int i=0; i<n; i++) {
                int mid = up[k-1][i];
                up[k][i] = up[k-1][mid];
            }
        }

        int m = queries.length;
        int[] res = new int[m];

        for(int i=0; i<m; i++){
            int u = queries[i][0];
            int v = queries[i][1];

            int a = nodeToIndex[u];
            int b = nodeToIndex[v];

            if(a==b) continue;

            if(a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if(comp[a] != comp[b]){
                res[i] = -1;
                continue;
            }

            int curr = a;
            int ans = 0;
            for(int k = LOG-1; k>=0; k--) {
                if(up[k][curr] < b) {
                    curr = up[k][curr];
                    ans += 1<<k;
                }
            }
            res[i] = ans + 1;
        }


        return res;
    }
}
/*
[0,1,2,3,4] -> [0,4,2,3,1]
[1,8,3,4,2] -> [1,2,3,4,8]
*/