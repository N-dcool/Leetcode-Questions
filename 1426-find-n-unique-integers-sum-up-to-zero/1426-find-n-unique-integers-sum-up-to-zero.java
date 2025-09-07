class Solution {
    public int[] sumZero(int n) {
        int sum = 0;
        int[] res = new int[n];

        for(int i=1; i<n; i++){
            res[i-1] = i;
            sum -= i;
        }

        res[n-1] = sum;

        return res;
    }
}