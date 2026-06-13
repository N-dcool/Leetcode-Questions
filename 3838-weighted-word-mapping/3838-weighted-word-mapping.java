class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        int n = words.length;
        char[] res = new char[n];

        int i = 0;
        for(String word : words) {
            long sum = 0;
            for(char c : word.toCharArray()) {
                sum += weights[c-'a'];
            }
            long mod = sum % 26;

            res[i++] = (char)('a' + (25-mod));
        }

        return new String(res);
    }
}