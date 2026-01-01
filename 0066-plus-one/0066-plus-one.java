class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        if(digits[n-1] != 9){
            digits[n-1]++;
            return digits;
        }

        int idx = n-1;
        
        while( idx>=0 && digits[idx]==9){
            digits[idx] = 0;
            idx--;
        }

        if(idx==-1){
            int[] res = new int[n+1];
            res[0] = 1;
            for(int i=0; i<n; i++){
                res[i+1] = 0;
            }

            return res;
        }

        digits[idx]++;
        return digits;
    }
}