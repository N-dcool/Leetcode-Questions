class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        int n = arr.length;
        for(int i=1; i<n; i++){
            min = Math.min(min, arr[i]-arr[i-1]);
        }

        List<List<Integer>> res = new ArrayList<>();

        for(int i=1; i<n; i++){
            if(min == arr[i] - arr[i-1]){
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i-1]);
                temp.add(arr[i]);
                res.add(temp);
                
            }
        }

        return res;
    }
}