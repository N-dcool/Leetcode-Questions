class Solution {
    public int minOperations(String s) {
        int maxDis = 0;
        for(char c : s.toCharArray()){
            if(c == 'a') continue;
            maxDis = Math.max(maxDis, 'z'-c+1);
        }

        return maxDis;
    }
}