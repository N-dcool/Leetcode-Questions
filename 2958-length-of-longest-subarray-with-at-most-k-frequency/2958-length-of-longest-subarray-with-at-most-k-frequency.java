class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int maxFreq = 0;
        int n = nums.length;
        int ans = 0;
        
        HashMap<Integer,Integer> freq = new HashMap<>();
        
        for(int left=0, right=0; right<n; right++){
            int curFreq = freq.getOrDefault(nums[right],0);
            freq.put(nums[right],curFreq+1);
            
            maxFreq = Math.max(maxFreq, curFreq+1);
            
            while(left<n && freq.get(nums[right]) > k){
                freq.put(nums[left], freq.getOrDefault(nums[left],0)-1);
                left++;
            }
            
            ans = Math.max(ans, right-left+1);
        }
        
        return ans;
    }
}


/*

0 1 2 3 4 5 6 7

1 2 3 1 2 3 1 2

1 2 3 1 2 3 1

1 1 1 2 2 2 3
                        
          6  
          
            
    

*/