class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {

        int n = landStartTime.length;
        int m = waterStartTime.length;
        int minTime = Integer.MAX_VALUE;

        int finishLand = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            finishLand = Math.min(finishLand, landStartTime[i] + landDuration[i]);
        }

        int finishWater = Integer.MAX_VALUE;
        for(int j=0; j<m; j++) {
            // land -> water
            minTime = Math.min(minTime, Math.max(finishLand, waterStartTime[j]) + waterDuration[j]);
            
            finishWater = Math.min(finishWater, waterStartTime[j] + waterDuration[j]);
        }

        for(int i=0; i<n; i++) {
            // water -> land
            minTime = Math.min(minTime, Math.max(finishWater, landStartTime[i]) + landDuration[i]);
        }

        return minTime;
    }
}