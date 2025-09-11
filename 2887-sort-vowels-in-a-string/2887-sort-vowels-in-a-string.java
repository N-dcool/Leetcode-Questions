class Solution {
    public String sortVowels(String s) {
        PriorityQueue<Character> pq = new PriorityQueue<>();

        int n = s.length();
        char[] sArr = new char[n];

        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(isVowel(c)){
                pq.add(c);
                sArr[i] = '-';
            } else{
                sArr[i] = c;
            }
        }

        if(pq.size()==0) return s;

        for(int i=0; i<n; i++){
            if(sArr[i] == '-'){
                sArr[i] = (char)pq.remove();
            }
        }

        return new String(sArr);
    }

    public boolean isVowel(char c){
        return c == 'A' || c == 'E' ||c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' ||c == 'i' || c == 'o' || c == 'u';
    }
}