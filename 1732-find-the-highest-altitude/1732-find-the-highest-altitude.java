class Solution {
    public int largestAltitude(int[] gain) {
        int h = 0;
        int max = 0;

        for(int g : gain) {
            max = Math.max(max, h = h+g);
        }

        return max;

    }
}