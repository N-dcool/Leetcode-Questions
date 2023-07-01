/*
You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given 
an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must
go to the same child and cannot be split up.

The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.

Return the minimum unfairness of all distributions.

 

Example 1:
    Input: cookies = [8,15,10,20,8], k = 2
    Output: 31
    Explanation: One optimal distribution is [8,15,8] and [10,20]
    - The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
    - The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
    The unfairness of the distribution is max(31,30) = 31.
    It can be shown that there is no distribution with an unfairness less than 31.

Example 2:
    Input: cookies = [6,1,3,2,2,4,1,2], k = 3
    Output: 7
    Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
    - The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
    - The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
    - The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
    The unfairness of the distribution is max(7,7,7) = 7.
    It can be shown that there is no distribution with an unfairness less than 7.
 

Constraints:
    2 <= cookies.length <= 8
    1 <= cookies[i] <= 105
    2 <= k <= cookies.length
*/

class Solution {
    private int dfs(int i, int[] distribute, int[] cookies, int k, int zeroCount) {
        // If there are not enough cookies remaining, return Integer.MAX_VALUE
        // as it leads to an invalid distribution.
        if (cookies.length - i < zeroCount) {
            return Integer.MAX_VALUE;   
        }

        // After distributing all cookies, return the unfairness of this
        // distribution.
        if (i == cookies.length) {
            int unfairness = Integer.MIN_VALUE;
            for (int value : distribute) {
                unfairness = Math.max(unfairness, value);
            }
            return unfairness;
        }
        
        // Try to distribute the i-th cookie to each child, and update answer
        // as the minimum unfairness in these distributions.
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < k; ++j) {
            zeroCount -= distribute[j] == 0 ? 1 : 0;
            distribute[j] += cookies[i];
            
            // Recursively distribute the next cookie.
            answer = Math.min(answer, dfs(i + 1, distribute, cookies, k, zeroCount));
            
            distribute[j] -= cookies[i];
            zeroCount += distribute[j] == 0 ? 1 : 0;
        }
        
        return answer;
    }
    
    public int distributeCookies(int[] cookies, int k) {
        int[] distribute = new int[k];
        
        return dfs(0, distribute, cookies, k, k);
    }
}
