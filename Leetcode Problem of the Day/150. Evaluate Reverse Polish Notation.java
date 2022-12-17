/*
Example 1:
    Input: tokens = ["2","1","+","3","*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
   
Example 2:   
    Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    Output: 22
    Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
    = ((10 * (6 / (12 * -11))) + 17) + 5
    = ((10 * (6 / -132)) + 17) + 5
    = ((10 * 0) + 17) + 5
    = (0 + 17) + 5
    = 17 + 5
    = 22    
*/

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        
        for(String c : tokens){
            if(c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")){
                int a = s.pop();
                int b = s.pop();
                if(c.equals("+"))
                    s.add(a+b);
                else if(c.equals("-"))
                    s.add(b-a);
                else if(c.equals("*"))
                    s.add(a*b);
                else
                    s.add(b/a);
                
                //System.out.println(a+"->"+b);
            }
            else
                s.add(Integer.parseInt(c));
        }
        
        return s.peek();
    }
}
