class Solution {
    public String findDifferentBinaryString(String[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums[0].length();

        for(String num : nums) set.add(Integer.parseInt(num, 2));

        int notPresent = -1;
        for(int i=0; i<100000; i++){
            if(!set.contains(i)){
                notPresent = i;
                break;
            }
        }

        String bin = Integer.toString(notPresent, 2);

        return "0".repeat(n-bin.length()) + bin;
    }
}