/*
Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip.
To avoid boredom, you will create a playlist so that:

Every song is played at least once.
A song can only be played again only if k other songs have been played.
Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:
    Input: n = 3, goal = 3, k = 1
    Output: 6
    Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].

Example 2:
    Input: n = 2, goal = 3, k = 0
    Output: 6
    Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].

Example 3:
    Input: n = 2, goal = 3, k = 1
    Output: 2
    Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].
 

Constraints:
    0 <= k < n <= goal <= 100
*/

class Solution {
    int n,goal,k;
    int MOD = 1000000007;
    
    Long[][] dp;
    
    public int numMusicPlaylists(int n, int goal, int k) {
        this.goal = goal;
        this.n = n;
        this.k = k;
        
        dp = new Long[101][101];
        
        return (int)dfs(0,0);
    }
    
    public long dfs(int i, int usedSong){
        if(i == goal)
            return usedSong == n ? 1 : 0;
        
        if(dp[i][usedSong] != null)
            return dp[i][usedSong]%MOD;
        
        
        
        long newSong = (n-usedSong)*dfs(i+1, usedSong+1);
        long repeate = Math.max(0, usedSong-k)*dfs(i+1, usedSong);
        
        long total = (newSong%MOD + repeate%MOD)%MOD;
        
        
        return dp[i][usedSong] = total;
    }
}
