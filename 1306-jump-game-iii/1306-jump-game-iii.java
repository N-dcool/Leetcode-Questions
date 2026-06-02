class Solution {
    boolean[] vis;
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        vis = new boolean[n+1];

        return solve(arr, start); 
    }

    public boolean solve(int[] arr, int start) {
        if(start < 0 || start >= arr.length) {
            return false;
        }
        if(vis[start]) return false;
        if(arr[start] == 0) return true;

        int next = start + arr[start];
        int prev = start - arr[start];

        vis[start] = true;

        return solve(arr, next) || solve(arr, prev);
        
    }
}