/*
In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of 
skills that the person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the 
team who has that skill. We can represent these teams by the index of each person.

For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.

It is guaranteed an answer exists.

 

Example 1:
    Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
    Output: [0,2]

Example 2:
    Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], 
    people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]
    Output: [1,2]
 

Constraints:
    1 <= req_skills.length <= 16
    1 <= req_skills[i].length <= 16
    req_skills[i] consists of lowercase English letters.
    All the strings of req_skills are unique.
    1 <= people.length <= 60
    0 <= people[i].length <= 16
    1 <= people[i][j].length <= 16
    people[i][j] consists of lowercase English letters.
    All the strings of people[i] are unique.
    Every skill in people[i] is a skill in req_skills.
    It is guaranteed a sufficient team exists.
*/

class Solution {
    // List of Integer to store minimum length ans
    List<Integer> miniAns = new ArrayList<>();
    public void solve(int j, int n, int[] skill, List<Integer> currAns, int totalskill, int[][] dp){
        if(j==skill.length){
            if(totalskill==(1<<n)-1){
                // update minimum length ans
                if(miniAns.size()==0 || currAns.size()<miniAns.size()){
                    miniAns = new ArrayList<>(currAns);
                }
            }
            return;
        }
        // Memoization part
        if(dp[j][totalskill]!=-1 && dp[j][totalskill]<=currAns.size())
        return;

        // not pick case
        solve(j+1, n, skill, currAns, totalskill, dp);
        // pick case
        currAns.add(j);
        solve(j+1, n, skill, currAns, totalskill|skill[j], dp);
        currAns.remove(currAns.size()-1);

        if(currAns.size() > 0)
        dp[j][totalskill] = currAns.size();
    }
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n=req_skills.length;
        Map<String, Integer> map = new HashMap<>();
        // store index of each skill in a map
        for(int i=0; i<n; i++){
            map.put(req_skills[i], i);
        }
        
        int[] skill = new int[people.size()];
        // convert skill of people in bits ans store in skill array
        for(int i=0; i<people.size(); i++){
            for(int j=0; j<people.get(i).size(); j++){
                int skillbit = 1<<(map.get(people.get(i).get(j)));
                skill[i] = skill[i] | skillbit;
            }
        }

        // List of Integer to store curr ans
        List<Integer> currAns = new ArrayList<>();
        // dp array for memoization
        int[][] dp = new int[people.size()][1<<n];
        for(int[] row:dp)
        Arrays.fill(row, -1);
        solve(0, n, skill, currAns, 0, dp);
        
        // now convert minimum length answer into int[]
        int[] ans = new int[miniAns.size()];
        for(int i=0; i<miniAns.size(); i++){
            ans[i] = miniAns.get(i);
        }
        return ans;
    }
}
