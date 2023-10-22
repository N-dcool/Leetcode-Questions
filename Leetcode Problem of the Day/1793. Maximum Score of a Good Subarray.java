/*
You are given an array of integers nums (0-indexed) and an integer k.

The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.

Return the maximum possible score of a good subarray.

 

Example 1:

Input: nums = [1,4,3,7,4,5], k = 3
Output: 15
Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15. 
Example 2:

Input: nums = [5,5,4,5,4,1,1,1], k = 0
Output: 20
Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 2 * 104
0 <= k < nums.length
*/

class Solution {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        Stack<Integer> s = new Stack<>();
        
        for(int i=0; i<n; i++){
            while(!s.isEmpty() &&  nums[s.peek()] >= nums[i]){
                s.pop();
            }
            
            if(s.isEmpty())
                left[i] = -1;
            else
                left[i] = s.peek();
            s.add(i);
        }
        
       
        while(!s.isEmpty()) s.pop();
        
        for(int i=n-1; i>=0; i--){
            while(!s.isEmpty() && nums[s.peek()] >= nums[i]){
                s.pop();
            }
            
            if(s.isEmpty())
                right[i] = n;
            else
                right[i] = s.peek();
            s.add(i);
        }
        
        int res = 0;
        for(int i=0; i<n; i++){
            int l = left[i];
            int r = right[i];
            if(l<k && r > k){
                int dis = (r-l-1);
                int prod = dis*nums[i];
                // System.out.println(i + " "+ l +" " + r + " expl:" + "dis: "+ dis + " nums[i]: "+ nums[i] + " prod: " + prod);
                res = Math.max(res, prod);
            }
        }
        
        return res;
    }
}
