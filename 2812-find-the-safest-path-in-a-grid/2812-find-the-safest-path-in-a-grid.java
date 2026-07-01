class Solution {
    
    public record Pair(int row, int col) { }
    
    public int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public int maximumSafenessFactor(List<List<Integer>> gridList) {
        int n = gridList.size();
        int m = gridList.get(0).size();
        Queue<Pair> one = new LinkedList<>();
        int[][] grid = createArrayGrid(gridList, n, m, one);
        
        if(grid[0][0]==1 || grid[n-1][m-1]==1) return 0;
        
        multipleBFSfromOne(grid, n, m, one);
        
//         for(int[] gr : grid){
//             for(int g : gr)
//                 System.out.print(g +" ");
//             System.out.println();
//         }
        
//         System.out.println(checkBFS(grid, n, m, 2));
        
        int left = 1;
        int right = Math.max(m,n);
        
        while(left <= right){
            int mid = (right+left)/2;
            if(checkBFS(grid, n, m, mid))
                left = mid+1;
            else
                right = mid-1;
        }
        
        return left-1;
        
    }
    
    public boolean checkBFS(int[][] grid, int n, int m, int k){
        if(grid[0][0] <= k || grid[n-1][m-1] <= k) return false;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        boolean[][] vis = new boolean[n][m];
        vis[0][0] = true;
        
        while(!q.isEmpty()){
            Pair cur = q.remove();
            int a = cur.row;
            int b = cur.col;
            
            if(a==n-1 && b==m-1) return true;
            
            for(int[] dir : dirs){
                int x = a + dir[0];
                int y = b + dir[1];
                if(x<n && y<m && x>=0 && y>=0 && !vis[x][y] && grid[x][y]>k){
                    q.add(new Pair(x,y));
                    vis[x][y] = true;
                }
            }
        }
                   
        return false;
    }
    
    public void multipleBFSfromOne(int[][] grid, int n, int m, Queue<Pair> q){
        
        int dis = 2;
        
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-->0){
                Pair cur = q.remove();
                int a = cur.row;
                int b = cur.col;
                
                for(int[] dir : dirs){
                    int x = a + dir[0];
                    int y = b + dir[1];
                    if(x<n && y<m && x>=0 && y>=0 && grid[x][y]==0){
                        grid[x][y] = dis;
                        q.add(new Pair(x,y));
                    }
                }
            }
                dis++;
        }
    }
    
    
    
    public int[][] createArrayGrid(List<List<Integer>> grid, int n, int m, Queue<Pair> one){
        int[][] gridArray = new int[n][m];
        
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++){
                if(grid.get(i).get(j) == 1){
                    gridArray[i][j] = 1;
                    one.add(new Pair(i,j));
                }

            }
        
        return gridArray;
    }
}