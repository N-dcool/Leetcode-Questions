class Solution {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] f = new int[n];
        int[] pre = new int[n];
        f[0] = 1;
        // since we start dynamic programming from i=minJump, we need to precompute the prefix sums for the part [0, minJump)
        for (int i = 0; i < minJump; i++) {
            pre[i] = 1;
        }
        for (int i = minJump; i < n; i++) {
            int left = i - maxJump;
            int right = i - minJump;
            if (s.charAt(i) == '0') {
                int total = pre[right] - (left <= 0 ? 0 : pre[left - 1]);
                f[i] = total != 0 ? 1 : 0;
            }
            pre[i] = pre[i - 1] + f[i];
        }
        return f[n - 1] == 1;
    }
}