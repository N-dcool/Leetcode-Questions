/*
You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, 
return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

 

Example 1:
    Input: grid = [[1,1],[3,4]]
    Output: 8
    Explanation: The strictly increasing paths are:
    - Paths with length 1: [1], [1], [3], [4].
    - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
    - Paths with length 3: [1 -> 3 -> 4].
    The total number of paths is 4 + 3 + 1 = 8.

Example 2:
    Input: grid = [[1],[2]]
    Output: 3
    Explanation: The strictly increasing paths are:
    - Paths with length 1: [1], [2].
    - Paths with length 2: [1 -> 2].
    The total number of paths is 2 + 1 = 3.
 

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 1000
    1 <= m * n <= 105
    1 <= grid[i][j] <= 105
*/

class Solution {
    int[][] dp;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int mod = 1_000_000_007;
    
    int dfs(int[][] grid, int i, int j) {
        // If dp[i][j] is non-zero, it means we have got the value of dfs(i, j),
        // so just return dp[i][j].
        if (dp[i][j] != 0)
            return dp[i][j];

        // Otherwise, set answer = 1, the path made of grid[i][j] itself.
        int answer = 1;

        // Check its four neighbor cells, if a neighbor cell grid[prevI][prevJ] has a
        // smaller value, we move to this cell and solve the subproblem: dfs(prevI, prevJ).
        for (int[] d : directions) {
            int prevI = i + d[0], prevJ = j + d[1];
            if (0 <= prevI && prevI < grid.length && 0 <= prevJ && 
                prevJ < grid[0].length && grid[prevI][prevJ] < grid[i][j]) {
                answer += dfs(grid, prevI, prevJ);
                answer %= mod;
            }
        }

        // Update dp[i][j], so that we don't recalculate its value later.
        dp[i][j] = answer;
        return answer;
    }
    
    public int countPaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        dp = new int[m][n];

        // Iterate over all cells grid[i][j] and sum over dfs(i, j).
        int answer = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                answer = (answer + dfs(grid, i, j)) % mod;
            }
        }

        return answer;
    }
}
