class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] res = new int[n];

        List<Integer> greater = new ArrayList<>();
        int countPivot = 0;

        int idx=0;

        // all small elements
        for(int i=0; i<n; i++) {
            if(nums[i] < pivot) {
                res[idx++] = nums[i];
            } else if(nums[i] > pivot) {
                greater.add(nums[i]);
            } else{
                countPivot++;
            }
        }

        // equal elements
        while(countPivot-- > 0) {
            res[idx++] = pivot;
        }

        // all greater elements
        for(int num : greater) {
            res[idx++] = num;
        }

        return res;

    }
}

// [9,5,3,10,10,12,14]