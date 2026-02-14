class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] res = new double[101];
        res[0] = poured;
        
        for(int row=0; row<query_row; row++){
            for(int col=row; col>=0; col--){
                if(res[col] > 1){
                    double rem = (res[col]-1)/2;
                    res[col] = rem;
                    res[col+1] += rem;
                }else{
                    res[col] = 0;
                }
            }
        }
        
        return Math.min(1, res[query_glass]);
    }
}

/*

4 => 7 ?
4/6

*/