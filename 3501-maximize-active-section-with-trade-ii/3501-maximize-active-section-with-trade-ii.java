class SegmentTree {

    private int n;
    private int[] arr;
    private int[] seg;

    private void build(int p, int l, int r) {
        if (l == r) {
            seg[p] = arr[l];
            return;
        }

        int mid = (l + r) >> 1;
        build(p << 1, l, mid);
        build((p << 1) | 1, mid + 1, r);
        seg[p] = Math.max(seg[p << 1], seg[(p << 1) | 1]);
    }

    private int _query(int p, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            return seg[p];
        }

        int mid = (l + r) >> 1;
        int res = 0;
        if (L <= mid) {
            res = Math.max(res, _query(p << 1, l, mid, L, R));
        }
        if (R > mid) {
            res = Math.max(res, _query((p << 1) | 1, mid + 1, r, L, R));
        }

        return res;
    }

    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.seg = new int[n << 2];
        build(1, 0, n - 1);
    }

    public int query(int L, int R) {
        if (L > R) {
            return 0;
        }

        return _query(1, 0, n - 1, L, R);
    }
}

class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(
        String s,
        int[][] queries
    ) {
        int n = s.length();
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                cnt1++;
            }
        }

        List<Integer> zeroBlocks = new ArrayList<>();
        List<Integer> blockLeft = new ArrayList<>();
        List<Integer> blockRight = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int st = i;
            while (i < n && s.charAt(i) == s.charAt(st)) {
                i += 1;
            }
            if (s.charAt(st) == '0') {
                zeroBlocks.add(i - st);
                blockLeft.add(st);
                blockRight.add(i - 1);
            }
        }

        int m = zeroBlocks.size();
        if (m < 2) {
            // continuous 0 blocks less than 2 segments, return the answer directly
            List<Integer> result = new ArrayList<>();
            for (int q = 0; q < queries.length; q++) {
                result.add(cnt1);
            }
            return result;
        }

        int[] tmpSum = new int[m - 1];
        for (int k = 0; k < m - 1; k++) {
            tmpSum[k] = zeroBlocks.get(k) + zeroBlocks.get(k + 1);
        }
        SegmentTree seg = new SegmentTree(tmpSum);
        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0],
                r = q[1];
            int idx = lowerBound(blockRight, l);
            int jdx = upperBound(blockLeft, r) - 1;

            // at most 1 continuous block of 0s within the substring
            if (idx > m - 1 || jdx < 0 || idx >= jdx) {
                ans.add(cnt1);
                continue;
            }
            int firstLen =
                blockRight.get(idx) - Math.max(blockLeft.get(idx), l) + 1; // actual length of the first consecutive block of 0s in the substring
            int lastLen =
                Math.min(blockRight.get(jdx), r) - blockLeft.get(jdx) + 1; // actual length of the last consecutive block of 0s in the substring
            // exactly 2 consecutive 0 blocks within the substring
            if (idx + 1 == jdx) {
                int bestGain = firstLen + lastLen;
                ans.add(cnt1 + bestGain);
                continue;
            }

            int val1 = firstLen + zeroBlocks.get(idx + 1);
            int val2 = zeroBlocks.get(jdx - 1) + lastLen;
            int val3 = seg.query(idx + 1, jdx - 2);
            int bestGain = Math.max(Math.max(val1, val2), val3);
            ans.add(cnt1 + bestGain);
        }

        return ans;
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0,
            right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int upperBound(List<Integer> list, int target) {
        int left = 0,
            right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}