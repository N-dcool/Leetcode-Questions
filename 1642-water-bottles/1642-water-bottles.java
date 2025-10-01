class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        if(numBottles < numExchange) return numBottles;

        return numBottles + solve(numBottles, numExchange);
    }

    private int solve(int e, int exc){
        if(e < exc) return 0;

        int waterBottles = e/exc;
        int unusedEmptyBottles = e%exc;

        return waterBottles + solve(waterBottles + unusedEmptyBottles, exc);
    }
}