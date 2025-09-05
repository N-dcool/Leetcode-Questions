class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        if(num1 == 0) return 0;

        for(int k=1; k<=40; k++){
            long num = num1 - (long)k*num2;
            if(k > num) return -1;
            if(k >= getBitCount(num)) return k;

        }

        return -1;
    }

    public int getBitCount(long num){
        int count = 0;

        while(num > 0){
            if((num & 1 ) == 1) count++;
            num = num >> 1;
        }

        return count;
    }
}