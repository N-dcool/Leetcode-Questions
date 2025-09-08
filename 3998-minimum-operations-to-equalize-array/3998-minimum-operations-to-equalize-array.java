class Solution {
    public int minOperations(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        return set.size() == 1 ? 0 : 1;
    }
}