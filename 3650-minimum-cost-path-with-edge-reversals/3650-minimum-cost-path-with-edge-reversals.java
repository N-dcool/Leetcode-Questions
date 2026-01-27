class Solution {

    public int minCost(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int w = e[2];
            g[x].add(new int[] { y, w });
            g[y].add(new int[] { x, 2 * w });
        }

        // Dijkstra
        int[] d = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
        pq.offer(new int[] { 0, 0 }); // [Distance, Node]

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int dist = current[0];
            int x = current[1];

            if (x == n - 1) {
                return dist;
            }

            if (visited[x]) {
                continue;
            }
            visited[x] = true;

            for (int[] neighbor : g[x]) {
                int y = neighbor[0];
                int w = neighbor[1];

                if (dist + w < d[y]) {
                    d[y] = dist + w;
                    pq.offer(new int[] { d[y], y });
                }
            }
        }

        return -1;
    }
}