class LCA {

    private int n;
    private int m;
    private int[] d;
    private List<Integer>[] e;
    private int[][] f;

    public LCA(int[][] edges, int root) {
        n = edges.length + 1;
        m = (int) (Math.log(n) / Math.log(2)) + 1;
        e = new ArrayList[n + 1];
        d = new int[n + 1];
        f = new int[n + 1][m];

        for (int i = 0; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            e[u].add(v);
            e[v].add(u);
        }

        dfs(root, 0);

        for (int i = 1; i < m; i++) {
            for (int x = 1; x <= n; x++) {
                f[x][i] = f[f[x][i - 1]][i - 1];
            }
        }
    }

    private void dfs(int x, int fa) {
        f[x][0] = fa;
        for (int y : e[x]) {
            if (y == fa) {
                continue;
            }
            d[y] = d[x] + 1;
            dfs(y, x);
        }
    }

    public int lca(int x, int y) {
        if (d[x] > d[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = m - 1; i >= 0; i--) {
            if (d[x] <= d[f[y][i]]) {
                y = f[y][i];
            }
        }

        if (x == y) {
            return x;
        }

        for (int i = m - 1; i >= 0; i--) {
            if (f[y][i] != f[x][i]) {
                x = f[x][i];
                y = f[y][i];
            }
        }

        return f[x][0];
    }

    public int dis(int x, int y) {
        return d[x] + d[y] - d[lca(x, y)] * 2;
    }
}

class Solution {

    private static final int MOD = 1000000007;
    private static final int N = 100010;
    private static int[] p2 = new int[N];

    static {
        p2[0] = 1;
        for (int i = 1; i < N; i++) {
            p2[i] = (int) (((long) p2[i - 1] * 2) % MOD);
        }
    }

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        LCA lca = new LCA(edges, 1);
        int m = queries.length;
        int[] res = new int[m];

        for (int i = 0; i < m; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            if (x != y) {
                res[i] = p2[lca.dis(x, y) - 1];
            }
        }

        return res;
    }
}