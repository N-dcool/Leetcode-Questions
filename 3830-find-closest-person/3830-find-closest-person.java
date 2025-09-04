class Solution {
    public int findClosest(int x, int y, int z) {
        int disX = Math.abs(x-z);
        int disY = Math.abs(y-z);

        if(disX < disY) return 1;
        if(disX > disY) return 2;

        return 0;
    }
}