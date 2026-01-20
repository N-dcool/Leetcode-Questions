class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for(int i=0; i<n; i++){
            ans[i] = findMinBitwiseOr(nums.get(i));
        }

        return ans;
    }

    public int findMinBitwiseOr(int num){
        if(num == 2) return -1;

        if((num & (num+1)) == 0) return num >> 1;

        int ref = num;
        int pos = -1;

        while(ref > 0){
            int lastBit = ref & 1;
            ref = ref >> 1;

            if(lastBit == 0){
                // System.out.println(pos + " and operation : " + (1 << pos+1));
                num = num & ~( 1 << pos);
                break;
            }

            pos++;
        }

        return num;
    }
}