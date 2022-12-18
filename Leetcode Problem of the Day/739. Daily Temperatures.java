/*
Given an array of integers temperatures represents the daily temperatures,
return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
If there is no future day for which this is possible, keep answer[i] == 0 instead.


Example 1:
  Input: temperatures = [73,74,75,71,69,72,76,73]
  Output: [1,1,4,2,1,1,0,0]
  
Example 2:
  Input: temperatures = [30,40,50,60]
  Output: [1,1,1,0]
  
Example 3:
  Input: temperatures = [30,60,90]
  Output: [1,1,0]
 

Constraints:
      1 <= temperatures.length <= 105
      30 <= temperatures[i] <= 100    
*/
  
https://assets.leetcode.com/users/images/d4874044-2a92-44c0-a060-7af87776857c_1597290931.7152643.png

//.         Using Stack (196 ms) : 

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        
        Stack<Integer> s = new Stack<>();
        
        for(int i=n-1; i>=0; i--){
            
            while(!s.isEmpty() && temperatures[s.peek()]<=temperatures[i])
                s.pop();
            
            if(s.isEmpty())
                ans[i] = 0;
            else
                ans[i] = s.peek()-i;
            
            s.add(i);
        }
        
        return ans;
    }
}

//.         using ForLoop (11ms):
//.                        https://assets.leetcode.com/users/images/d4874044-2a92-44c0-a060-7af87776857c_1597290931.7152643.png


class Solution {
    public int[] dailyTemperatures(int[] temps) {
        int n = temps.length;
        int[] ans = new int[n];
        
        for(int i=n-1; i>=0; i--){
            for(int j=i-1; j>=0 && temps[j]<temps[i] ; j--)
                ans[j] = i - j;
        }
        
        return ans;
    }
}
