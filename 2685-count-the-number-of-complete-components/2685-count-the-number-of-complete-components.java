class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] e : edges) {
            graph.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], k -> new ArrayList<>()).add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int count = 0;

        for(int i=0; i<n; i++) {
            if(!vis[i] && bfs(i, graph, vis)) {
                count++;
            }
        }

        return count;
    }

    private boolean bfs(int node, HashMap<Integer, List<Integer>> graph, boolean[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        vis[node] = true;
        int totalNodes = 0;
        int totalEdges = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();
            totalNodes++;

            for(int next : graph.getOrDefault(cur, Collections.emptyList())) {
                totalEdges++;
                if(!vis[next]) {
                    q.add(next);
                    vis[next] = true;
                }
            }
        }

        return totalNodes * (totalNodes - 1) == totalEdges;
    }
}