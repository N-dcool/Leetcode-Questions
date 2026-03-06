class Solution {
    public boolean checkOnesSegment(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();

        int idx = sb.indexOf("1");

        for(int i=idx+1; i<sb.length(); i++){
            if(sb.charAt(i) == '0') return false;
        }
        
        return true;
    }
}