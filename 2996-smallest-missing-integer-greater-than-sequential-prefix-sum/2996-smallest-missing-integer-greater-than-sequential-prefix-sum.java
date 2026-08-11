class Solution {

    public int missingInteger(int[] nums) {
        int n = nums.length;
        Set<Integer> numSet = new HashSet<>(n);
        for (int num : nums) {
            numSet.add(num);
        }
        int prefixLen = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixLen += 1;
            } else {
                break;
            }
        }

        int total = ((nums[prefixLen - 1] + nums[0]) * prefixLen) / 2;
        while (numSet.contains(total)) {
            total += 1;
        }

        return total;
    }
}