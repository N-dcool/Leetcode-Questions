class Solution {

    public int uniqueXorTriplets(int[] nums) {
        int m = 0;
        for (int v : nums) {
            m = Math.max(m, v);
        }
        int u = 1;
        while (u <= m) {
            u <<= 1;
        }
        boolean[] one = new boolean[u];
        boolean[] two = new boolean[u];
        boolean[] three = new boolean[u];
        for (int v : nums) {
            one[v] = true;
            for (int x = 0; x < u; x++) {
                if (one[x]) {
                    two[x ^ v] = true;
                }
            }
        }
        for (int v : nums) {
            for (int x = 0; x < u; x++) {
                if (two[x]) {
                    three[x ^ v] = true;
                }
            }
        }
        int ans = 0;
        for (boolean b : three) {
            if (b) {
                ans++;
            }
        }
        return ans;
    }
}