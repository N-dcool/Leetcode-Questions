class Solution {
    public String reverseWords(String s) {
        String[] words = s.split(" ");

        int n = words.length;
        int count = countVowels(words[0]);

        for(int i=1; i<n; i++){
            if(count == countVowels(words[i])){
                words[i] = reverse(words[i]);
            }
        }

        return String.join(" ", words);
    }

    public int countVowels(String word){
        int count = 0;

        for(char c : word.toCharArray()){
            if(isVowel(c)) count++;
        }

        return count;
    }

    public String reverse(String s){
        StringBuilder sb = new StringBuilder(s);

        return sb.reverse().toString();
    }

    public boolean isVowel(char c){
        for(char v : "aeiou".toCharArray()){
            if(c == v) return true;
        }

        return false;
    }
}