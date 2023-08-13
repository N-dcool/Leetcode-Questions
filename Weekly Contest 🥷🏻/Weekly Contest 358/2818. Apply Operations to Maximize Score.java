/*
You are given an array nums of n positive integers and an integer k.

Initially, you start with a score of 1. You have to maximize your score by applying the following operation at most k times:

Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the one with the smallest index.
Multiply your score by x.
Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends being inclusive.

The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.

Return the maximum possible score after applying at most k operations.

Since the answer may be large, return it modulo 109 + 7.

 

Example 1:
    Input: nums = [8,3,9,3,8], k = 2
    Output: 81
    Explanation: To get a score of 81, we can apply the following operations:
    - Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
    - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the smaller index. Hence, we multiply the
    score by nums[2]. The score becomes 9 * 9 = 81.
    It can be proven that 81 is the highest score one can obtain.

Example 2:
    Input: nums = [19,12,14,6,10,18], k = 3
    Output: 4788
    Explanation: To get a score of 4788, we can apply the following operations: 
    - Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19 = 19.
    - Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18 = 342.
    - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
    It can be proven that 4788 is the highest score one can obtain.
 

Constraints:
    1 <= nums.length == n <= 105
    1 <= nums[i] <= 105
    1 <= k <= min(n * (n + 1) / 2, 109)
*/

class Solution {
    static final int MOD = 1000000007;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();

        int upper = Collections.max(nums) + 1;

        boolean[] prime = new boolean[upper];
        int[] primeScore = new int[upper];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i < upper; i++) {
            if (prime[i]) {
                for (int j = i; j < upper; j += i) {
                    primeScore[j]++;
                    prime[j] = false;
                }
            }
        }

        int[] nextGreaterElement = new int[n];
        Arrays.fill(nextGreaterElement, n);
        Stack<Integer> s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!s.empty() && primeScore[nums.get(i)] >= primeScore[nums.get(s.peek())]) {
                s.pop();
            }
            nextGreaterElement[i] = s.empty() ? n : s.peek();
            s.push(i);
        }

        int[] prevGreaterOrEqualElement = new int[n];
        Arrays.fill(prevGreaterOrEqualElement, -1);
        s = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s.empty() && primeScore[nums.get(i)] > primeScore[nums.get(s.peek())]) {
                s.pop();
            }
            prevGreaterOrEqualElement[i] = s.empty() ? -1 : s.peek();
            s.push(i);
        }

        int res = 1;
        int[][] tuples = new int[n][2];
        for (int i = 0; i < n; i++) {
            tuples[i][0] = nums.get(i);
            tuples[i][1] = i;
        }
        Arrays.sort(tuples, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        for (int i = 0; i < n; i++) {
            int num = tuples[i][0];
            int idx = tuples[i][1];
            int operations = Math.min((idx - prevGreaterOrEqualElement[idx]) * (nextGreaterElement[idx] - idx), k);
            res = (int)((1L * res * pow(num, operations)) % MOD);
            k -= operations;
            if (k == 0) {
                return res;
            }
        }

        return res;
    }

    public int pow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = (int)((1L * res * x) % MOD);
            }
            x = (int)((1L * x * x) % MOD);
            n /= 2;
        }
        return res;
    }
}


/*
class Solution {
    int[] nextGreatestElement;
    int[] prevGreatestOrEqual;
    PriorityQueue<Pair> pq;
    
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 
    53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 
    137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 
    227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313}; 
    int n;
    int MOD = 1000000007;
    
    public record Pair(int i, int num){}
    
    public int maximumScore(List<Integer> nums, int k) {
        this.n = nums.size();
        int maxElement = nums.get(0);
        for(int num : nums) maxElement = Math.max(num, maxElement);
        int[] primeScores = new int[maxElement+1];
        
        // generating primeScore for each element in nums: 
        for(int p : primes){
            int j = p;
            while(j<=maxElement){
                primeScores[j]++;
                j += p; 
            }
        }

        
         pq = new PriorityQueue<>((a,b)->b.num==a.num ? a.i-b.i : b.num - a.num);
        
        // making 2 arrays for index of next largest score and prev greater or equalscore :)
        make(nums, primeScores);
        
        long res = 1;
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int i = p.i;
            int num = p.num;
            
            int operations = Math.min(k, (i-prevGreatestOrEqual[i])*(nextGreatestElement[i]-i));
            k -= operations;
            
            res = (1l * res * pow(num, operations))%MOD;
            if(k==0)
                return (int)res;
        }
        
        
        return (int)res;
     }
    
    public void make(List<Integer> nums, int[] primeScore){
        nextGreatestElement = new int[n];
        prevGreatestOrEqual = new int[n];
        Stack<Integer> s = new Stack<>();
        
        for(int i=n-1; i>=0; i--){
            while(!s.isEmpty() && primeScore[nums.get(i)] >= primeScore[nums.get(s.peek())])
                s.pop();
            nextGreatestElement[i] = s.isEmpty() ? n : s.peek();
            s.add(i);
        }
        s = new Stack<>();
        
        for(int i=0; i<n; i++){
            while(!s.isEmpty() && primeScore[nums.get(i)] > primeScore[nums.get(s.peek())])
                s.pop();
            prevGreatestOrEqual[i] = s.isEmpty() ? -1 : s.peek();
            s.add(i);
            
            // making heap :)
            pq.add(new Pair(i, nums.get(i)));
        }
    }
    
    public int pow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = (int)((1L * res * x) % MOD);
            }
            x = (int)((1L * x * x) % MOD);
            n /= 2;
        }
        return res;
    }
}
*/
