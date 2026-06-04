// optimized
class Solution {

    private record Pair(long count, long sum) { }
    
    Long[][][][] dpCount;
    Long[][][][] dpSum;

    public int totalWaviness(int num1, int num2) {
        String s1 = Integer.toString(num1-1);
        String s2 = Integer.toString(num2);

        return (int)(solve(s2) - solve(s1));
    }

    public long solve(String num) {
        int n = num.length();
        dpCount = new Long[n+1][10][10][2];
        dpSum   = new Long[n+1][10][10][2];

        return dfs(0, -1, -1, true, true, num, n).sum;
    }

    public Pair dfs(int i, int prev, int cur, boolean tight, boolean isLeading, String num, int n) {

        if(i == n) return new Pair(1,0);
        if(!tight) {
            int leading = isLeading ? 1 : 0;
            if(prev >=0 && cur >=0 && dpCount[i][prev][cur][leading] != null){
                return new Pair(dpCount[i][prev][cur][leading],
                                dpSum[i][prev][cur][leading]);
            }
        }


        int up = tight ? (num.charAt(i) - '0') : 9;
        long count = 0;
        long sum = 0;
        
        for(int d=0; d<=up; d++) {
            boolean newLeading = isLeading && d==0;
            int newPrev = cur;
            int newCur = newLeading ? -1 : d;
            boolean newTight = tight && (d==up);

            // System.out.println(newPrev +" "+ newCur +" "+ d);
            
            Pair res = dfs(i+1, newPrev, newCur, newTight, newLeading, num, n);

            if(!newLeading && prev>=0 && cur>=0 && ((cur>prev && cur>d) || (cur<prev && cur<d))) {
                sum += res.count;
            }

            count += res.count;
            sum += res.sum;
        }

        if(!tight) {
            int leading = isLeading ? 1 : 0;
            if(prev >=0 && cur >=0){
                dpCount[i][prev][cur][leading] = count;
                dpSum[i][prev][cur][leading] = sum;
            }
        }

        return new Pair(count, sum);
    }
}

// Brute force :(
/*
class Solution {
    public int totalWaviness(int num1, int num2) {
        int res = 0;

        for(int i=Math.max(100, num1); i<=num2; i++) {
            String cur = Integer.toString(i);

            for(int j=1; j<cur.length()-1; j++) {
                int a = cur.charAt(j-1) - '0';
                int b = cur.charAt(j) - '0';
                int c = cur.charAt(j+1) - '0';
                if((b>a && b>c) || (b<a && b<c)) res++;
            }
        }

        return res;

    }
}
*/