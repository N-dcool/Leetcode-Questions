class Solution {

    public String smallestNumber(String num, long t) {
        long temp = t;
        for (int i = 2; i <= 9; i++) {
            while (temp % i == 0) {
                temp /= i;
            }
        }
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        long[] rem = new long[n + 1];
        rem[0] = t;
        int pos = n - 1;

        char[] numChars = num.toCharArray();
        for (int i = 0; i < n; i++) {
            if (numChars[i] == '0') {
                pos = i;
                break;
            }
            rem[i + 1] = rem[i] / gcd(rem[i], numChars[i] - '0');
        }

        if (rem[n] == 1) {
            return num;
        }

        for (int i = pos; i >= 0; i--) {
            while (++numChars[i] <= '9') {
                long tNow = rem[i] / gcd(rem[i], numChars[i] - '0');
                int k = 9;

                for (int j = n - 1; j > i; j--) {
                    while (tNow % k != 0) {
                        k--;
                    }
                    tNow /= k;
                    numChars[j] = (char) ('0' + k);
                }

                if (tNow == 1) {
                    return new String(numChars);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        long originalT = t;
        for (int i = 9; i > 1; i--) {
            while (originalT % i == 0) {
                ans.append((char) ('0' + i));
                originalT /= i;
            }
        }

        int padding = Math.max(n + 1 - ans.length(), 0);
        for (int i = 0; i < padding; i++) {
            ans.append('1');
        }

        return ans.reverse().toString();
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}