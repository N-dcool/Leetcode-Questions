/*
There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:


Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6
Example 2:


Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
 

Constraints:

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
*/

class Solution {
  int M = 1000000007;

  public int findPaths(int m, int n, int N, int i, int j) {
    int[][][] memo = new int[m][n][N + 1];
    for (int[][] l : memo) for (int[] sl : l) Arrays.fill(sl, -1);
    return findPaths(m, n, N, i, j, memo);
  }

  public int findPaths(int m, int n, int N, int i, int j, int[][][] memo) {
    if (i == m || j == n || i < 0 || j < 0) return 1;
    if (N == 0) return 0;
    if (memo[i][j][N] >= 0) return memo[i][j][N];
    memo[i][j][N] = (
        (findPaths(m, n, N - 1, i - 1, j, memo) + findPaths(m, n, N - 1, i + 1, j, memo)) % M +
        (findPaths(m, n, N - 1, i, j - 1, memo) + findPaths(m, n, N - 1, i, j + 1, memo)) % M
    ) % M;
    return memo[i][j][N];
  }
}
