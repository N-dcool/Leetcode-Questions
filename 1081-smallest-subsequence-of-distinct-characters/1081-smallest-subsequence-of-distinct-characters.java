class Solution {
    public String smallestSubsequence(String s) {
        int[] remaining = new int[26];

        for (char c : s.toCharArray()) {
            remaining[c - 'a']++;
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean[] used = new boolean[26];

        for (char c : s.toCharArray()) {
            int currentIndex = c - 'a';

            // Current occurrence has now been consumed.
            remaining[currentIndex]--;

            // Character is already part of the answer.
            // Do not modify the stack.
            if (used[currentIndex]) {
                continue;
            }

            /*
             * Remove larger characters when they are available again later.
             *
             * Example:
             * stack = [a, d], current = b
             *
             * If d appears later:
             * [a, d] -> [a] -> [a, b]
             *
             * We can add d later and get a smaller answer.
             */
            while (!stack.isEmpty()
                    && stack.peekLast() > c
                    && remaining[stack.peekLast() - 'a'] > 0) {

                char removed = stack.removeLast();
                used[removed - 'a'] = false;
            }

            stack.addLast(c);
            used[currentIndex] = true;
        }

        StringBuilder answer = new StringBuilder();

        while (!stack.isEmpty()) {
            answer.append(stack.removeFirst());
        }

        return answer.toString();
    }
}

/* Wrong submission solution:

-----------------
Input s = "cdadabcc"
Output = "dabc"
Expected = "adbc"
-----------------

class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];

        for(char c : s.toCharArray()) freq[c-'a']++;

        ArrayDeque<Character> dq = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            // System.out.println(dq);
            while(!dq.isEmpty() && dq.peek() >= c && freq[dq.peek()-'a'] > 1) {
                freq[dq.peek()-'a']--;
                dq.pop();
            }
            dq.add(c);
        }


        StringBuilder sb = new StringBuilder();
        boolean[] added = new boolean[26];
        while(!dq.isEmpty()) {
            char cur = dq.poll();
            if(!added[cur-'a']){
                sb.append(cur);
                added[cur-'a'] = true;
            }
        }

        return sb.toString();
        
    }
}

*/

/*
c-1
b-2
a-5

*/