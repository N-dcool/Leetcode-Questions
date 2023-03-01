/*
Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

 

Example 1:
    Input: nums = [5,2,3,1]
    Output: [1,2,3,5]
    Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), 
                  while the positions of other numbers are changed (for example, 1 and 5).

Example 2:
    Input: nums = [5,1,1,2,0,0]
    Output: [0,0,1,1,2,5]
    Explanation: Note that the values of nums are not necessairly unique.
 

Constraints:
    1 <= nums.length <= 5 * 104
    -5 * 104 <= nums[i] <= 5 * 104
*/

//Merge Sort ✔️
class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        
        divide(nums, 0, n-1);
        
        return nums;
    }
    
    public void divide(int[] nums, int start, int end){
        if(start >= end)
            return;
        int mid = start + (end - start)/2;
        divide(nums, start, mid);
        divide(nums, mid+1, end);
        
        conquer(nums, start, mid, end);
    }
    public void conquer(int[] nums, int start, int mid, int end){
        int[] merged = new int[end - start + 1];
        
        int idx1 = start;
        int idx2 = mid+1;
        int i = 0;
        while(idx1<=mid && idx2<=end){
            if(nums[idx1]<=nums[idx2]){
                merged[i++] = nums[idx1++];
            }else{
                merged[i++] = nums[idx2++];
            }
        }
        while(idx1<=mid)
            merged[i++] = nums[idx1++];
        while(idx2<=end)
            merged[i++] = nums[idx2++];
        
        for(int idx=0, j=start; idx<merged.length; idx++, j++){
            nums[j] = merged[idx];
        }
        
    }
}


/*
QUICK SORT : Time Limit Exceed :(

class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        
        quickSort(nums, 0, n-1);
        
        return nums;
    }
    public void quickSort(int[] nums, int low, int high){
        if(low < high){
            int pIdx = partition(nums, low, high);
            
            quickSort(nums, low, pIdx-1);
            quickSort(nums, pIdx+1, high);
        }
    }
    public int partition(int[] nums, int low, int high){
        int pivot = nums[high];
        int i = low-1;
        
        for(int j=low; j<high; j++){
            if(nums[j]<pivot){
                i++;
                //swap:
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        i++;
        int temp = nums[i];
        nums[i] = pivot;
        nums[high] = temp;
        
        return i; //next pivot index
    }
}
*/
