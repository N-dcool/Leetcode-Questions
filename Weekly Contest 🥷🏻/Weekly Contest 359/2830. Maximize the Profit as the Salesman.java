/*
You are given an integer n representing the number of houses on a number line, numbered from 0 to n - 1.

Additionally, you are given a 2D integer array offers where offers[i] = [starti, endi, goldi], indicating that ith buyer 
wants to buy all the houses from starti to endi for goldi amount of gold.

As a salesman, your goal is to maximize your earnings by strategically selecting and selling houses to buyers.

Return the maximum amount of gold you can earn.

Note that different buyers can't buy the same house, and some houses may remain unsold.

 

Example 1:
    Input: n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
    Output: 3
    Explanation: There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
    We sell houses in the range [0,0] to 1st buyer for 1 gold and houses in the range [1,3] to 3rd buyer for 2 golds.
    It can be proven that 3 is the maximum amount of gold we can achieve.

Example 2:
    Input: n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
    Output: 10
    Explanation: There are 5 houses numbered from 0 to 4 and there are 3 purchase offers.
    We sell houses in the range [0,2] to 2nd buyer for 10 golds.
    It can be proven that 10 is the maximum amount of gold we can achieve.
 

Constraints:
    1 <= n <= 105
    1 <= offers.length <= 105
    offers[i].length == 3
    0 <= starti <= endi <= n - 1
    1 <= goldi <= 103
*/

class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        int m = offers.size();
        
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        
        for(List<Integer> l : offers){
            List<Integer> newL = new ArrayList<>();
            newL.add(l.get(0)+1);
            newL.add(l.get(1)+1);
            newL.add(l.get(2));
            map.computeIfAbsent(l.get(1)+1, k-> new ArrayList<>()).add(newL);
        }
        
        // System.out.println(map);
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++){
            dp[i] = dp[i-1];
            if(map.containsKey(i)){
                for(List<Integer> offer : map.get(i)){
                    dp[i] = Math.max(dp[i], dp[offer.get(0)-1] + offer.get(2));
                }
            }
        }
        
        return dp[n];
    }
}



== > Memory Limit Exceed :( 
  
class Solution {
    Integer[][] dp;
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        dp = new Integer[offers.size()][100000];
        Collections.sort(offers, (a,b)->a.get(0)-b.get(0));
        
        return helper(0, offers , -1);
    }
    
    public int helper(int i, List<List<Integer>> offers, int prevBound){
        if(i == offers.size()) return 0;
        if(dp[i][prevBound+1]!=null) return dp[i][prevBound+1];
        
        int a = 0;
        int b = 0;
        if(prevBound < offers.get(i).get(0))
            a = offers.get(i).get(2) + helper(i+1, offers, offers.get(i).get(1));
        
        b = helper(i+1, offers, prevBound);
            
        return dp[i][prevBound+1] = Math.max(a,b);
    }
}
