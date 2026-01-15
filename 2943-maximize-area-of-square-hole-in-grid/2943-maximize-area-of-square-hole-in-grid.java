class Solution {
    public int maximizeSquareHoleArea(int n1, int m1, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int n = hBars.length;
        int m = vBars.length;

        int hMax = 1;
        int count = 1;
        for(int i=1; i<n; i++){
            if(hBars[i-1]+1==hBars[i]){
                count++;
            } else{
                count = 1;
            }

            hMax = Math.max(hMax, count);
        }

        int vMax = 1;
        count = 1;
        for(int i=1; i<m; i++){
            if(vBars[i-1]+1==vBars[i]){
                count++;
            } else{
                count = 1;
            }

            vMax = Math.max(vMax, count);
        }


        int maxLen = Math.min(hMax+1, vMax+1);

        return maxLen * maxLen;
    }
}