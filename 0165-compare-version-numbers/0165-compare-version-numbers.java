class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // for(String v : v1){
        //     System.out.print(v + " ");
        // }
        // System.out.println();

        // for(String v : v2){
        //     System.out.print(v + " ");
        // }

        int i = 0;
        int n = v1.length;
        int m = v2.length;

        while(i < Math.max(n,m)){
            int a = 0;
            int b = 0;

            if(i < n){
                a = Integer.parseInt(v1[i]);
            }
            if(i < m){
                b = Integer.parseInt(v2[i]);
            }

            if(a < b) return -1;
            else if(a > b) return 1;

            i++;
        }
        
        return 0;
    }
}