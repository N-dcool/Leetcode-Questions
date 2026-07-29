class Solution {

    private long comb(long n, long m, long k) {
        long res = 1;
        m = Math.min(m, n - m);

        for (long i = 1; i <= m; i++) {
            res = (res * (n - i + 1)) / i;
            if (res > k) {
                return k + 1;
            }
        }
        return res;
    }

    private long permutations(int rem, int[] bucket, long k) {
        long ways = 1;
        for (int i = 0; i < 26; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            ways *= comb(rem, bucket[i], k);
            if (ways > k) {
                break;
            }
            rem -= bucket[i];
        }
        return ways;
    }

    public String smallestPalindrome(String s, long k) {
        int partition = s.length() / 2;
        int[] bucket = new int[26];

        for (int i = 0; i < partition; i++) {
            bucket[s.charAt(i) - 97] += 1;
        }

        StringBuilder left = new StringBuilder();
        long startIndex = 1;

        for (int pos = 0; pos < partition; pos++) {
            for (int i = 0; i < 26; i++) {
                if (bucket[i] == 0) {
                    continue;
                }

                bucket[i] -= 1;

                long ways = permutations(partition - pos - 1, bucket, k);
                if (startIndex + ways > k) {
                    left.append((char) (i + 97));
                    break;
                }

                bucket[i] += 1;
                startIndex += ways;
            }
        }

        if (left.length() < partition) {
            return "";
        }

        if (s.length() % 2 != 0) {
            left.append(s.charAt(partition));
        }

        for (int i = partition - 1; i >= 0; i--) {
            left.append(left.charAt(i));
        }

        return left.toString();
    }
}