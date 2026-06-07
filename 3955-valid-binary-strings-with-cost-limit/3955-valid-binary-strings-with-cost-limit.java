class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateValidStrings(int n, int k) {
        solve(1, new StringBuilder("0"), n, k);
        solve(1, new StringBuilder("1"), n, k);

        return res;
    }

    public void solve(int i, StringBuilder sb, int n, int k) {
        if(i==n) {
            if(k >= 0) {
                res.add(sb.toString());
            }
            return;
        }

        char prev = sb.charAt(i-1);

        if(prev == '1'){
            solve(i+1, sb.append("0"), n, k);
        } else{
            StringBuilder newSb = new StringBuilder(sb);
            solve(i+1, newSb.append("0"), n, k);
            solve(i+1, sb.append("1"), n, k-i);
        }
    }
}