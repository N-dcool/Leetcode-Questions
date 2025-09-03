class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int count = 0;
        Arrays.sort(points, (a,b) ->{
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        for(int i=0; i<n; i++){
            int[] alice = points[i];
            int top = alice[1];
            int bottom = Integer.MIN_VALUE;

            for(int j=i+1; j<n; j++){
                int[] bob = points[j];
                if(top >= bob[1] && bob[1] > bottom){
                    count++;
                    bottom = bob[1];
                    if(bob[1] == top) top--;
                }
                
            }
        }

        return count;
    }
}