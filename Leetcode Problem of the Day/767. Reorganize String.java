/*
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

 

Example 1:
    Input: s = "aab"
    Output: "aba"

Example 2:
    Input: s = "aaab"
    Output: ""
 

Constraints:
    1 <= s.length <= 500
    s consists of lowercase English letters.
*/

class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        int[] freq = new int[26];
        
        int max = 0;
        for(char c : s.toCharArray()){
            freq[c-'a']++;
            max = Math.max(max, freq[c-'a']);
        }
        
        int k = (n+1)/2;
        if(k < max) return "";
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1] - a[1]);
        
        for(int i=0; i<26; i++){
            if(freq[i] > 0){
                pq.add(new int[]{i+'a',freq[i]});
            }
        }
        
        char[] ref = new char[n];
        
        int idx = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int size = cur[1];
            
            while(size-- > 0){
                ref[idx++] = (char)cur[0];
            }
            
        }
        
        char[] ans = new char[n];
        int j = 0;
        for(int i=0; i<n; i=i+2){
            ans[i] = ref[j++];
        }
        for(int i=1; i<n; i=i+2){
            ans[i] = ref[j++];
        }
        
        
        return new String(ans);
    }
}
