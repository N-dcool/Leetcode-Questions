class Solution {
    public int gcdOfOddEvenSums(int n) {
        int a = n*(n-1);
        int b = n*n;

        while(b != 0) {
            int rem = a%b;
            a = b;
            b = rem;
        }

        return a;
    }
}

/*
Sn = n/2 *[2a+(n−1)d]

even = n/2 [4+2n-2] = n(n-1)

odd = n/2 [2+2n-2] =n*n
*/