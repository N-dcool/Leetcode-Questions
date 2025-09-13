class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        HashSet<Integer> set = new HashSet<>();
        for(int f : friends) set.add(f);

        int n = friends.length;
        int[] res = new int[n];
        int i = 0;
        for(int o : order){
            if(set.contains(o)) res[i++] = o;
        }

        return res;
    }
}