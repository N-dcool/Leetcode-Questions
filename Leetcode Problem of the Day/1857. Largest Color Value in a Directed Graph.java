/*
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

 

Example 1:
    Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
    Output: 3
    Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Example 2:
    Input: colors = "a", edges = [[0,0]]
    Output: -1
    Explanation: There is a cycle from 0 to 0.
 

Constraints:
    n == colors.length
    m == edges.length
    1 <= n <= 105
    0 <= m <= 105
    colors consists of lowercase English letters.
    0 <= aj, bj < n
*/

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int k = 26;
        int[] indegrees = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            indegrees[v]++;
        }
        Set<Integer> zero_indegree = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                zero_indegree.add(i);
            }
        }
        int[][] counts = new int[n][k];
        for (int i = 0; i < n; i++) {
            counts[i][colors.charAt(i) - 'a']++;
        }
        int max_count = 0;
        int visited = 0;
        while (!zero_indegree.isEmpty()) {
            int u = zero_indegree.iterator().next();
            zero_indegree.remove(u);
            visited++;
            for (int v : graph.get(u)) {
                for (int i = 0; i < k; i++) {
                    counts[v][i] = Math.max(counts[v][i], counts[u][i] + (colors.charAt(v) - 'a' == i ? 1 : 0));
                }
                indegrees[v]--;
                if (indegrees[v] == 0) {
                    zero_indegree.add(v);
                }
            }
            max_count = Math.max(max_count, Arrays.stream(counts[u]).max().getAsInt());
        }
        return visited == n ? max_count : -1;
    }
}
