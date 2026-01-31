class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int min = Integer.MAX_VALUE;

        for(char c : letters){
            if(c - target > 0){
                return c;
            }
        }

        return letters[0];
    }
}