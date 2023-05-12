/*
You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].

The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) 
and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of 
the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.

For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
Return the maximum points you can earn for the exam.

 

Example 1:
    Input: questions = [[3,2],[4,3],[4,4],[2,5]]
    Output: 5
    Explanation: The maximum points can be earned by solving questions 0 and 3.
    - Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
    - Unable to solve questions 1 and 2
    - Solve question 3: Earn 2 points
    Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.

Example 2:
    Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
    Output: 7
    Explanation: The maximum points can be earned by solving questions 1 and 4.
    - Skip question 0
    - Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
    - Unable to solve questions 2 and 3
    - Solve question 4: Earn 5 points
    Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
 

Constraints:
    1 <= questions.length <= 105
    questions[i].length == 2
    1 <= pointsi, brainpoweri <= 105
*/

class Solution {
    int n;
    Long[] dp;
    public long mostPoints(int[][] questions) {
        n = questions.length;
        dp = new Long[n+1];
        
        return helper(0, questions);
    }
    
    public long helper(int i, int[][] questions){
        if(i>=n)
            return 0;
        if(dp[i]!=null)
            return dp[i];
        
        long take = questions[i][0] + helper(i+questions[i][1]+1, questions);
        long notTake = helper(i+1, questions);
        
        return dp[i] = Math.max(take, notTake);
    }
}
