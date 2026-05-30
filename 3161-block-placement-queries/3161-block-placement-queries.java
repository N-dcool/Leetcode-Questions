class Solution {

    private int[] bt;

    private void update(int x, int v) {
        for (; x < bt.length; x += x & -x) {
            bt[x] = Math.max(bt[x], v);
        }
    }

    private int query(int x) {
        int res = 0;
        for (; x > 0; x -= x & -x) {
            res = Math.max(res, bt[x]);
        }
        return res;
    }

    public List<Boolean> getResults(int[][] queries) {
        int mx = 50000;

        TreeSet<Integer> st = new TreeSet<>();
        st.add(0);
        st.add(mx);

        for (int[] q : queries) {
            if (q[0] == 1) {
                st.add(q[1]);
            }
        }

        bt = new int[mx + 1];

        int pre = 0;
        for (int x : st) {
            if (x == 0) continue;
            update(x, x - pre);
            pre = x;
        }

        List<Boolean> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];
                int preVal = Optional.ofNullable(st.floor(x)).orElse(0);
                int maxSpace = query(preVal);
                maxSpace = Math.max(maxSpace, x - preVal);
                ans.add(maxSpace >= sz);
            } else {
                int x = q[1];
                int preVal = Optional.ofNullable(st.lower(x)).orElse(0);
                int nxt = Optional.ofNullable(st.higher(x)).orElse(mx);
                update(nxt, nxt - preVal);
                st.remove(x);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}