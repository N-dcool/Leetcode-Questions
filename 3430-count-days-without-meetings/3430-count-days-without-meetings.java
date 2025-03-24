class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a,b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        // for(int[] mee : meetings){
        //     for(int m : mee){
        //         System.out.print(m + " ");
        //     }
        //     System.out.println();
        // }

        int count = 0;
        int curDay = 0;

        for(int[] meeting : meetings){
            int start = meeting[0];
            int end = meeting[1];
            if(curDay < start){
                // System.out.println("cur : " + curDay + " start : " + start);
                count += start - curDay - 1;
            }
            curDay = Math.max(curDay, end);
            if(curDay >= days) return count;
        } 

        return count + (days - curDay);
    }
}