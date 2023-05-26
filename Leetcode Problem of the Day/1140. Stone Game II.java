/*
Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive 
integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alice and Bob take turns, with Alice starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

Example 1:
    Input: piles = [2,7,9,4,4]
    Output: 10
    Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. 
    Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. 
    In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 

Example 2:
    Input: piles = [1,2,3,4,5,100]
    Output: 104
 

Constraints:
    1 <= piles.length <= 100
    1 <= piles[i] <= 104
*/

class Solution {
    private int f(int[] piles, int[][][] dp, int p, int i, int m) {
        if (i == piles.length) {
            return 0;
        }
        if (dp[p][i][m] != -1) {
            return dp[p][i][m];
        }
        int res = p == 1 ? 1000000 : -1, s = 0;
        for (int x = 1; x <= Math.min(2 * m, piles.length - i); x++) {
            s += piles[i + x - 1];
            if (p == 0) {
                res = Math.max(res, s + f(piles, dp, 1, i + x, Math.max(m, x)));
            }
            else {
                res = Math.min(res, f(piles, dp, 0, i + x, Math.max(m, x)));
            }
        }
        return dp[p][i][m] = res;
    }
    public int stoneGameII(int[] piles) {
        int[][][] dp = new int[2][piles.length + 1][piles.length + 1];
        for (int p = 0; p < 2; p++) {
            for (int i = 0; i <= piles.length; i++) {
                for (int m = 0; m <= piles.length; m++) {
                    dp[p][i][m] = -1;
                }
            }
        }
        return f(piles, dp, 0, 0, 1);
    }
}
