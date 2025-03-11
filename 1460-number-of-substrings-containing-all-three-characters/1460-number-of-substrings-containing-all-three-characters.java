class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int right = 0;
        int left = 0;
        int res = 0;

        while(right < n){
            char cr = s.charAt(right);
            map.put(cr, map.getOrDefault(cr,0)+1);

            while(map.size() == 3){
                res += n-right;
                char cl = s.charAt(left++);
                map.put(cl,map.get(cl)-1);
                if(map.get(cl)==0) map.remove(cl);
            }
            right++;
        }

        return res;
    }
}