/*
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444
 

Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104
*/

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        for(int i=1; i<n; i++){
            int idx = i-1;
            while(idx >=0 && arr[idx] > arr[i])
                idx = left[idx]-1;
            left[i] = idx + 1;
        }
        
        right[n-1] = n-1;
        for(int i=n-2; i>=0; i--){
            int idx = i+1;
            while(idx<n && arr[idx]>=arr[i])
                idx = right[idx]+1;
            right[i] = idx - 1;
        }
        
        long res = 0;
        
        for(int i=0; i<n; i++){
            res += 1l*arr[i]*(i-left[i]+1)*(right[i]-i+1);
        }
        
        // for(int i=0; i<left.length; i++){
        //     System.out.print(right[i]+ ",");
        // }
        
        return (int)(res%1_000_000_007);
    }
}
