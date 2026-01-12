class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int total = 0;
        int n = points.length;

        for(int i=1; i<n; i++){
            int[] p1 = points[i-1];
            int[] p2 = points[i];

            int x = Math.abs(p1[0]-p2[0]);
            int y = Math.abs(p1[1]-p2[1]);

            total+=Math.max(x,y);
        }

        return total;
    }
}