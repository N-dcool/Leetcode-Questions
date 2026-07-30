class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        if(n<=8) return n;

        int unique = 0;
        boolean[] letter = new boolean[26];
        for(char c : word.toCharArray()) {
            if(!letter[c-'a']){
                unique++;
                letter[c-'a'] = true;
            }
        }

        // System.out.println(unique);

        if(unique<=16) return 8 + (unique-8)*2;

        if(unique<=24) return 8 + 8*2 + (unique-16)*3;

        return 8 + 8*2 + 8*3 + (unique-24)*4;
    }
}