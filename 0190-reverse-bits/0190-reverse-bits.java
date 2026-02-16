class Solution {
    public int reverseBits(int n) {
        StringBuilder sb = new StringBuilder();
        int count = 32;
        while(count-- > 0){
            int lastBit = n & 1;
            n = n >> 1;
            sb.append(lastBit);
        }

        // System.out.println(sb.toString());

        return Integer.parseInt(sb.toString(),2);
    }
}