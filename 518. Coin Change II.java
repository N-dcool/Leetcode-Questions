//TopDown Approach:

class Solution {
    Integer[][] dp;
    public int change(int amount, int[] coins) {
        int n = coins.length;
        dp = new Integer[n+1][amount+1];
        
        return helper(n-1, amount, coins);
    }
    
    public int helper(int i, int target, int[] coins){
        if(i==0){
            if(target==0 || target%coins[0]==0)
                return 1;
            return 0;
        }
        if(dp[i][target]!=null)
            return dp[i][target];
        
        int notTake = helper(i-1, target, coins);
        int take = 0;
        if(target>=coins[i])
            take = helper(i, target-coins[i], coins);
        
        return dp[i][target] = take + notTake;
    }
}

//BottomUp approach: 

class Solution {
   
    public int change(int amount, int[] coins) {
        int n = coins.length;
         int[][] dp = new int[n+1][amount+1];
        
        //baseConditions 
        for(int i=0; i<n; i++)
            dp[i][0] = 1;
        for(int target=1; target<=amount; target++)
            if(target%coins[0]==0)
                dp[0][target] = 1;
        
        for(int i=1; i<n; i++){
            for(int target=1; target<=amount; target++){
                
                int notTake = dp[i-1][target];
                int take = 0;
                if(target>=coins[i])
                    take = dp[i][target-coins[i]];

                dp[i][target] = take + notTake;
                
            }
        }
        
        
        return dp[n-1][amount];
    }
}


//SpaceOptimisation:

class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
         int[] prev = new int[amount+1];
        
        //baseConditions 
        prev[0] = 1;
        for(int target=1; target<=amount; target++)
            if(target%coins[0]==0)
                prev[target] = 1;
        
        for(int i=1; i<n; i++){
            int[] cur = new int[amount+1];
            cur[0] = 1;
            for(int target=1; target<=amount; target++){
                
                int notTake = prev[target];
                int take = 0;
                if(target>=coins[i])
                    take = cur[target-coins[i]];

                cur[target] = take + notTake;
                
            }
            prev = cur;
        }
        
        
        return prev[amount];
    }
}
