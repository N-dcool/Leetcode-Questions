/*
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

 

Example 1:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    Output: true
    Explanation: One way to obtain s3 is:
    Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
    Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
    Since s3 can be obtained by interleaving s1 and s2, we return true.

Example 2:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    Output: false
    Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

Example 3:
    Input: s1 = "", s2 = "", s3 = ""
    Output: true
 

Constraints:
    0 <= s1.length, s2.length <= 100
    0 <= s3.length <= 200
    s1, s2, and s3 consist of lowercase English letters.
*/

class Solution {
    boolean see(String s1, String s2, String s3,int i,int j,int k,Boolean[][] dp){
        if(k==s3.length()){
            return true;
        }
        if(dp[i][j]!=null){
            return dp[i][j];
        }
        boolean one=false;
        boolean two=false;
     if(i<s1.length()){
        if(s1.charAt(i)==s3.charAt(k)){
            one =see(s1,s2,s3,i+1,j,k+1,dp);
        }
     }
     if(j<s2.length()){
       if(s2.charAt(j)==s3.charAt(k)){
           two= see(s1,s2,s3,i,j+1,k+1,dp);
        }
        // else{
        //     return false;
        // }
     }
        
        return  dp[i][j]=one||two;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        Boolean[][] dp = new Boolean[s1.length()+1][s2.length()+1];
        return see(s1,s2,s3,0,0,0,dp);
        
    }
}




