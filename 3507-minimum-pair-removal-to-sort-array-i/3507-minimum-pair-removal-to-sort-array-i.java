class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }

        int count = 0;

        while(!isSorted(list)){
            int min = Integer.MAX_VALUE;
            int idx = -1;
            int n = list.size();
            for(int i=0; i<n-1; i++){
                int sum = list.get(i) + list.get(i+1);
                if(min > sum){
                    min = sum;
                    idx = i;
                }
            }

            List<Integer> temp = new ArrayList<>();
            for(int i=0; i<n; i++){
                if(i==idx){
                    temp.add(min);
                    i++;
                } else{
                    temp.add(list.get(i));
                }
            }
            list = temp;
            count++;
        }

        return count;
    }

    public boolean isSorted(List<Integer> nums){
        int size = nums.size();

        for(int i=1; i<size; i++){
            if(nums.get(i-1) > nums.get(i)) return false;
        }
        
        return true;
    }
}