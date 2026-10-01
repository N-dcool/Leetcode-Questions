class Solution {
    public boolean isValid(String str) {
        Stack<Character> s = new Stack<>();

        for(char c : str.toCharArray()) {
            if(s.isEmpty() && (c == ')' || c == '}' ||c == ']')) return false;
            if(c == ')') {
                char top = s.pop();
                if(top != '(') return false;

            } else if(c == '}') {
                char top = s.pop();
                if(top != '{') return false;

            } else if(c == ']') {
                char top = s.pop();
                if(top != '[') return false;

            } else {
                s.add(c);
            }
        }

        return s.isEmpty();
    }
}