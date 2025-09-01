class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->{
            double ratioA = (classes[a][0]+1)/(classes[a][1]*1.0 + 1)-(classes[a][0])/(classes[a][1]*1.0);
            double ratioB = (classes[b][0]+1)/(classes[b][1]*1.0 + 1)-(classes[b][0])/(classes[b][1]*1.0);

            if(ratioA < ratioB){
                 return 1;
            }
            return -1;
        });

        for(int i=0; i<classes.length; i++){
            pq.add(i);
        }

        while(extraStudents > 0){
            extraStudents--;
            int topIdx = pq.remove();
            classes[topIdx][0]++;
            classes[topIdx][1]++;
            pq.add(topIdx);
        }

        double res = 0;
        for(int[] c : classes){
            res += (c[0]/(c[1]*1.0));
        }

        return res/classes.length;
    }
}
