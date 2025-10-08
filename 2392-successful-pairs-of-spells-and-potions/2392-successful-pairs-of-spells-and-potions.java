class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];

        for(int i=0; i<n; i++){

            long target = (success + spells[i] -1)/spells[i];

            // System.out.println(target + " " + binarySearch(potions, target));

            res[i] = m - binarySearch(potions, target);
        }


        return res;

    }

    public int binarySearch(int[] arr, long val){
        int l = 0;
        int r = arr.length - 1;
        int res = r+1;

        while(l <= r){
            int mid = (r+l)/2;

            if(arr[mid] >= val){
                res = mid;
                r = mid-1;
            } else{
                l = mid+1;
            }
        }

        return res;
    }
}