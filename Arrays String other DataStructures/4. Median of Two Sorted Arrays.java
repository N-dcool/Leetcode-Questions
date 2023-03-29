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
        int idx = 0;
        int i1 = 0;
        int i2 = 0;
        int n = nums1.length;
        int m = nums2.length;
        
        int mid = (n+m)/2;
        
        mid++;
        
        List<Integer> ans = new ArrayList<>();
        
        while(idx!=mid){
            if(i1==n){
                ans.add(nums2[i2]);
                i2++;
            }
            else if(i2==m){
                ans.add(nums1[i1]);
                i1++;
            }
            else if(nums1[i1] < nums2[i2]){
                ans.add(nums1[i1]);
                i1++;
            }else{
                ans.add(nums2[i2]);
                i2++;
            }
            
            idx++;
        }
        
        //System.out.println(ans);
        
        int s = ans.size();
        
        if((n+m)%2==1)
            return ans.get(s-1);
        
        return (ans.get(s-1)+ans.get(s-2))*1.0/2;
        
        
    }
}
