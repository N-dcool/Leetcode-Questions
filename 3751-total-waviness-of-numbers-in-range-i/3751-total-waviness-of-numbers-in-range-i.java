class Solution {
    public int totalWaviness(int num1, int num2) {
        int res = 0;

        for(int i=Math.max(100, num1); i<=num2; i++) {
            String cur = Integer.toString(i);

            for(int j=1; j<cur.length()-1; j++) {
                int a = cur.charAt(j-1) - '0';
                int b = cur.charAt(j) - '0';
                int c = cur.charAt(j+1) - '0';
                if((b>a && b>c) || (b<a && b<c)) res++;
            }
        }

        return res;

    }
}