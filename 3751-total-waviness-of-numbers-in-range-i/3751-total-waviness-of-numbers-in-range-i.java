// optimized
class Solution {

    private record Pair(int count, long sum) { }

    public int totalWaviness(int num1, int num2) {
        String s1 = Integer.toString(num1-1);
        String s2 = Integer.toString(num2);
        int n1 = s1.length();
        int n2 = s2.length();

        Pair res1 = solve(0, -1, -1, true, true, s1, n1);
        Pair res2 = solve(0, -1, -1, true, true, s2, n2);

        return (int)(res2.sum - res1.sum);
    }

    public Pair solve(int i, int prev, int cur, boolean tight, boolean isLeading, String num, int n) {

        if(i == n) return new Pair(1,0);

        int up = tight ? (num.charAt(i) - '0') : 9;
        int count = 0;
        int sum = 0;
        
        for(int d=0; d<=up; d++) {
            boolean newLeading = isLeading && d==0;
            int newPrev = cur;
            int newCur = newLeading ? -1 : d;
            boolean newTight = tight && (d==up);

            // System.out.println(newPrev +" "+ newCur +" "+ d);
            
            Pair res = solve(i+1, newPrev, newCur, newTight, newLeading, num, n);

            if(!newLeading && prev>=0 && cur>=0 && ((cur>prev && cur>d) || (cur<prev && cur<d))) {
                sum += res.count;
            }

            count += res.count;
            sum += res.sum;
            
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