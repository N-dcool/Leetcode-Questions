class Solution {
    public long repairCars(int[] ranks, int cars) {
        int max = 1;

        for(int rank : ranks){
            max = Math.max(max, rank);
        }

        long left = 1l;
        long right = 1l * max * cars * cars;
        long res = left;

        while(left <= right){
            long mid = left + (right - left)/2;
            // System.out.println("left : " + left+ " right : " + right+ " mid : " + mid+ " res : "+ temp);
            if(isPossible(ranks, mid, cars)){
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public boolean isPossible(int[] ranks, long time, int cars){

        for(int rank : ranks){
            int canBeWashed = (int)Math.sqrt(time/rank);
            cars -= canBeWashed;

            if(cars <= 0) return true;
        }

        return false;
    }
}