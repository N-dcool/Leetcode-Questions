class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] pref = new int[n];

        int max = 0;
        for(int i=0; i<n; i++) {
            max = Math.max(max, nums[i]);
            pref[i] = gcd(nums[i], max);
        }

        Arrays.sort(pref);
        int left = 0;
        int right = n-1;

        long sum = 0;
        while(left < right) {
            sum += gcd(pref[left], pref[right]);
            left++;
            right--;
        }

        return sum;
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