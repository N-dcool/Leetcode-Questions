/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 
Example 1:
    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

Example 2:
    Input: nums = [1], k = 1
    Output: [1]
 

Constraints:
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        
        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0)+1);
        
        int[] ans = new int[k];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> freq.get(b) - freq.get(a));
        
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            pq.add(entry.getKey());
        }
        
        for(int i=0; i<k; i++)
            ans[i] = pq.poll();
        
        return ans;
    }
}

optimal solution:

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        
        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0)+1);
        
        int[] ans = new int[k];
        
        List<Integer>[] bucket = new ArrayList[n+1];
        
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int key = entry.getKey();
            int val = entry.getValue();
            
            if(bucket[val] == null)
                bucket[val] = new ArrayList<>();
            
            bucket[val].add(key);
        }
        
        int j =0 ;
        for(int i=n; i>=0; i--){
            if(bucket[i] != null)
                for(int v : bucket[i])
                    if(j<k)
                        ans[j++] = v;
                    else
                        break;
        }
        
        
        return ans;
    }
}
