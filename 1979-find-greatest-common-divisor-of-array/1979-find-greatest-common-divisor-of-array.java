class Solution {
    public int findGCD(int[] nums) {
        int min = 1000;
        int max = 1;

        for(int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        while(b!=0) {
            int rem = a%b;
            a = b;
            b = rem;
        }

        return a;
    }
}