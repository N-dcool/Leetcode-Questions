class Solution {
    public int maxSum(int[] nums) {
        int max = nums[0];
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums){
            if(num > 0) set.add(num);
            max = Math.max(max, num);
        }

        if(set.isEmpty()) return max;

        int sum = 0;
        for(int s : set) sum += s;

        return sum;
    }
}