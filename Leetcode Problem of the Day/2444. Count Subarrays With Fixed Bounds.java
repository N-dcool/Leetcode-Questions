/*
ou are given an integer array nums and two integers minK and maxK.

A fixed-bound subarray of nums is a subarray that satisfies the following conditions:

The minimum value in the subarray is equal to minK.
The maximum value in the subarray is equal to maxK.
Return the number of fixed-bound subarrays.

A subarray is a contiguous part of an array.

 

Example 1:
    Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
    Output: 2
    Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].

Example 2:
    Input: nums = [1,1,1,1], minK = 1, maxK = 1
    Output: 10
    Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 

Constraints:
    2 <= nums.length <= 105
    1 <= nums[i], minK, maxK <= 106
*/

--> N Solution ✅ 

class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long count = 0;
        
        int min = -1;
        int max = -1;
        int start = 0;
        
        for(int i=0; i<n; i++){
            if(nums[i]<minK || nums[i]>maxK){
                min=max=-1;
                start = i+1;
            }
            if(nums[i]==minK)
                min = i;
            if(nums[i]==maxK)
                max = i;
            count += Math.max(0, Math.min(min, max)-start+1);
        }
        
        return count;
    }
}

--> N^2 Solution: ❌ TLE

class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long count = 0;
        
        for(int i=0; i<n; i++){
            int min = -1 ,max = -1;
            for(int j=i; j<n; j++){
                if(nums[j]<minK || nums[j]>maxK) break;
                
                if(nums[j]==minK)
                    min = minK;
                if(nums[j]==maxK)
                    max = maxK;
                if(minK==min && maxK==max)
                    count++;
            }
        }
        
        return count;
    }
}

--> N^3 Solution: ❌ TLE

class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long count = 0;
        
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int k=i; k<j+1; k++){
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }
                if(min==minK && max==maxK)
                        count++;
            }
            
        }
        return count;
    }
}
