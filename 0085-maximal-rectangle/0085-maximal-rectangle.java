class Solution {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] his = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                his[i][j] = matrix[i][j]=='0' ? 0 : 1;
            }
        }

        int res = maxRecArea(his[0]);

        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                if(his[i][j] != 0){
                    his[i][j] += his[i-1][j];
                }
            }
            res = Math.max(res, maxRecArea(his[i]));
        }

        return res;
    }

    public int maxRecArea(int[] arr){
        int n = arr.length;
        // for(int a : arr)
        //     System.out.print(a +" ");

        // System.out.println();

        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> s = new Stack<>();
        s.add(0);
        left[0] = -1;

        for(int i=1; i<n; i++){
            while(i<n && !s.isEmpty() && arr[s.peek()] >= arr[i]){
                s.pop();
            }
            if(s.isEmpty()) left[i] = -1;
            else left[i] = s.peek();
            s.add(i);
        }

        s.clear();

        s.add(n-1);
        right[n-1] = n;
        
        for(int i=n-2; i>=0; i--){
            while(i>=0 && !s.isEmpty() && arr[s.peek()] >= arr[i]){
                s.pop();
            }

            if(s.isEmpty()) right[i] = n;
            else right[i] = s.peek();

            s.add(i);
        }

        // for(int l : left)
        //     System.out.print(l +" ");

        // System.out.println();

        // for(int r : right)
        //     System.out.print(r + " ");

        // System.out.println();

        int res = 0;

        for(int i=0; i<n; i++){
            if(arr[i] != 0){
                int calArea = (right[i]-left[i]-1)*arr[i];
                // System.out.print(calArea + " ");
                res = Math.max(res, calArea);

            }
        }
        // System.out.println();

        return res;
    }
}