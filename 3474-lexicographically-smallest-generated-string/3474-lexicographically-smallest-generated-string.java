class Solution {

    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] s = new char[n + m - 1];
        int[] fixed = new int[n + m - 1];

        for (int i = 0; i < s.length; i++) {
            s[i] = 'a';
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = i; j < i + m; j++) {
                    if (fixed[j] == 1 && s[j] != str2.charAt(j - i)) {
                        return "";
                    } else {
                        s[j] = str2.charAt(j - i);
                        fixed[j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean flag = false;
                int idx = -1;
                for (int j = i + m - 1; j >= i; j--) {
                    if (str2.charAt(j - i) != s[j]) {
                        flag = true;
                    }
                    if (idx == -1 && fixed[j] == 0) {
                        idx = j;
                    }
                }
                if (flag) {
                    continue;
                } else if (idx != -1) {
                    s[idx] = 'b';
                } else {
                    return "";
                }
            }
        }
        return new String(s);
    }
}