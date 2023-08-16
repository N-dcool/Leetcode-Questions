/*
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    Explanation: 
    Window position                Max
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

Example 2:
    Input: nums = [1], k = 1
    Output: [1]
 

Constraints:
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length
*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int m = n-k+1;
        int[] res = new int[m];
        
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i=0; i<k-1; i++){
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();
            dq.offer(i);
        }
        
        // System.out.println(dq);
        
        int l = 0;
        for(int r = k-1; r<n; r++){
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[r])
                dq.pollLast();
            dq.add(r);
            // System.out.println(dq);
            res[l] = nums[dq.peekFirst()];
            while(!dq.isEmpty() && dq.peekFirst() <= l) dq.removeFirst();
            l++;
        }
        
        return res;
    }
}
