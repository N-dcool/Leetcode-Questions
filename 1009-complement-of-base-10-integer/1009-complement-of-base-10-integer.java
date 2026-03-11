class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0) return 1;
        
        StringBuilder comp = new StringBuilder();

        while(n != 0){
            int bit = n & 1;

            if(bit == 1) comp.append(0);
            else comp.append(1);

            n = n >> 1;
        }

        return Integer.parseInt(comp.reverse().toString(), 2);
    }
}