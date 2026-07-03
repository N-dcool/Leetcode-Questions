class Solution {

    private record Node(int next, int cost) {}

    private int n;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.n = online.length;

        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];
        int max = 0;

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];

            if (online[a] && online[b]) {
                graph[a].add(new Node(b, w));
                indegree[b]++;
                max = Math.max(max, w);
            }
        }

        int[] topo = buildTopo(graph, indegree);

        int left = 0;
        int right = max;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canAchieve(mid, graph, topo, k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private int[] buildTopo(List<Node>[] graph, int[] indegree) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] topo = new int[n];
        int index = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topo[index++] = node;

            for (Node next : graph[node]) {
                indegree[next.next]--;

                if (indegree[next.next] == 0) {
                    queue.offer(next.next);
                }
            }
        }

        return topo;
    }

    private boolean canAchieve(int requiredScore, List<Node>[] graph, int[] topo, long k) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int node : topo) {
            if (dist[node] == INF) continue;

            for (Node next : graph[node]) {
                if (next.cost < requiredScore) continue;

                long newCost = dist[node] + next.cost;

                if (newCost < dist[next.next] && newCost <= k) {
                    dist[next.next] = newCost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}