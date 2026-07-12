class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if(n==0) return arr;
        int[] temp = arr.clone();

        Arrays.sort(temp);
        
        int rank = 1;
        HashMap<Integer, Integer> elToRank = new HashMap<>();
        elToRank.put(temp[0], rank);
        for(int i=1; i<n; i++) {
            if(temp[i-1]!=temp[i]) rank++;
            elToRank.put(temp[i], rank);
        }

        int[] res = new int[n];
        for(int i=0; i<n; i++) {
            res[i] = elToRank.get(arr[i]);
        }

        return res;
    }
}