class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateValidStrings(int n, int k) {

        solve(0, -1, new char[n], n, k);

        return res;
    }

    public void solve(int i, int prevBit, char[] binary, int n, int k) {
        if(k<0) return;
        if(i==n) {
            if(k >= 0) {
                res.add(new String(binary));
            }
            return;
        }

        binary[i] = '0';
        solve(i+1, 0 , binary, n, k);
        if(prevBit != 1){
            binary[i] = '1';
            solve(i+1, 1, binary, n, k-i);
        } 
    }
}