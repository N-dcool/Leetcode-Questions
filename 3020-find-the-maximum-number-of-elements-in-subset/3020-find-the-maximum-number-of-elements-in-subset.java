class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> map = new HashMap<>();

        for(int num : nums) {
            map.put((long)num, map.getOrDefault((long)num, 0)+1);
        }
        int countOne = map.getOrDefault(1L, 0);
        int ans = (countOne & 1) == 1 ? countOne : countOne-1;
        map.remove(1L);

        for(long num : map.keySet()) {
            int res = 0;
            long x = num;

            while(map.containsKey(x) && map.get(x) > 1) {
                res += 2;
                x *= x;
            }

            ans = Math.max(ans, res + (map.containsKey(x) ? 1 : -1));
        }

        return ans;
    }
}