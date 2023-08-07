/*
You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

 

Example 1:
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    Output: true

Example 2:
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    Output: false
 

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -104 <= matrix[i][j], target <= 104
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int left = 0;
        int right = n*m;
                
        while(left < right){
            int mid = (left+right)/2;
            int row = mid/m;
            int col = mid%m;
            
            if(matrix[row][col] == target)
                return true;
            if(matrix[row][col] < target)
                left = mid + 1;
            else
                right = mid;
        }
        
        return false;
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        
        for(int i=n-1; i>=0; i--){
            if(matrix[i][0]<=target && matrix[i][m-1]>=target){
                return binarySearch(i, m, matrix, target);
            }
        }
        
        return false;
    }
    
    public boolean binarySearch(int i, int m, int[][] matrix, int target){
        int l = 0;
        int r = m;
        
        while(l < r){
            int mid = (l+r)/2;
            if(matrix[i][mid] == target)
                return true;
            if(matrix[i][mid] > target)
                r = mid;
            else
                l = mid+1;
        }
        
        return false;
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int r = 0;
        int c = m-1;
        
        while(r<n && c>=0){
            if(matrix[r][c] == target) return true;
            if(matrix[r][c] < target) r++;
            else c--;
        }
        
        return false;
    }
}
