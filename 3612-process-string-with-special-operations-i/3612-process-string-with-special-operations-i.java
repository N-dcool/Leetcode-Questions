class Solution {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()) {
            switch(c) {
                case('*') -> {
                    int lastIdx = sb.length() - 1;
                    if(lastIdx >= 0) {
                        sb.deleteCharAt(lastIdx);
                    }
                }
                case('#') -> sb.append(sb.toString());
                case('%') -> sb.reverse();
                default -> sb.append(c);
            }
        }

        return sb.toString();
    }
}