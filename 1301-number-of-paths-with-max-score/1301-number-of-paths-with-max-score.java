class Solution {
    private int n;
    private int m;
    private List<String> board;
    private long[][] score;
    private int[][] ways;
    private boolean[][] vis;

    private static final int MOD = 1000000007;
    private static final int[][] dirs = new int[][]{{1,0}, {0,1}, {1,1}};

    public int[] pathsWithMaxScore(List<String> board) {
        this.n = board.size();
        this.m = board.get(0).length();
        this.board = board;
        this.score = new long[n][m];
        this.ways = new int[n][m];
        this.vis = new boolean[n][m];

        long[] ans = solve(0,0);

        return new int[]{(int)ans[0], (int)ans[1]};
    }

    public long[] solve(int i, int j) {
        if(i==n-1 && j==m-1){
            return new long[]{0, 1};
        }
        if(i<0 || j<0 || i>=n || j>=m || board.get(i).charAt(j)=='X'){
            return new long[]{0, 0};
        }
        if(vis[i][j]){
            return new long[]{score[i][j], ways[i][j]};
        }

        int cur = getValue(i,j);
        vis[i][j] = true;
        long maxSum = 0;
        long count = 0;

        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            long[] res = solve(x, y);

            long resSum = res[0];
            long resWays = res[1];

            if(resWays == 0) continue;

            long totalSum = cur + resSum;

            if(totalSum > maxSum) {
                maxSum = totalSum;
                count = resWays;
            } else if(totalSum == maxSum) {
                count  = (count + resWays) % MOD;
            }
        }

        score[i][j] = maxSum;
        ways[i][j] = (int)count;

        return new long[]{maxSum, count};

    }

    private int getValue(int i, int j) {
        if(board.get(i).charAt(j)=='S' || board.get(i).charAt(j)=='E') return 0;

        return board.get(i).charAt(j) - '0';
    }
}