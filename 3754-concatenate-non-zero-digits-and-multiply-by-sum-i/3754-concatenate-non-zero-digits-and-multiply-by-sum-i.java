class Solution {
    public long sumAndMultiply(int n) {
        StringBuilder num = new StringBuilder("");
        int sum = 0;

        while(n>0) {
            int digit = n%10;
            n /= 10;

            if(digit == 0) continue;
            num.append(digit);
            sum += digit;
        }

        long x = num.isEmpty() ? 0 : Long.parseLong(num.reverse().toString());

        return x * sum;
    }
}