class Solution {
    public int minFlips(String s) {
        int n = s.length();
        // s = s + s;

        // s1 = 010101010...
        // s2 = 101010101...
        // String s1 = "" , s2 = "";

        // for(int i=0; i<2*n; i++){
        //     s1 += i%2==0 ? '0' : '1';
        //     s2 += i%2==0 ? '1' : '0';
        // }

        int i = 0;
        int j = 0;
        int min = n;
        int s1Flip = 0;
        int s2Flip = 0;

        while(j<2*n){
            if(s.charAt(j%n) != charAtIndexForS1(j)){
                s1Flip++;
            }
            if(s.charAt(j%n) != charAtIndexForS2(j)){
                s2Flip++;
            }

            if(j-i+1 > n){ // shrink size of sliding window
                if(s.charAt(i) != charAtIndexForS1(i)){
                    s1Flip--;
                }
                if(s.charAt(i) != charAtIndexForS2(i)){
                    s2Flip--;
                }

                i++;
            }

            if(j-i+1 == n){
                min = Math.min(min, Math.min(s1Flip, s2Flip));
            }

            j++;
        }

        return min;
    }

    private char charAtIndexForS1(int idx){
        return idx%2 == 0 ? '0' : '1';
    }

    private char charAtIndexForS2(int idx){
        return idx%2 == 0 ? '1' : '0';
    }
}