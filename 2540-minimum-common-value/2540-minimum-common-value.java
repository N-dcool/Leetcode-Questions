class Solution {
    public int getCommon(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        int n1 = 0;
        int n2 = 0;
        while(n1<n && n2<m){
            if(nums1[n1] == nums2[n2]) return nums1[n1];
            else if(nums1[n1] > nums2[n2]) n2++;
            else n1++;
        }

        // for(int num : nums1){
        //     if(binarySearch(nums2, num) != -1){
        //         return num;
        //     }
        // }
        
        return -1;
    }

    public int binarySearch(int[] nums, int target){
        int l = 0;
        int r = nums.length-1;

        while(l <= r) {
            int mid = ((r-l)/2) + l;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] > target){
                r = mid-1;
            } else{
                l = mid+1;
            }
        }

        return -1;
    }
}