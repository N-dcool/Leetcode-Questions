/*
You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent 
strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

Example 1:
    Input: s1 = "parker", s2 = "morris", baseStr = "parser"
    Output: "makkek"
    Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
    The characters in each group are equivalent and sorted in lexicographical order.
    So the answer is "makkek".

Example 2:
    Input: s1 = "hello", s2 = "world", baseStr = "hold"
    Output: "hdld"
    Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
    So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".

Example 3:
    Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
    Output: "aauaaaaada"
    Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in 
    baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 
 
Constraints:
    1 <= s1.length, s2.length, baseStr <= 1000
    s1.length == s2.length
    s1, s2, and baseStr consist of lowercase English letters.
*/

class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parentGraph = new int[26];
        
        for(int i=0; i<26; i++)
            parentGraph[i] = i;
        
        for(int i=0; i<s1.length(); i++){
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            
            int parentA = findParent(parentGraph, a);
            int parentB = findParent(parentGraph, b);
            
            if(parentA < parentB)
                parentGraph[parentB] = parentA;
            else if(parentA > parentB)
                parentGraph[parentA] = parentB;
        }
        
        /*
        for(int i=0; i<26; i++){
            char c1 = (char)(i+'a');
            char c2 = (char)(findParent(parentGraph, i) + 'a');
            System.out.println(c1 +"==>"+ c2);
 
        }
        */
        StringBuilder sb = new StringBuilder();
        for(char c : baseStr.toCharArray()){
            int x = c - 'a';
            char ansParent = (char)(findParent(parentGraph, x) + 'a'); 
            sb.append(ansParent);
        }
        
        return sb.toString();
        
    }
    
    public int findParent(int[] graph, int x){
        if(graph[x] == x)
            return x;
        return findParent(graph, graph[x]);
    }
}
