class Solution {
    public long minTime(int[] skill, int[] mana) {
        long totalMana = 0;
        int n = skill.length;
        int m = mana.length;

        long[] finishTime = new long[n];

        for(int i=0; i<m; i++){

            long now = finishTime[0];
            int curMana = mana[i];

            for(int j=1; j<n; j++){
                now = Math.max(now + skill[j-1]*curMana, finishTime[j]);
            }

            finishTime[n-1] = now + skill[n-1]*curMana;

            for(int j=n-2; j>=0; j--){
                finishTime[j] = finishTime[j+1] - curMana * skill[j+1];
            }

        }

        return finishTime[n-1];
    }
}