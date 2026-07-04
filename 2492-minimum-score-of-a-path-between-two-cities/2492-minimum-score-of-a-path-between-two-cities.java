class Solution {
    public int minScore(int n, int[][] roads) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();

        for(int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList<>()).add(new int[]{road[0], road[2]});
        }

        int min = Integer.MAX_VALUE;
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-->0) {
                int curNode = q.poll();
                for(int[] next : graph.getOrDefault(curNode, new ArrayList<>())) {
                    int nextNode = next[0];
                    int dist = next[1];

                    if(!vis[nextNode]) {
                        q.add(nextNode);
                        vis[nextNode] = true;
                    }

                    min = Math.min(min, dist);
                }
            }
        }

        return min;
    }
}