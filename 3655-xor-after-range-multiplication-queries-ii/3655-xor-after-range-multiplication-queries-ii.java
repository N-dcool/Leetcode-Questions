class Solution {

    private static final int MOD = 1_000_000_007;

    private int pow(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            y >>= 1;
        }
        return (int) res;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int T = (int) Math.sqrt(n);
        List<List<int[]>> groups = new ArrayList<>(T);
        for (int i = 0; i < T; i++) {
            groups.add(new ArrayList<>());
        }

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            if (k < T) {
                groups.get(k).add(new int[] { l, r, v });
            } else {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) (((long) nums[i] * v) % MOD);
                }
            }
        }

        long[] dif = new long[n + T];
        for (int k = 1; k < T; k++) {
            if (groups.get(k).isEmpty()) {
                continue;
            }
            Arrays.fill(dif, 1);
            for (int[] q : groups.get(k)) {
                int l = q[0];
                int r = q[1];
                int v = q[2];
                dif[l] = (dif[l] * v) % MOD;
                int R = ((r - l) / k + 1) * k + l;
                dif[R] = (dif[R] * pow(v, MOD - 2)) % MOD;
            }

            for (int i = k; i < n; i++) {
                dif[i] = (dif[i] * dif[i - k]) % MOD;
            }
            for (int i = 0; i < n; i++) {
                nums[i] = (int) (((long) nums[i] * dif[i]) % MOD);
            }
        }

        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        return res;
    }
}