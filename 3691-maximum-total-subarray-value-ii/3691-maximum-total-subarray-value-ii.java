class Solution {
    private record Range(int i, int j, int val) { }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SegmentTree s = new SegmentTree(nums);

        PriorityQueue<Range> pq = new PriorityQueue<>((a,b) -> {
            int valA = a.val();
            int valB = b.val();

            return valA==valB ? a.i() - b.i() : valB-valA;
        });

        for(int i=0; i<n-1; i++) {
            pq.add(new Range(i, n-1, s.query(i,n-1)));
        }

        long res = 0;

        while(!pq.isEmpty() && k-->0) {
            Range cur = pq.poll();
            int i = cur.i();
            int j = cur.j();
            res += cur.val();

            if(i < j-1){
                pq.add(new Range(i, j-1, s.query(i, j-1)));
            }
            
        }

        return res;
    }
}

class SegmentTree {
    Pair[] tree;
    int[] arr;
    int n;
    private static final int INF = Integer.MAX_VALUE;
    private static final int NEG_INF = Integer.MIN_VALUE;

    private record Pair(int min, int max) { }
    
    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.tree = new Pair[4*n];
        build(0, 0, n-1);
    }

    public int query(int queryL, int queryR) {
        Pair res = query(0, 0, n-1, queryL, queryR);

        return res.max() - res.min();
    }

    private Pair query(int node, int segL, int segR, int queryL, int queryR) {
        
        if(queryR < segL || segR < queryL) return new Pair(INF, NEG_INF);

        if(queryL <= segL && segR <= queryR) {
            return tree[node];
        }


        int mid = segL + (segR-segL)/2;

        Pair left = query(2*node + 1, segL, mid, queryL, queryR);
        Pair right = query(2*node + 2, mid+1, segR, queryL, queryR);

        return new Pair(Math.min(left.min(), right.min()), Math.max(left.max(), right.max()));
        
    }

    private void build(int node, int start, int end) {
        if(start == end) {
            tree[node] = new Pair(arr[start], arr[start]);
            return;
        } 

        int mid = start + (end-start)/2;
        int leftNode = 2*node + 1;
        int rightNode = 2*node + 2;

        build(leftNode, start, mid);
        build(rightNode, mid+1, end);

        tree[node] = new Pair(Math.min(tree[leftNode].min, tree[rightNode].min), Math.max(tree[leftNode].max, tree[rightNode].max));
    }
}

// brute force:
/*
class Solution {
    public long maxTotalValue(int[] nums, int m) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        long ans = 0;

        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int k=i; k<=j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }
                pq.add(max-min);
            }
        }

        while(!pq.isEmpty() && m-->0) {
            ans += pq.poll();
        }

        return ans;
    }
}
*/