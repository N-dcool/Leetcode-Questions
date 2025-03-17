class Solution {
    public boolean divideArray(int[] nums) {
        int oddCount = 0;
        int[] freq = new int[501];

        for(int num : nums){
            freq[num]++;
            if(freq[num]%2 == 0) oddCount--;
            else oddCount++;
        }

        return oddCount == 0;
    }
}