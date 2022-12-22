/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates 
that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of 
the distances between the ith node in the tree and all other nodes.

 
Example 1:
      Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
      Output: [8,12,6,10,10,10]
      Explanation: The tree is shown above.
      We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
      equals 1 + 1 + 2 + 2 + 2 = 8.
      Hence, answer[0] = 8, and so on.

Example 2:
    Input: n = 1, edges = []
    Output: [0]

Example 3:
    Input: n = 2, edges = [[1,0]]
    Output: [1,1]
 

Constraints:
    1 <= n <= 3 * 104
    edges.length == n - 1
    edges[i].length == 2
    0 <= ai, bi < n
    ai != bi
    The given input represents a valid tree.
*/

class Solution {
    int[] ans, count;
    List<Set<Integer>> graph;
    int N;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        graph = new ArrayList<Set<Integer>>();
        ans = new int[N];
        count = new int[N];
        Arrays.fill(count, 1);

        for (int i = 0; i < N; ++i)
            graph.add(new HashSet<Integer>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                dfs(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
    }

    public void dfs2(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                ans[child] = ans[node] - count[child] + N - count[child];
                dfs2(child, node);
            }
    }
}
