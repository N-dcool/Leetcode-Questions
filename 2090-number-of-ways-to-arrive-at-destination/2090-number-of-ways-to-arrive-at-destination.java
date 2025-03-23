class Solution {
    public record Pair(int node, long time){}
    int MOD = 1000000007;

    public int countPaths(int n, int[][] roads) {
        if(n == 1) return 1;
        HashMap<Integer, List<Pair>> map = new HashMap<>();

        for(int[] r : roads){
            map.computeIfAbsent(r[0], k -> new ArrayList<>()).add(new Pair(r[1], r[2]));
            map.computeIfAbsent(r[1], k -> new ArrayList<>()).add(new Pair(r[0], r[2]));
        }

        int[] ways = new int[n];
        long[] minTime = new long[n];
        for(int i=0; i<n; i++){
            minTime[i] = Long.MAX_VALUE;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> (int)(a.time - b.time));
        pq.add(new Pair(0,0));
        ways[0] = 1;
        minTime[0] = 0;

        while(!pq.isEmpty()){
            // System.out.println(pq);
            Pair cur = pq.remove();
            int node = cur.node;
            long time = cur.time;

            for(Pair next : map.get(node)){
                int nextNode = next.node;
                long totTime = next.time + time;

                if(minTime[nextNode] == totTime){
                    ways[nextNode] = (ways[nextNode] + ways[node])%MOD;
                }
                else if(minTime[nextNode] > totTime){
                    minTime[nextNode] = totTime;
                    ways[nextNode] = ways[node];
                    pq.add(new Pair(nextNode, minTime[nextNode]));
                }
            }
        }

        return ways[n-1];

    }
}
