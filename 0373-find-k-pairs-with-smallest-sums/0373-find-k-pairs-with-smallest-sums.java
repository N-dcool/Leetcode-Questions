class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;

        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->{
            return a[2]==b[2] ? Long.compare(a[0],b[0]) : Long.compare(a[2],b[2]);
        });

        for(int j=0; j<Math.min(k,m); j++) {
            pq.add(new long[]{0,j, nums1[0] + nums2[j]});
        }

        while(!pq.isEmpty() && k-->0) {
            long[] cur = pq.poll();
            int i = (int) cur[0];
            int j = (int) cur[1];

            res.add(List.of(nums1[i], nums2[j]));

            if(i+1<n) pq.add(new long[]{i+1, j, nums1[i+1] + nums2[j]});
        }

        return res;
    }
}