class Solution {
    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder("a");

        while(sb.length() < k){
            StringBuilder nextString = new StringBuilder();
            for(char c : sb.toString().toCharArray()){
                nextString.append(nextChar(c));
            }
            sb.append(nextString);
        }
        // System.out.println(sb.toString());
        return sb.charAt(k-1);
    }

    public char nextChar(char c){
        if(c == 'z') return 'a';

        return (char)(c+1);
    }
}

