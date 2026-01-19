class Solution {
    int n,m;
    int[][] pefixSum;
    int threshold;
    public int maxSideLength(int[][] mat, int threshold) {
        this.threshold = threshold;
        n = mat.length;
        m = mat[0].length;
        pefixSum = new int[n][m];

        for(int i=0; i<n; i++){
            pefixSum[i][0] = mat[i][0];
            for(int j=1; j<m; j++){
                pefixSum[i][j] = pefixSum[i][j-1] + mat[i][j];
            }
        }

        for(int j=0; j<m; j++){
            for(int i=1; i<n; i++){
                pefixSum[i][j] += pefixSum[i-1][j];
            }
        }

        // for(int[] pre : pefixSum){
        //     for(int p : pre){
        //         System.out.print(p +" ");
        //     }
        //     System.out.println();
        // }

        int left = 1;
        int right = Math.min(n,m);
        int res = 0;

        while(left <= right){
            int mid = (left+right)/2;

            if(isPossible(mid)){
                res = mid;
                left = mid+1;
            } else{
                right = mid-1;
            }
        }
        
        return res;
    }

    public boolean isPossible(int len){
        // System.out.println("------------------len : "+ len);

        for(int i=len-1; i<n; i++){
            for(int j=len-1; j<m; j++){
                int area = pefixSum[i][j];
                if(i-len >= 0 && j-len >= 0){
                    area = area - pefixSum[i-len][j] -pefixSum[i][j-len] + pefixSum[i-len][j-len];
                } else if(i-len >= 0){
                    area -= pefixSum[i-len][j];
                } else if(j-len >= 0){
                    area -= pefixSum[i][j-len];
                }
                
                // System.out.println("{ "+ i + ", "+ j + "} area = " + area + " theshold = " + threshold);
                if(threshold >= area){
                    // System.out.println("True : " + len);
                    return true;
                }
            }
        }

        

        return false;
        
    }
}