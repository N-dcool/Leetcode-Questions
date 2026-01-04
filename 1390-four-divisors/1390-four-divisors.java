class Solution {
    public int sumFourDivisors(int[] nums) {
        int res = 0;

        for(int num : nums){
            int sum = isValid(num);
            if( sum != -1){
                res += sum;
            }
        }

        return res;
    }

    public int isValid(int num){
        int count = 0;
        int sum = 0;

        for(int i=1; i<=num; i++){
            if(num%i == 0) {
                count++;
                sum+=i;
            }

            if(count > 4) return -1;
        }

        return count == 4 ? sum : -1;
    }
}