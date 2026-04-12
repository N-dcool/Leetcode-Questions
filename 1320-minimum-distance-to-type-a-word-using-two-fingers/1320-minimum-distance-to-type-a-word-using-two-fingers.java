class Solution {

    private int getDistance(int p, int q) {
        int x1 = p / 6;
        int y1 = p % 6;
        int x2 = q / 6;
        int y2 = q % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int minimumDistance(String word) {
        int n = word.length();
        int[][][] dp = new int[n][26][26];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                for (int k = 0; k < 26; ++k) {
                    dp[i][j][k] = Integer.MAX_VALUE / 2;
                }
            }
        }

        for (int i = 0; i < 26; ++i) {
            dp[0][i][word.charAt(0) - 'A'] = 0;
            dp[0][word.charAt(0) - 'A'][i] = 0;
        }

        for (int i = 1; i < n; ++i) {
            int cur = word.charAt(i) - 'A';
            int prev = word.charAt(i - 1) - 'A';
            int d = getDistance(prev, cur);

            for (int j = 0; j < 26; ++j) {
                dp[i][cur][j] = Math.min(dp[i][cur][j], dp[i - 1][prev][j] + d);
                dp[i][j][cur] = Math.min(dp[i][j][cur], dp[i - 1][j][prev] + d);

                if (prev == j) {
                    for (int k = 0; k < 26; ++k) {
                        int d0 = getDistance(k, cur);
                        dp[i][cur][j] = Math.min(
                            dp[i][cur][j],
                            dp[i - 1][k][j] + d0
                        );
                        dp[i][j][cur] = Math.min(
                            dp[i][j][cur],
                            dp[i - 1][j][k] + d0
                        );
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE / 2;
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                ans = Math.min(ans, dp[n - 1][i][j]);
            }
        }
        return ans;
    }
}