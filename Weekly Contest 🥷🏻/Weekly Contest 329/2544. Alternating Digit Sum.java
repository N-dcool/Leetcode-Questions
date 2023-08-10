/*
You are given a positive integer n. Each digit of n has a sign according to the following rules:

The most significant digit is assigned a positive sign.
Each other digit has an opposite sign to its adjacent digits.
Return the sum of all digits with their corresponding sign.

 

Example 1:
    Input: n = 521
    Output: 4
    Explanation: (+5) + (-2) + (+1) = 4.

Example 2:
    Input: n = 111
    Output: 1
    Explanation: (+1) + (-1) + (+1) = 1.

Example 3:
    Input: n = 886996
    Output: 0
    Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.
 

Constraints:
    1 <= n <= 109
*/

class Solution {
    public int alternateDigitSum(int n) {
        int res1 = 0;
        int res2 = 0;
        
        int count = 1;
        
        while(n!=0){
            int d = n%10;
            n = n/10;
            if(count%2==0){
                res1 += d;
                res2 -= d;
            }else{
                res1 -= d;
                res2 += d;
            }
            
            count++;
        }
        
        // System.out.println(count);
        
        return count%2==0 ? res2 : res1;
    }
}

// 886996
