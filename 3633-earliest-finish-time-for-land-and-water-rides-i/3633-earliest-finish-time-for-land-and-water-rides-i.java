class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int minTime = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {

                // land -> water
                int finishLand = landStartTime[i] + landDuration[i];

                int startWater = Math.max(finishLand, waterStartTime[j]);
                int finishLandThenWater = startWater + waterDuration[j];

                // water -> land
                int finishWater = waterStartTime[j] + waterDuration[j];
                
                int startLand = Math.max(finishWater, landStartTime[i]);
                int finishWaterThenLand = startLand + landDuration[i];

                minTime = Math.min(minTime, Math.min(finishLandThenWater, finishWaterThenLand));
            }
        }

        return minTime;
        
    }
}