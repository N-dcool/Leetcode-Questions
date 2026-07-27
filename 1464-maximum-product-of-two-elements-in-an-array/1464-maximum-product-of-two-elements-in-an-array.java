class Solution {
    public int maxProduct(int[] nums) {
        int a = 0;
        int b = 0;

        for(int i=0; i<nums.length; i++) {
            if(a < nums[i]) {
                b = a;
                a = nums[i];
            }else {
                b = Math.max(b, nums[i]);
            }
        }
        
        // System.out.println(a +" : "+ b);

        return (a-1)*(b-1);
    }
}