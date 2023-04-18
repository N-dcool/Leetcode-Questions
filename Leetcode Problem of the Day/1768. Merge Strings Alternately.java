/*
ou are given two strings word1 and word2. Merge the strings by adding letters in alternating order, 
starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

 
Example 1:
    Input: word1 = "abc", word2 = "pqr"
    Output: "apbqcr"
    Explanation: The merged string will be merged as so:
    word1:  a   b   c
    word2:    p   q   r
    merged: a p b q c r

Example 2:
    Input: word1 = "ab", word2 = "pqrs"
    Output: "apbqrs"
    Explanation: Notice that as word2 is longer, "rs" is appended to the end.
    word1:  a   b 
    word2:    p   q   r   s
    merged: a p b q   r   s

Example 3:
    Input: word1 = "abcd", word2 = "pq"
    Output: "apbqcd"
    Explanation: Notice that as word1 is longer, "cd" is appended to the end.
    word1:  a   b   c   d
    word2:    p   q 
    merged: a p b q c   d
 

Constraints:
    1 <= word1.length, word2.length <= 100
    word1 and word2 consist of lowercase English letters.
*/

class Solution {
    public String mergeAlternately(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        char[] ans = new char[n+m];
        
        int i = 0;
        int j = 1;
        int k;
        for(k=0; k<Math.min(n,m); k++){
            ans[i] = word1.charAt(k);
            ans[j] = word2.charAt(k);
            i+=2;
            j+=2;
        }
        
        if(n==m)
            return new String(ans);
        // System.out.println("i =" + i + " j  ="+ j);
        
        if(k==n){
            j-=1;
            while(k<m){
                ans[j] = word2.charAt(k);
                k++;
                j++;
            }
        }else{
            
            while(k<n){
                ans[i] = word1.charAt(k);
                k++;
                i++;
            }
        }
        
        return new String(ans);
    }
}
