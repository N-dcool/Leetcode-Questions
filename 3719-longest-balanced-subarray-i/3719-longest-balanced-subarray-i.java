class Solution {

    public int longestBalanced(int[] nums) {
        int len = 0;

        for (int i = 0; i < nums.length; i++) {
            HashMap<Integer, Integer> odd = new HashMap<>();
            HashMap<Integer, Integer> even = new HashMap<>();

            for (int j = i; j < nums.length; j++) {
                HashMap<Integer, Integer> map = (nums[j] & 1) == 1 ? odd : even;
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

                if (odd.size() == even.size()) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }

        return len;
    }
}