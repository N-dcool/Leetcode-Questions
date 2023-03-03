/*
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, 
or -1 if needle is not part of haystack.

 

Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.
 

Constraints:
    1 <= haystack.length, needle.length <= 104
    haystack and needle consist of only lowercase English characters.
*/

class Solution {
    public int strStr(String haystack, String needle) {
        
        int m = needle.length();
        int n = haystack.length();
        
        for(int i=0; i<n-m+1; i++){
            int k = 0;
            int count=0;
            while(needle.charAt(k)==haystack.charAt(k+i)){
                //System.out.println(needle.charAt(k)+ "==" + haystack.charAt(k+i));
                k++;
                count++;
                if(count==m)
                    return i;
            }
        }
        
        return -1;
    }
}

/*
Date: 12 Nov 2023 
class Solution {
    public int strStr(String haystack, String needle) {        
        int hs = haystack.length();
        int n = needle.length();
        
        if(n ==0)
            return 0;
        else if(hs == 0)
            return -1;
        
        
        for(int i=0; i<hs; i++){
            
            if(i > hs - n)
                break;
            
          for(int j=0; j<n; j++){
              if(needle.charAt(j) != haystack.charAt(j+i))
                  break;
              if(j == n-1)
                  return i;
          }
        }
        
        return -1;
        
        //one liner solution:
        //return haystack.indexOf(needle);
    }
}
*/
