/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
    Input: n = 3
    Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:
    Input: n = 1
    Output: [[1]]
 

Constraints:
  1 <= n <= 20
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int m = n;
        int[][] mat = new int[n][n];
        int i=0,j=0;
        int num = 1;
        
        while(i!=(m+1)/2){
            for(int k=i; k<n && num!=(m*m)+1; k++){
                mat[i][k] = num;
                num++;
            }

            for(int k=i+1; k<n && num!=(m*m)+1; k++){
                mat[k][n-1] = num;
                num++;
            }

            for(int k=n-2; k>=j && num!=(m*m)+1; k--){
                mat[n-1][k] = num;
                num++;
            }

            for(int k=n-2; k>i && num!=(m*m)+1; k--){
                mat[k][j] = num;
                num++;
            }
            i++;
            j++;
            n--;
        }
        
        
        return mat;
        
    }
}
