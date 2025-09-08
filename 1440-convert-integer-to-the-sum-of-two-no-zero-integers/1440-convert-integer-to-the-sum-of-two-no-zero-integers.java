class Solution {
    public int[] getNoZeroIntegers(int n) {
        int[] res = new int[2];
        int len = (n+1)/2;
        for(int i=1; i<=len; i++){
            if(hasZero(i) || hasZero(n-i)) continue;
            else {
                res[0] = i;
                res[1] = n-i;
                break;
            }
        }

        return res;
    }

    public boolean hasZero(int num){
        boolean res = false;

        while(num > 0){
            int digit = num%10;
            if(digit == 0) return true;
            num /= 10; 
        }

        return false;
    }
}