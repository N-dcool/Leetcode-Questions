class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals,(a,b)-> {
            int start = Integer.compare(a[0],b[0]);
            int end = Integer.compare(b[1],a[1]);
            return start == 0 ? end : start;
        });


        int count = 0;
        int prevEnd = 0;
        for(int[] interval : intervals) {
            int s = interval[0];
            int e = interval[1];

            if(prevEnd >= e) count++;
            prevEnd = Math.max(prevEnd, e);
        }

        return n-count;
    }
}

/*
s1----e1
 s2-------e2
 s3-e3
        s4---e4 

*/