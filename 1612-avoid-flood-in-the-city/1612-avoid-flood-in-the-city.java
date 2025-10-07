class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        
        int[] res = new int[n];
        List<Integer> zeros = new ArrayList<>();

        for(int i=0; i<n; i++){
            if(rains[i] == 0){
                zeros.add(i);
                continue;
            }

            if(map.containsKey(rains[i])){
                if(zeros.size() == 0) return new int[0];

                int target = map.get(rains[i]);
                int idx = binarySearch(zeros, target);
                // System.out.println(zeros +" " + target +" " + idx);
                if(idx == zeros.size()){
                    return new int[0];
                } 
                res[zeros.get(idx)] = rains[i];
                map.put(rains[i], i);
                zeros.remove(idx);

            } else{
                map.put(rains[i], i);
            }

            res[i] = -1;

        }

        for(int idx : zeros){
            res[idx] = 1;
        }


        return res;
    }

    private int binarySearch(List<Integer> nums, int target){
        int left = 0;
        int right = nums.size()-1;
        int res = right+1;

        while(left <= right){
            int mid = (left + right)/2;

            if(nums.get(mid) >= target){
                res = mid;
                right = mid-1;
            } else{
                left = mid+1;
            }
        }

        return res;
    }
}