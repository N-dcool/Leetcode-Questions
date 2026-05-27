class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lower = new int[26];
        int[] upper = new int[26];
        Arrays.fill(lower, -1);
        Arrays.fill(upper, -1);

        int special = 0;

        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);

            if(Character.isLowerCase(c)) {
                lower[c-'a'] = i; // last occurance
            } else if(upper[c-'A'] == -1) {
                upper[c-'A'] = i; // ensure we get first occurnce of upper case only
            }
        }

        for(int i=0; i<26; i++) {

            if(upper[i]!= -1 && lower[i]!= -1 && lower[i] < upper[i]) {
                // System.out.println(i);
                // System.out.println("lower: " + lower[i] + " upper: "+ upper[i]);
                special++;
            }
        }

        return special;
    }
}