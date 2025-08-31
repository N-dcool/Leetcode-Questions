class Solution {
    int n = 9;
    int m = 9;
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    public boolean dfs(char[][] board, int row, int col){
        if(row>=n) return true;
        if(col>=m) {
            return dfs(board, row+1, 0);
        }

        // System.out.println(row +" " + col);

        if(board[row][col] == '.'){
            for(char i='1'; i<='9'; i++){
                if(isValidCellNumber(board, row, col, i)){
                    board[row][col] = i;
                    boolean res = dfs(board, row, col+1);
                    if(res == true) return true;
                    else board[row][col] = '.';
                }
            }
            return false;
        } 

        return dfs(board, row, col+1);
    }

    public boolean isValidCellNumber(char[][] board, int row, int col, char num){
        // checking if num present in col
        for(int k=0; k<n; k++){
            if(k!=row && num == board[k][col]) return false;
        }

        // checking if num present in row
        for(int k=0; k<m; k++){
            if(k!=col && num == board[row][k]) return false;
        }

        // checking in matrix if num is present
        int matRow = (row/3)*3;
        int matCol = (col/3)*3;
        // System.out.println(matRow +" "+ matCol);
        for(int i=matRow; i<matRow+3; i++){
            for(int j=matCol; j<matCol+3; j++){
                if(i!=row && j!=col && num == board[i][j]) return false;
            }
        }

        return true;
    }
}