class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a,b)->{
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int n = points.length;
        int count = 0;
        for(int i=0; i<n; i++){
            int x0 = points[i][0];
            int y0 = points[i][1];
            int topBound = y0;
            int lowerBound = -1;

            for(int j=i+1; j<n; j++){
                int x1 = points[j][0];
                int y1 = points[j][1]; 

                if(topBound >= y1 && y1 > lowerBound){
                    count++;
                    lowerBound = y1;
                    if(y1 == topBound) topBound--;
                }
            }
        }

        return count;
    }
}