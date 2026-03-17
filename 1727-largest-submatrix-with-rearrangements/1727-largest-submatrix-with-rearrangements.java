
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int res = minArea(matrix[0].clone(), m);

        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 1){ 
                    matrix[i][j] += matrix[i-1][j];
                }
            }
            res = Math.max(res, minArea(matrix[i].clone(), m));
        }

        return res;
    }

    public int minArea(int[] arr, int n){
        Arrays.sort(arr);
        int max = 0;
        for(int i=0; i<n; i++){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
        for(int i=0; i<n; i++){
            if(arr[i] == 0) continue;
            int len = n - i;
            max = Math.max(max, len*arr[i]);
        }

        return max;
    }
}
