class Answer {

    int[] ans;

    public Answer() {
        ans = new int[3];
    }

    void put(int x) {
        if (x > ans[0]) {
            ans[2] = ans[1];
            ans[1] = ans[0];
            ans[0] = x;
        } else if (x != ans[0] && x > ans[1]) {
            ans[2] = ans[1];
            ans[1] = x;
        } else if (x != ans[0] && x != ans[1] && x > ans[2]) {
            ans[2] = x;
        }
    }

    List<Integer> get() {
        List<Integer> ret = new ArrayList<>();
        for (int num : ans) {
            if (num != 0) {
                ret.add(num);
            }
        }
        return ret;
    }
}

class Solution {

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum1 = new int[m + 1][n + 2];
        int[][] sum2 = new int[m + 1][n + 2];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                sum1[i][j] = sum1[i - 1][j - 1] + grid[i - 1][j - 1];
                sum2[i][j] = sum2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        Answer ans = new Answer();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // a single cell is also a rhombus
                ans.put(grid[i][j]);
                for (int k = i + 2; k < m; k += 2) {
                    int ux = i;
                    int uy = j;
                    int dx = k;
                    int dy = j;
                    int lx = (i + k) / 2;
                    int ly = j - (k - i) / 2;
                    int rx = (i + k) / 2;
                    int ry = j + (k - i) / 2;
                    if (ly < 0 || ry >= n) {
                        break;
                    }
                    int sum =
                        (sum2[lx + 1][ly + 1] - sum2[ux][uy + 2]) +
                        (sum1[rx + 1][ry + 1] - sum1[ux][uy]) +
                        (sum1[dx + 1][dy + 1] - sum1[lx][ly]) +
                        (sum2[dx + 1][dy + 1] - sum2[rx][ry + 2]) -
                        (grid[ux][uy] +
                            grid[dx][dy] +
                            grid[lx][ly] +
                            grid[rx][ry]);
                    ans.put(sum);
                }
            }
        }

        List<Integer> resultList = ans.get();
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}