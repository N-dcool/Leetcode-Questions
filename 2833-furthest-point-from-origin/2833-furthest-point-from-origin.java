class Solution {

    public int furthestDistanceFromOrigin(String moves) {
        int L = 0;
        int R = 0;
        int B = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                L++;
            } else if (c == 'R') {
                R++;
            } else {
                B++;
            }
        }
        return Math.abs(L - R) + B;
    }
}