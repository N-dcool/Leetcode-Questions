/*
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only
if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

 

Example 1:
    Input: n = 3
    Output: 5
    Explanation: The five different ways are show above.

Example 2:
    Input: n = 1
    Output: 1
 

Constraints:
    1 <= n <= 1000
    
*/
[!img][https://assets.leetcode.com/uploads/2021/07/15/lc-domino1.jpg]
//memoization : 

class Solution {
    private int n;
    private int[][] memo;
    int MOD = (int) 1e9+7;

    public int numTilings(int n) {
        this.n = n;
        this.memo = new int[n][3];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        /*
             0 = BOTH CELLS EMPTY (STATE 1)
             1 = BOTTOM CELL EMPTY (STATE 2)
             2 = TOP CELL EMPTY (STATE 3)
        */
         
        
        
        return dp(0,0);
    }

    private int dp(int i, int end) {
        if (i == n) return end == 0 ? 1 : 0;
        if (i > n) return 0;
        if (memo[i][end] == -1) {
            int v = 0;
            if (end == 0) {
                int v1 = dp(i + 1, 0);
                int v2 = dp(i + 2, 0);
                int v3 = dp(i + 1, 1);
                int v4 = dp(i + 1, 2);
                v = ((v1 + v2) % MOD + v3) % MOD + v4;
            } else if (end == 1) {
                int v1 = dp(i + 2, 0);
                int v2 = dp(i + 1, 2);
                v = v1 + v2;
            } else if (end == 2) {
                int v1 = dp(i + 2, 0);
                int v2 = dp(i + 1, 1);
                v = v1 + v2;
            }
            memo[i][end] = v % MOD;
        }
        return memo[i][end];
    }
}


//using map as dp : 

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int mod = (int)(1e9 + 7);
    public int numTilings(int n) {
        if(n <= 2) {
            return n;
        }
        if(n == 3) {
            return 5;
        }
        if(map.containsKey(n)) return map.get(n);
        int res = (2 * numTilings(n-1) % mod + numTilings(n-3) % mod) % mod;
        map.put(n, res);
        return res;
    }
}
