class LazyTag {

    int toAdd;

    LazyTag() {
        this.toAdd = 0;
    }

    LazyTag add(LazyTag other) {
        this.toAdd += other.toAdd;
        return this;
    }

    boolean hasTag() {
        return this.toAdd != 0;
    }

    void clear() {
        this.toAdd = 0;
    }
}

class SegmentTreeNode {

    int minValue;
    int maxValue;
    LazyTag lazyTag;

    SegmentTreeNode() {
        this.minValue = 0;
        this.maxValue = 0;
        this.lazyTag = new LazyTag();
    }
}

class SegmentTree {

    private int n;
    private SegmentTreeNode[] tree;

    SegmentTree(int[] data) {
        this.n = data.length;
        this.tree = new SegmentTreeNode[this.n * 4 + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new SegmentTreeNode();
        }
        build(data, 1, this.n, 1);
    }

    void add(int l, int r, int val) {
        LazyTag tag = new LazyTag();
        tag.toAdd = val;
        update(l, r, tag, 1, this.n, 1);
    }

    int findLast(int start, int val) {
        if (start > this.n) {
            return -1;
        }
        return find(start, this.n, val, 1, this.n, 1);
    }

    private void applyTag(int i, LazyTag tag) {
        tree[i].minValue += tag.toAdd;
        tree[i].maxValue += tag.toAdd;
        tree[i].lazyTag.add(tag);
    }

    private void pushdown(int i) {
        if (tree[i].lazyTag.hasTag()) {
            LazyTag tag = new LazyTag();
            tag.toAdd = tree[i].lazyTag.toAdd;
            applyTag(i << 1, tag);
            applyTag((i << 1) | 1, tag);
            tree[i].lazyTag.clear();
        }
    }

    private void pushup(int i) {
        tree[i].minValue = Math.min(
            tree[i << 1].minValue,
            tree[(i << 1) | 1].minValue
        );
        tree[i].maxValue = Math.max(
            tree[i << 1].maxValue,
            tree[(i << 1) | 1].maxValue
        );
    }

    private void build(int[] data, int l, int r, int i) {
        if (l == r) {
            tree[i].minValue = tree[i].maxValue = data[l - 1];
            return;
        }

        int mid = l + ((r - l) >> 1);
        build(data, l, mid, i << 1);
        build(data, mid + 1, r, (i << 1) | 1);
        pushup(i);
    }

    private void update(
        int targetL,
        int targetR,
        LazyTag tag,
        int l,
        int r,
        int i
    ) {
        if (targetL <= l && r <= targetR) {
            applyTag(i, tag);
            return;
        }

        pushdown(i);
        int mid = l + ((r - l) >> 1);
        if (targetL <= mid) update(targetL, targetR, tag, l, mid, i << 1);
        if (targetR > mid) update(
            targetL,
            targetR,
            tag,
            mid + 1,
            r,
            (i << 1) | 1
        );
        pushup(i);
    }

    private int find(int targetL, int targetR, int val, int l, int r, int i) {
        if (tree[i].minValue > val || tree[i].maxValue < val) {
            return -1;
        }

        if (l == r) {
            return l;
        }

        pushdown(i);
        int mid = l + ((r - l) >> 1);

        if (targetR >= mid + 1) {
            int res = find(targetL, targetR, val, mid + 1, r, (i << 1) | 1);
            if (res != -1) return res;
        }

        if (l <= targetR && mid >= targetL) {
            return find(targetL, targetR, val, l, mid, i << 1);
        }

        return -1;
    }
}

class Solution {

    public int longestBalanced(int[] nums) {
        Map<Integer, Queue<Integer>> occurrences = new HashMap<>();

        int len = 0;
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = sgn(nums[0]);
        occurrences.computeIfAbsent(nums[0], k -> new LinkedList<>()).add(1);

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1];
            Queue<Integer> occ = occurrences.computeIfAbsent(nums[i], k ->
                new LinkedList<>()
            );
            if (occ.isEmpty()) {
                prefixSum[i] += sgn(nums[i]);
            }
            occ.add(i + 1);
        }

        SegmentTree seg = new SegmentTree(prefixSum);

        for (int i = 0; i < nums.length; i++) {
            len = Math.max(len, seg.findLast(i + len, 0) - i);

            int nextPos = nums.length + 1;
            occurrences.get(nums[i]).poll();
            if (!occurrences.get(nums[i]).isEmpty()) {
                nextPos = occurrences.get(nums[i]).peek();
            }

            seg.add(i + 1, nextPos - 1, -sgn(nums[i]));
        }

        return len;
    }

    private int sgn(int x) {
        return (x % 2) == 0 ? 1 : -1;
    }
}