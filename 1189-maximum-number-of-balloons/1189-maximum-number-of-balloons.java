class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];

        for(char c : text.toCharArray()){
            freq[c-'a']++;
        }
        freq['l'-'a'] /= 2;
        freq['o'-'a'] /= 2;

        int count = 10000;
        for(char c : "balon".toCharArray()) {
            // System.out.println(c +" -> "+freq[c-'a']);
            count = Math.min(count, freq[c-'a']);
        }

        return count;
    }
}