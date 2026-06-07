class Solution {
    public int sumOfGoodIntegers(int n, int k) {
        int count = 0;
        
        for(int i=Math.max(n-k,0); i<=n+k; i++) {
            if((n&i) == 0) count+=i;
        }

        return count;
    }
}