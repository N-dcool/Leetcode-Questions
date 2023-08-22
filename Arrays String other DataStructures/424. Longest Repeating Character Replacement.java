/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. 
You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

Example 1:
    Input: s = "ABAB", k = 2
    Output: 4
    Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
    Input: s = "AABABBA", k = 1
    Output: 4
    Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    The substring "BBBB" has the longest repeating letters, which is 4.
    There may exists other ways to achive this answer too.
 

Constraints:
    1 <= s.length <= 105
    s consists of only uppercase English letters.
    0 <= k <= s.length
*/

class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        
        int l = 0;
        int maxF = 0;
        int ans = 0;
        for(int r=0; r<n; r++){
            freq[s.charAt(r)-'A']++;
            maxF = Math.max(maxF, freq[s.charAt(r)-'A']);
            if(r-l+1-maxF > k){
                freq[s.charAt(l)-'A']--;
                l++;
            }
            ans = Math.max(ans, r-l+1);

        }
        
        return ans;
    }
}
