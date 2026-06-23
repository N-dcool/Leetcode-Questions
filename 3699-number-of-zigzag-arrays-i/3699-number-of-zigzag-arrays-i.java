public class Solution {

    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int[] dp0 = new int[r + 1];
        int[] dp1 = new int[r + 1];
        int[] sum0 = new int[r + 2];
        int[] sum1 = new int[r + 2];

        for (int i = l; i <= r; i++) {
            dp0[i] = 1;
            dp1[i] = 1;
            sum0[i] = i - l + 1;
            sum1[i] = i - l + 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = l; j <= r; j++) {
                dp0[j] = (sum1[r] - sum1[j] + MOD) % MOD;
                dp1[j] = sum0[j - 1];
            }

            sum0[l] = dp0[l];
            sum1[l] = dp1[l];
            for (int j = l + 1; j <= r; j++) {
                sum0[j] = (sum0[j - 1] + dp0[j]) % MOD;
                sum1[j] = (sum1[j - 1] + dp1[j]) % MOD;
            }
        }

        return (sum0[r] + sum1[r]) % MOD;
    }
}