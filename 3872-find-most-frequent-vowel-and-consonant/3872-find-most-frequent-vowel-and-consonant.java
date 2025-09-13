class Solution {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        int maxV = 0;
        int maxC = 0;

        for(char c : s.toCharArray()){
            freq[c-'a']++;

            if(isVowel(c)){
                maxV = Math.max(maxV, freq[c-'a']);
            } else {
                maxC = Math.max(maxC, freq[c-'a']);
            }
        }

        return maxV + maxC;
        
    }

    public boolean isVowel(char c){
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u' ;
    }
}