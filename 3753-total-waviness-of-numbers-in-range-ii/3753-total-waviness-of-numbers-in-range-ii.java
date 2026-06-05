class Solution {
    int n;
    char[] digits;
    Pair[][][] dp;
    public long totalWaviness(long num1, long num2) {

        return solve(num2) - solve(num1-1);
        
    }

    private record Pair(long count, long waviness) { }

    private long solve(long num) {
        if(num <=100) return 0;
        
        digits = Long.toString(num).toCharArray();
        n = digits.length;
        dp = new Pair[3][11][n+1];

        return dfs(0, 10, 0, true, false).waviness();
    }

    private Pair dfs(int prevDiff, int prevDigit, int pos, boolean tight, boolean started) {
        if(pos == n) {
            return started ? new Pair(1,0) : new Pair(0,0);
        }

        if(!tight && started && dp[prevDiff+1][prevDigit][pos] != null) {
            return dp[prevDiff+1][prevDigit][pos];
        }

        int maxDigit = tight ? (digits[pos] - '0') : 9;
        long totalCount = 0;
        long totalWaviness = 0;


        for(int digit=0; digit<=maxDigit; digit++) {
            

            if(!started && digit==0) {
                Pair res = dfs(0, 0, pos+1, false, started);
                totalCount += res.count();
                totalWaviness += res.waviness();
            } else {
                int newDiff = 0;
                int addWave = 0;
                if(!started){
                    newDiff = 0;
                } else {
                    newDiff = Integer.compare(digit, prevDigit);
                    if((prevDiff==1 && newDiff==-1) || (prevDiff==-1 && newDiff==1)) {
                        addWave = 1;
                    }
                }
                boolean newTight = tight && (digits[pos] - '0' == digit);

                Pair res = dfs(newDiff, digit, pos+1, newTight, true);

                totalCount += res.count();
                totalWaviness += res.waviness() + 1l * addWave * res.count();
            }
        }
        
        Pair res = new Pair(totalCount, totalWaviness);

        if(!tight && started) {
            dp[prevDiff+1][prevDigit][pos] = res;
        }

        return res;
    }
    
}