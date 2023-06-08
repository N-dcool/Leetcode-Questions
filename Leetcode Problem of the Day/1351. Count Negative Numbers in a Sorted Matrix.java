/*
Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

 
Example 1:
    Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
    Output: 8
    Explanation: There are 8 negatives number in the matrix.

Example 2:
    Input: grid = [[3,2],[1,0]]
    Output: 0
 

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    -100 <= grid[i][j] <= 100
*/

class Solution {
    public int countNegatives(int[][] grid) {
        int count = 0;
        int n = grid[0].length;
        int currRowNegativeIndex = n - 1;

        // Iterate on all rows of the matrix one by one.
        for (int[] row : grid) {
            // Decrease 'currRowNegativeIndex' so that it points to current row's last positive element.
            while (currRowNegativeIndex >= 0 && row[currRowNegativeIndex] < 0) {
                currRowNegativeIndex--;
            }
            // 'currRowNegativeIndex' points to the last positive element,
            // which means 'n - (currRowNegativeIndex + 1)' is the number of all negative elements.
            count += (n - (currRowNegativeIndex + 1));
        }
        return count;
    }
}
