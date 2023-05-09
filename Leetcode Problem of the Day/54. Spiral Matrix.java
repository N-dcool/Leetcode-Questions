/*
Given an m x n matrix, return all elements of the matrix in spiral order. 

Example 1:
    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,2,3,6,9,8,7,4,5]

Example 2:
    Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i=0, j=0;
        List<Integer> ans = new ArrayList<>();
        int mid = n/2 +1;
        int mid2 = m/2 +1;
        
        while(i!=mid && j!=mid2){
            // horizontal Line ➡️ 1st row:
            for(int k=j; k<m; k++){
                if(matrix[i][k] != 101)
                    ans.add(matrix[i][k]);
                matrix[i][k] = 101;
            }

            // vertical Line ⬇️ last col:
            for(int k=i+1; k<n; k++){
                if(matrix[k][m-1] != 101)
                    ans.add(matrix[k][m-1]);
                matrix[k][m-1] = 101;
            }

            // horizontal Line ⬅️ last row:
            for(int k=m-2; k>=j; k--){
                if(matrix[n-1][k]!=101)
                    ans.add(matrix[n-1][k]);
                matrix[n-1][k] = 101;
                
            }

            // vertical Line ⬆️ 1st col:
            for(int k=n-2; k>i; k--){
                if(matrix[k][j]!=101)
                    ans.add(matrix[k][j]);
                matrix[k][j] = 101;  
            }
        
            i++;
            j++;
            n--;
            m--;
        }
    
        return ans;
    }
}
