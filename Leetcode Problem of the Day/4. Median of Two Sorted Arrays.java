/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.

Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:
    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int mid = (n+m)/2;
        
        int[] sort = new int[mid+1];
        
        int i = 0;
        int j = 0;
        int idx = 0;
        for(; idx<sort.length; idx++){
            if(i<n && j<m){
                if(nums1[i] < nums2[j]){
                    sort[idx] = nums1[i++];
                }else{
                    sort[idx] = nums2[j++];
                }
            }else{
                if(i>=n)
                    sort[idx] = nums2[j++];
                else 
                    sort[idx] = nums1[i++];
            }
        }
        
        
        return (n+m)%2==0 ? ((sort[mid-1]+sort[mid])/2.0) : (sort[mid]);
    }
}
