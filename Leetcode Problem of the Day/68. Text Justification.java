/*
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully 
(left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary 
so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words,
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:
    Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
    Output:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]

Example 2:
    Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
    Output:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
    Note that the second line is also left-justified because it contains only one word.

Example 3:
    Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], 
           maxWidth = 20
      
    Output:
    [
      "Science  is  what we",
      "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]
     

Constraints:
    1 <= words.length <= 300
    1 <= words[i].length <= 20
    words[i] consists of only English letters and symbols.
    1 <= maxWidth <= 100
    words[i].length <= maxWidth
*/

class Solution {
    String[] words;
    int maxWidth;
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        this.words = words;
        this.maxWidth = maxWidth;
        int n = words.length;
        // System.out.println("|"+left_justified(3,5,6)+"|");
        
        int i = 0;
        int j = 0;
        int curSize = 0;
        for(j=0; j<n; j++){
            curSize += words[j].length();
            int num = j-i+1;
            if(curSize+num-1 == maxWidth){
                // System.out.println(i +" "+ j +" "+ curSize);
                res.add(left_justified(i,j,curSize));
                curSize = 0;
                i = j+1;
            }else if(curSize+num-1 > maxWidth){
                curSize -= words[j].length();
                j--;
                // System.out.println(i +" "+ j +" "+ curSize);
                res.add(left_justified(i,j,curSize));
                curSize = 0;
                i = j+1;
            }
        }
        
        if(i<n){
            StringBuilder sb = new StringBuilder();
            int size = 0;
            for(; i<n; i++){
                size+=words[i].length();
                sb.append(words[i]);
                sb.append(" ");
                size++;
            }
            int rem = maxWidth - size;
            // System.out.println(rem);
            while(rem-->0) sb.append(" ");
            res.add(sb.toString());
        }
        
        return res;
        
    }
    
    public String left_justified(int i, int j, int totalSize){
        int space = maxWidth - totalSize; // totalSpace
        int noOfWords = j-i+1;
        int k = noOfWords-1;    // noOfSpacesRequired
        StringBuilder sb = new StringBuilder();
        
        if(k==0){
            sb.append(words[i]); 
            while(space-->0)
                sb.append(" ");
            return sb.toString();
        }
        
        int[] pattern = helper(space, k);
        
        int index = 0;
        for(int idx=i; idx<=j; idx++){
            sb.append(words[idx]);
            if(idx!=j){
                int size = pattern[index++];
                while(size-->0) sb.append(" ");
            }
        }
        
        
        return sb.toString();
    }
    
    public int[] helper(int space, int k){
        int[] ans = new int[k];
        int i=0;
        
        while(space > 0){
            ans[i%k]++;
            i++;
            space--;
        }
        
        return ans;
    }
}

