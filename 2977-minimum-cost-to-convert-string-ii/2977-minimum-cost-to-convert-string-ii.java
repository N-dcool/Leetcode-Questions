class Trie {

    Trie[] child = new Trie[26];
    int id = -1;
}

class Solution {

    private static final int INF = Integer.MAX_VALUE / 2;

    private int add(Trie node, String word, int[] index) {
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';
            if (node.child[i] == null) {
                node.child[i] = new Trie();
            }
            node = node.child[i];
        }
        if (node.id == -1) {
            node.id = ++index[0];
        }
        return node.id;
    }

    private void update(long[] x, long y) {
        if (x[0] == -1 || y < x[0]) {
            x[0] = y;
        }
    }

    public long minimumCost(
        String source,
        String target,
        String[] original,
        String[] changed,
        int[] cost
    ) {
        int n = source.length();
        int m = original.length;
        Trie root = new Trie();

        int[] p = { -1 };
        int[][] G = new int[m * 2][m * 2];

        for (int i = 0; i < m * 2; i++) {
            Arrays.fill(G[i], INF);
            G[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int x = add(root, original[i], p);
            int y = add(root, changed[i], p);
            G[x][y] = Math.min(G[x][y], cost[i]);
        }

        int size = p[0] + 1;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    G[i][j] = Math.min(G[i][j], G[i][k] + G[k][j]);
                }
            }
        }

        long[] f = new long[n];
        Arrays.fill(f, -1);
        for (int j = 0; j < n; j++) {
            if (j > 0 && f[j - 1] == -1) {
                continue;
            }
            long base = (j == 0 ? 0 : f[j - 1]);
            if (source.charAt(j) == target.charAt(j)) {
                f[j] = f[j] == -1 ? base : Math.min(f[j], base);
            }

            Trie u = root;
            Trie v = root;
            for (int i = j; i < n; i++) {
                u = u.child[source.charAt(i) - 'a'];
                v = v.child[target.charAt(i) - 'a'];
                if (u == null || v == null) {
                    break;
                }
                if (u.id != -1 && v.id != -1 && G[u.id][v.id] != INF) {
                    long newVal = base + G[u.id][v.id];
                    if (f[i] == -1 || newVal < f[i]) {
                        f[i] = newVal;
                    }
                }
            }
        }

        return f[n - 1];
    }
}