class Spreadsheet {

    int[][] spreedsheet;

    public Spreadsheet(int rows) {
        spreedsheet = new int[rows+1][26];
    }
    
    public void setCell(String cell, int value) {
        int[] cellIdx = getCoordinates(cell);

        int row = cellIdx[0];
        int col = cellIdx[1];

        spreedsheet[row][col] = value;
    }
    
    public void resetCell(String cell) {
        setCell(cell, 0);
    }
    
    public int getValue(String formula) {
        formula = formula.substring(1);
        int a = 0, b = 0;

        for(int i=0; i<formula.length(); i++){
            if(formula.charAt(i) == '+'){
                String left = formula.substring(0,i);
                String right = formula.substring(i+1);

                a = Character.isDigit(left.charAt(0)) ? Integer.parseInt(left) : getValueFromSheet(left);
                b = Character.isDigit(right.charAt(0)) ? Integer.parseInt(right) : getValueFromSheet(right);
                break;
            }
        }

        return a + b;
    }

    public int getValueFromSheet(String cell){
        int[] cellIdx = getCoordinates(cell);

        int row = cellIdx[0];
        int col = cellIdx[1];

        return spreedsheet[row][col];
    }

    public int[] getCoordinates(String cell){
        int y = cell.charAt(0) - 'A';
        int x = Integer.parseInt(cell.substring(1));

        return new int[]{x,y};
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */