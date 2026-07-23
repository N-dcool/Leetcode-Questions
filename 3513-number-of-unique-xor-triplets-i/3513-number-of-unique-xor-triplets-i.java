class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if(n<3) return n;

        int bit = 0;
        while(n>0) {
            n = n>>1;
            bit++;
        }

        // System.out.println(bit);

        return 1<<bit;
    }
}

