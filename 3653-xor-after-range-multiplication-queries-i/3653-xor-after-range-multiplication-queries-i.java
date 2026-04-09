class Solution {

    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            for (int i = l; i <= r; i += k) {
                nums[i] = (int) (((long) nums[i] * v) % MOD);
            }
        }
        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        return res;
    }
}