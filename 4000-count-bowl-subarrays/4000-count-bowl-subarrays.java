class Solution {
    public long bowlSubarrays(int[] nums) {
        long res = 0;
        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> s = new Stack<>();

        for(int i=0; i<n; i++){
            while(!s.isEmpty() && nums[s.peek()] < nums[i]){
                s.pop();
            }
            if(s.isEmpty()) left[i] = -1;
            else left[i] = s.peek();

            s.add(i);
        }

        while(!s.isEmpty()){
            s.pop();
        }

        for(int i=n-1; i>=0; i--){
            while(!s.isEmpty() && nums[s.peek()] < nums[i]){
                s.pop();
            }
            if(s.isEmpty()){
                right[i] = -1;
            }else{
                right[i] = s.peek();
            }
            s.add(i);
        }

        for(int i=0; i<n; i++){
            if(left[i] != -1 && right[i] != -1) res++;
        }

        return res;
    }
} 