class Solution {
    int n,m;
    public boolean isValidSudoku(char[][] board) {
        n = board.length;
        m = board[0].length;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char curNum = board[i][j];
                if(curNum != '.' && !isValidCellNumber(board, i, j, curNum)) return false;
            }
        }

        return true;
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