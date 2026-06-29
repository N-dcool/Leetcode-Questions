class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;

        for(String w : patterns) {
            if(word.contains(w)) count++;
        }

        return count;
    }
}