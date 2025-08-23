class Solution {
    public int numSubmat(int[][] mat) { 
        int n = mat.length;
        int m = mat[0].length;

        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j] > 0){
                    mat[i][j] += mat[i-1][j];
                }
            }
        }

        // for(int[] ma : mat){
        //     for(int a : ma){
        //         System.out.print(a + " ");
        //     }

        //     System.out.println();
        // }
        int ans = 0;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j] > 0){
                    int cur = 0;
                    int prev = mat[i][j];
                    for(int k=j; k>=0; k--){
                        if(mat[i][k] == 0) break;
                        // System.out.println("inside loop "+i +" "+ k +" prev: "+ prev + " i,k: "+ mat[i][k] + " cur " + cur);
                        if(prev >= mat[i][k]){
                            prev = mat[i][k];
                        } 
                        cur += prev;
                        
                    }
                    // System.out.println(i +" "+ j +" "+ cur);
                    ans += cur;
                } 
            }
        }

        return ans;
    }
} 