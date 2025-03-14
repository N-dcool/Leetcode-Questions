class Solution {
    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;
        int ans = 0;
        int left = 1;
        int right = getMax(candies);

        while(left <= right){
            int mid = left + (right - left)/2;

            if(isPossible(candies, mid, k)){
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public int getMax(int[] arr){
        int max = 1;
        for(int a : arr) {
            max = Math.max(max, a);
        }
        return max;
    }

    public boolean isPossible(int[] arr, int pile, long k){

        for(int a : arr){
            k -= a/pile;
            if(k <= 0) return true;
        }

        return false;
    }
}