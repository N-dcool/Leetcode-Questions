class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        boolean[] broken = new boolean[26];
        int count = 0;

        for(char c : brokenLetters.toCharArray()){
            broken[c-'a'] = true;
        }

        for(String word : words){
            if(canType(word, broken)) count++;
        }

        return count;
    }

    public boolean canType(String word, boolean[] broken){
        for(char c : word.toCharArray()){
            if(broken[c-'a']) return false;
        }

        return true;
    }
}