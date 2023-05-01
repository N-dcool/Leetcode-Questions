/*
You are given an array of unique integers salary where salary[i] is the salary of the ith employee.

Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.

 
Example 1:
    Input: salary = [4000,3000,1000,2000]
    Output: 2500.00000
    Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
    Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500

Example 2:
    Input: salary = [1000,2000,3000]
    Output: 2000.00000
    Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
    Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
 

Constraints:
    3 <= salary.length <= 100
    1000 <= salary[i] <= 106
    All the integers of salary are unique.
*/

class Solution {
    public double average(int[] salary) {
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        int n = salary.length;
        long sum = 0;
        
        for(int s : salary){
            min = Math.min(min, s);
            max = Math.max(max, s);
            sum += s;
        }
        
        //System.out.println(min + "  "+ max);
        
        sum -= min;
        sum -= max;
        
        return sum*1.0/(n-2);
    }
}
