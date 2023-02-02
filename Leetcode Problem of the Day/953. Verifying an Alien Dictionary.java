/*
In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. 
The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only
if the given words are sorted lexicographically in this alien language.

 

Example 1:
    Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
    Output: true
    Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
    Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
    Output: false
    Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:
    Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
    Output: false
    Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical
    rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).


Constraints:
    1 <= words.length <= 100
    1 <= words[i].length <= 20
    order.length == 26
    All characters in words[i] and order are English lowercase letters.
*/



class Solution {
    HashMap<Character, Character> map;
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1)
            return true;
        
        map = new HashMap<>();
        int k=0;
        for(char c : order.toCharArray()){
            map.put(c, (char)('a' + k));
            k++;
        }
        
        //System.out.println(map);
        
        for(int i=1; i<words.length; i++){
            String word1 = words[i-1];
            String word2 = words[i];
            int n = word1.length();
            int m = word2.length();
            if(!validate(word1, word2, n, m))
                return false;
        }
        
        return true;
    }
    
    public boolean validate(String a, String b, int n, int m){
        StringBuilder a1 = new StringBuilder(),b1 = new StringBuilder();
        
        for(int i=0; i<Math.max(n,m); i++){
            
            
            if(i<n){
                char c1 = a.charAt(i);
                a1.append(map.get(c1));
            }
            if(i<m){
                char c2 = b.charAt(i);
                b1.append(map.get(c2));
            }
        }
        
        return  a1.compareTo(b1) <= 0;
    }
}
