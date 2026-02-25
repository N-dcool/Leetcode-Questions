class Solution {
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr)
        .boxed()
        .sorted((a,b)->{
            int bitA = numberOfBits(a);
            int bitB = numberOfBits(b);

            if(bitA != bitB) return Integer.compare(bitA, bitB);

            return Integer.compare(a,b);
        })
        .mapToInt(Integer::intValue)
        .toArray();
    }

    public int numberOfBits(int num){
        int count = 0;

        while(num > 0){
            int bit = num & 1;
            if(bit == 1) count++;

            num = num >> 1;
        }

        return count;
    }
}