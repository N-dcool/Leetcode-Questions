/*
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

Example 1:
    Input: numRows = 5
    Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
    Input: numRows = 1
    Output: [[1]]
 

Constraints:
    1 <= numRows <= 30
*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i=1; i<=numRows; i++){
            res.add(buildRow(i));
        }
        
        return res;
    }
    
    public List<Integer> buildRow(int r){
        List<Integer> row = new ArrayList<>();
        /*
            1 = 1
            2 = 1 1
            3 = 1 2 1
            4 = 1 3 3 1
        */
        row.add(1);
        int val = 1;
        for(int j=1; j<r; j++){
            val = val*(r-j);
            val /= j;
            row.add(val);
        }
        
        return row;
    }
}

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i=0; i<numRows; i++){
            List<Integer> cur = new ArrayList<>();
            if(i<2){
                int size = i+1;
                while(size-->0)
                    cur.add(1);
                res.add(cur);
            }else{
                cur.add(1);
                int size = i-1;
                int idx = 1;
                while(size-->0){
                    cur.add(res.get(i-1).get(idx) + res.get(i-1).get(idx-1));
                    idx++;
                }
                cur.add(1);
                res.add(cur);
            }
        }
        
        return res;
    }
}

