class Solution {

    Map<Integer, List<Integer>> numToIdx;
    int n;
    boolean[] vis;

    public int minJumps(int[] arr) {
        this.n = arr.length;
        if(n==1) return 0;
        this.vis = new boolean[n];
        this.numToIdx = IntStream
            .range(0, n).boxed()
            .collect(Collectors.groupingBy(i -> arr[i], Collectors.toList()));
        // return dfs(0, arr);

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        int step = 0;

        while(!q.isEmpty()){
            // System.out.println(q);
            int size = q.size();

            while(size-- > 0){
                int curIdx = q.poll();

                int next = curIdx+1;
                int prev = curIdx-1;
                List<Integer> jumps = numToIdx.get(arr[curIdx]);

                if(!vis[next]) {
                    q.add(next);
                    vis[next] = true;
                    if(next == n-1) return 1+step;
                }
                if(prev>=0 && !vis[prev]) {
                    q.add(prev);
                    vis[prev] = true;
                    if(prev == n-1) return 1+step;
                }
                for(int jump : jumps){
                    if(!vis[jump]){
                        q.add(jump);
                        vis[jump] = true;
                        if(jump == n-1) return 1+step;
                    } 
                }
                jumps.clear();
            }            

            step++;
        }

        return n;
    }

    // public int dfs(int i, int[] arr){
    //     if(i < 0 || i>=n) return n;
    //     if(i == n-1) return 0;

    //     int forward = n;
    //     int backward = n;
    //     int jump = n;

    //     if(!vis[i]){
    //         vis[i] = true;

    //         forward = 1 + dfs(i+1, arr);
    //         backward = 1 + dfs(i-1, arr);
    //         for(int idx : numToIdx.get(arr[i])){
    //             if(i != idx){
    //                 jump = Math.min(jump , 1 + dfs(idx, arr));
    //             }
    //         }

    //         vis[i] = false;
    //     }

    //     return Math.min(jump, Math.min(forward, backward));
    // }
}