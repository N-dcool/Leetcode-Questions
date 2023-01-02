/*
We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

 

Example 1:

Input: word = "USA"
Output: true
Example 2:

Input: word = "FlaG"
Output: false
 

Constraints:

1 <= word.length <= 100
word consists of lowercase and uppercase English letters.
*/

class Solution {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        if(n==1) return true;
        if(n==2){
            if(isSmallLetter(word.charAt(0)) && !isSmallLetter(word.charAt(1)))
                return false;
            return true;
        }
        
        if(isSmallLetter(word.charAt(0))){
            for(char c : word.toCharArray()){
                if(!isSmallLetter(c))
                    return false;
            }
        }
        else if(!isSmallLetter(word.charAt(0)) && isSmallLetter(word.charAt(1))){
            for(int i=2; i<n; i++){
                char c = word.charAt(i);
                 if(!isSmallLetter(c))
                     return false;
            }
        }
        else{
            for(char c : word.toCharArray()){
                 if(isSmallLetter(c))
                     return false;
            }
        }
        
        return true;
    }
    public boolean isSmallLetter(char c){
        int n = (int)c;
        
        return c>=97 && c<123;
    }
}
