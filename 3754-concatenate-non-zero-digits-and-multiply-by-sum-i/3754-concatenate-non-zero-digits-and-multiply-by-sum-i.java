class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long place = 1;
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                x = digit * place + x;
                place *= 10;
                sum += digit;
            }

            n /= 10;
        }

        return x * sum;
    }
}

/*
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
*/