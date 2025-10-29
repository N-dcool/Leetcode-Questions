class Solution {
    public int smallestNumber(int n) {
        int x = 1;

        while(n>0){
            // System.out.println(n);
            n = n>>1;
            x *= 2;
        }

        return x-1;
    }
}