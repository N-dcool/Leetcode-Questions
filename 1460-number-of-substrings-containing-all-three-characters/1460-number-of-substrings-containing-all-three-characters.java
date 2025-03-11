class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] count = new int[3];
        int right = 0;
        int left = 0;
        int res = 0;

        while(right < n){
            char cr = s.charAt(right);
            count[cr - 'a']++;
            while(count[0] > 0 && count[1] > 0 && count[2] > 0){
                res += n-right;
                char cl = s.charAt(left++);
                count[cl - 'a']--;
            }
            right++;
        }
        return res;
    }
}