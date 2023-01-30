/*
The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

 

Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537
 

Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
*/


class Solution {
    public int tribonacci(int n) {
        int a = 0, b = 1, c = 1;
        
        if(n == 1)
            return 1;
        else if(n == 2)
            return 1;
        else if(n == 3)
            return b+c;
        
        int d=0;
        for(int i=3; i<=n; i++){
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return d;
    }
}
