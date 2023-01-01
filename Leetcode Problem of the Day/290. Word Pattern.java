/*
Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

 

Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
 

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
*/


class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<String, Character> map = new HashMap<>();
        HashMap<Character, String> map2 = new HashMap<>();
        
        int start = 0;
        int pt = 0;
        for(int end=0; end<s.length(); end++){
            
            if(pt==pattern.length())
                return false;
            
            while(end<s.length() && s.charAt(end) != ' ')
                end++; 
            String word = s.substring(start, end);
            start = end+1; 
            //System.out.println(word + " : "+ pattern.charAt(pt));
            if(map.containsKey(word) && !map.get(word).equals(pattern.charAt(pt))){
                //System.out.print(map);
                return false;    
            }
            if(map2.containsKey(pattern.charAt(pt)) && !map2.get(pattern.charAt(pt)).equals(word))
                return false;
            
            map.put(word, pattern.charAt(pt));
            map2.put(pattern.charAt(pt), word);
            pt++;  
        }
        
        //System.out.print(map);
        //System.out.print(pt + " ->"+ pattern.length());
        
        if(pt!=pattern.length())
            return false;
           
        return true;
    }
}
