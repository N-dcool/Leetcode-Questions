class Solution {

    class DisjointSet {

        int[] f;

        public DisjointSet(int m, int n) {
            f = new int[m * n];
            for (int i = 0; i < m * n; ++i) {
                f[i] = i;
            }
        }

        public int find(int x) {
            return x == f[x] ? x : (f[x] = find(f[x]));
        }

        public void merge(int x, int y) {
            f[find(x)] = find(y);
        }
    }

    int[][] grid;
    int m;
    int n;
    DisjointSet ds;

    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        ds = new DisjointSet(m, n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                handler(i, j);
            }
        }
        return ds.find(getId(0, 0)) == ds.find(getId(m - 1, n - 1));
    }

    public int getId(int x, int y) {
        return x * n + y;
    }

    public void detectL(int x, int y) {
        if (
            y - 1 >= 0 &&
            (grid[x][y - 1] == 4 || grid[x][y - 1] == 6 || grid[x][y - 1] == 1)
        ) {
            ds.merge(getId(x, y), getId(x, y - 1));
        }
    }

    public void detectR(int x, int y) {
        if (
            y + 1 < n &&
            (grid[x][y + 1] == 3 || grid[x][y + 1] == 5 || grid[x][y + 1] == 1)
        ) {
            ds.merge(getId(x, y), getId(x, y + 1));
        }
    }

    public void detectU(int x, int y) {
        if (
            x - 1 >= 0 &&
            (grid[x - 1][y] == 3 || grid[x - 1][y] == 4 || grid[x - 1][y] == 2)
        ) {
            ds.merge(getId(x, y), getId(x - 1, y));
        }
    }

    public void detectD(int x, int y) {
        if (
            x + 1 < m &&
            (grid[x + 1][y] == 5 || grid[x + 1][y] == 6 || grid[x + 1][y] == 2)
        ) {
            ds.merge(getId(x, y), getId(x + 1, y));
        }
    }

    public void handler(int x, int y) {
        switch (grid[x][y]) {
            case 1:
                detectL(x, y);
                detectR(x, y);
                break;
            case 2:
                detectU(x, y);
                detectD(x, y);
                break;
            case 3:
                detectL(x, y);
                detectD(x, y);
                break;
            case 4:
                detectR(x, y);
                detectD(x, y);
                break;
            case 5:
                detectL(x, y);
                detectU(x, y);
                break;
            case 6:
                detectR(x, y);
                detectU(x, y);
        }
    }
}