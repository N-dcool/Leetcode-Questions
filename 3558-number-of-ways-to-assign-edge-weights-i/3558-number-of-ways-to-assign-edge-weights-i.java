class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length;
        boolean[] vis = new boolean[n+1];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for(int[] edge : edges){
            map.computeIfAbsent(edge[0], k-> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k-> new ArrayList<>()).add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[0] = true;
        int depth = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-->0) {
                int cur = q.poll();

                for(int next : map.getOrDefault(cur, new ArrayList<>())) {
                    if(!vis[next-1]) {
                        q.add(next);
                        vis[next-1] = true;
                    }
                }
            }
            depth++;
        }

        System.out.println(depth);

        return (int)(modPow(2, depth-2));

    }

    private static final int MOD = 1_000_000_007;

    private long modPow(long base, long exp) {
        long ans = 1;

        while(exp > 0) {
            if((exp & 1) == 1) {
                ans = ans * base % MOD;
            }

            base = base * base % MOD;
            exp >>= 1;
        }

        return ans;
    }
}