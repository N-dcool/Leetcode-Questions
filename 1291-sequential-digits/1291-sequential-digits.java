class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String seqDigit = "123456789";
        List<Integer> res = new ArrayList<>();

        int min = getIntLength(low);
        int max = getIntLength(high);

        for(int i=min; i<=max; i++) {
            int start = 0;
            for(int j=i; j<=9; j++) {
                int num = Integer.parseInt(seqDigit.substring(start++,j));
                // System.out.println(num);
                if(num>=low && num<=high){
                    res.add(num);
                }
            }
        }

        return res;
    }

    private int getIntLength(int num) {
        return Integer.toString(num).length();
    }
}