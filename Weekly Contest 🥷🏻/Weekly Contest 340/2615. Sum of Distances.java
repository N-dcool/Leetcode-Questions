/*
You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the 
sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

Return the array arr.

 
Example 1:
    Input: nums = [1,3,1,1,2]
    Output: [5,0,3,4,0]
    Explanation: 
    When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
    When i = 1, arr[1] = 0 because there is no other index with value 3.
    When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
    When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
    When i = 4, arr[4] = 0 because there is no other index with value 2. 

Example 2:
    Input: nums = [0,5,3]
    Output: [0,0,0]
    Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
 

Constraints:
    1 <= nums.length <= 105
    0 <= nums[i] <= 109
*/

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        
        Map<Integer, long[]> map = new HashMap<>();
        /*
        [0] -> left Sum
        [1] -> right sum
        [2] -> left count
        [3] -> right count
        */
        int i=0;
        for(int a : nums){
            if(!map.containsKey(a))
                map.put(a, new long[4]);
            map.get(a)[1] += i++;  // total sum
            map.get(a)[3]++;       // frequency of occurence :)
        }
        
        i=0;
        for(int a : nums){
            long[] temp = map.get(a);
            
            temp[1] -= i;
            temp[3]--;
            ans[i] = Math.abs(temp[0] - i*temp[2]) + Math.abs(temp[1] - i*temp[3]);
            temp[0] += i++;
            temp[2]++;
        }
        
        return ans;
    }
}

/*
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        long[] ans = new long[n];
        
        for(int i=0; i<n; i++){
            map.computeIfAbsent(nums[i], k-> new ArrayList<>()).add(i);
        }
        
        //System.out.println(map);
        
        for(int i=0; i<n; i++){
            long sum = 0;
            //System.out.println(map.get(nums[i]));
            for(int num : map.get(nums[i]))
                sum += 1l*(Math.abs(i - num));
            ans[i] = sum;
        }
        
        return ans;
    }
}
*/
