class Solution {

    private static final int[][] dirs = new int[][]{{0,1},{0,-1}, {1,0}, {-1,0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int n = grid.size();
        int m = grid.get(0).size();

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(b[2], a[2])
        );
        boolean[][] vis = new boolean[n][m];

        int iniHealth = grid.get(0).get(0) == 0 ? health : health-1;
        pq.add(new int[]{0,0, iniHealth});
        vis[0][0] = true;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int curHealth = cur[2];

            if(curHealth == 0) continue;

            if(a==n-1 && b==m-1) return true;

            for(int[] dir : dirs) {
                int x = a + dir[0];
                int y = b + dir[1];

                if(x<0 || y<0 || x>=n || y>=m || vis[x][y]) continue;

                int nextHealth = grid.get(x).get(y) == 0 ? curHealth : curHealth-1;

                pq.add(new int[]{x,y,nextHealth});
                vis[x][y] = true;
            }
        }

        return false;
    }
}