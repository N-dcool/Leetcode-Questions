class Solution {
    public String triangleType(int[] nums) {
        int max = Math.max(nums[0], Math.max(nums[1], nums[2]));
        int sum = nums[0] + nums[1] + nums[2];

        if(sum <= 2 * max) return "none";

        if(nums[0] == nums[1] && nums[1] == nums[2]) return "equilateral";
        else if(nums[0] == nums[1] || nums[0] == nums[2] || nums[1] == nums[2]) return "isosceles";
        return "scalene";
    }
}