class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int i = 0;

        int res = 0;
        int[] freq = new int[26];

        for(int j=0; i<n-2; j++) {
            while(j<n && !presentAll(freq)) {
                freq[s.charAt(j++) - 'a']++;
            }
            if(presentAll(freq)) res += (n-j+1);
            j--;
            freq[s.charAt(i++) - 'a']--;
        }

        return res;
    }

    public boolean presentAll(int[] freq) {
        return freq[0]!=0 && freq[1]!=0 && freq[2]!=0;
    }
}