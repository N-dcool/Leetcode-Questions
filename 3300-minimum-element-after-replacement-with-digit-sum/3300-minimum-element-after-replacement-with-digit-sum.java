class Solution {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;

        for(int num : nums) {
            min = Math.min(min, digitSum(num));
        }

        return min;
    }

    public int digitSum(int num) {
        int sum = 0;

        while(num > 0) {
            int digit = num%10;

            sum += digit;

            num /= 10;
        }

        return sum;
    }
}