/*
You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.
*/

class Solution {
    private int result;
    public int maxLength(List<String> arr) {
        result = 0;

        traverse(0, arr, 0, new boolean[26]);
        return result;
    }

    private void traverse(int start, List<String> arr, int length, boolean[] visited) {
        for (int i = start; i < arr.size(); i ++ ) {
            String cur = arr.get(i);
            if (!markVisited(visited, cur)) {
                continue;
            }

            length += cur.length();
            result = Math.max(result, length);

            traverse(i+1, arr, length, visited);
            length -= cur.length();
            unmarkVisited(visited, cur);

        }
    }

    private boolean markVisited(boolean[] visited, String cur) {
        for (int i = 0; i < cur.length(); i ++) {
            char c = cur.charAt(i);
            if (visited[c-'a']) {
                for (int j = 0; j < i; j ++) {
                    visited[cur.charAt(j)-'a'] = false;
                }

                return false;
            }

            visited[c-'a'] = true;
        } 

        return true;
    }


    private void unmarkVisited(boolean[] visited, String cur) {
        for (int i = 0; i < cur.length(); i ++) {
            visited[cur.charAt(i)-'a'] = false;
        } 
    }
}
