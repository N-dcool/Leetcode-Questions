/*
You are given a 0-indexed 2D integer array items of length n and an integer k.

items[i] = [profiti, categoryi], where profiti and categoryi denote the profit and category of the ith item respectively.

Let's define the elegance of a subsequence of items as total_profit + distinct_categories2, where total_profit is the sum of all profits
in the subsequence, and distinct_categories is the number of distinct categories from all the categories in the selected subsequence.

Your task is to find the maximum elegance from all subsequences of size k in items.

Return an integer denoting the maximum elegance of a subsequence of items with size exactly k.

Note: A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without
changing the remaining elements' relative order.

 

Example 1:
    Input: items = [[3,2],[5,1],[10,1]], k = 2
    Output: 17
    Explanation: In this example, we have to select a subsequence of size 2.
    We can select items[0] = [3,2] and items[2] = [10,1].
    The total profit in this subsequence is 3 + 10 = 13, and the subsequence contains 2 distinct categories [2,1].
    Hence, the elegance is 13 + 22 = 17, and we can show that it is the maximum achievable elegance. 

Example 2:
    Input: items = [[3,1],[3,1],[2,2],[5,3]], k = 3
    Output: 19
    Explanation: In this example, we have to select a subsequence of size 3. 
    We can select items[0] = [3,1], items[2] = [2,2], and items[3] = [5,3]. 
    The total profit in this subsequence is 3 + 2 + 5 = 10, and the subsequence contains 3 distinct categories [1,2,3]. 
    Hence, the elegance is 10 + 32 = 19, and we can show that it is the maximum achievable elegance.

Example 3:
    Input: items = [[1,1],[2,1],[3,1]], k = 3
    Output: 7
    Explanation: In this example, we have to select a subsequence of size 3. 
    We should select all the items. 
    The total profit will be 1 + 2 + 3 = 6, and the subsequence contains 1 distinct category [1]. 
    Hence, the maximum elegance is 6 + 12 = 7.  
 

Constraints:
    1 <= items.length == n <= 105
    items[i].length == 2
    items[i][0] == profiti
    items[i][1] == categoryi
    1 <= profiti <= 109
    1 <= categoryi <= n
    1 <= k <= n
*/

class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        int n = items.length;
        Arrays.sort(items, (a,b)->b[0]-a[0]);
        
        Set<Integer> category = new HashSet<>();     
        Stack<int[]> s = new Stack<>();
        long totalSum = 0;
        
        
        for(int i=0; i<k; i++){
            int prof = items[i][0];
            int cat = items[i][1];
            
            if(category.contains(cat))
                s.add(items[i]);
            
            totalSum += prof;
            category.add(cat);
        }
        
        long totalProf = totalSum + (long)Math.pow(category.size(), 2);
        
        for(int i=k; i<n; i++){
            int prof = items[i][0];
            int cat = items[i][1];
            
            if(category.size() == k) break;
            
            if(!category.contains(cat) && !s.isEmpty()){
                category.add(cat);
                int[] item = s.pop();
                totalSum = totalSum - item[0] + prof;
                long curProf = totalSum + (long)Math.pow(category.size(), 2);
                totalProf = Math.max(totalProf, curProf);
            }
        }
        
        return totalProf;
    }
}
