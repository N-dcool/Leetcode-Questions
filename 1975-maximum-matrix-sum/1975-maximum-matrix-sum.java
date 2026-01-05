class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int negCount = 0;
        int min = Integer.MAX_VALUE;
        long totalSum = 0;

        for(int[] mat : matrix){
            for(int m : mat){
                if(m < 0){
                    negCount++;
                    m = m*(-1);
                }
                
                min = Math.min(min, m);
                totalSum += m;
            }
        }
        return negCount%2==0 ? totalSum : totalSum-(2*min);

    }
}