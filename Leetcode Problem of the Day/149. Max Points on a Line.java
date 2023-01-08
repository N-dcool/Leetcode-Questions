/*
Given an array of points where points[i] = [xi, yi] 
represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

 

Example 1:
    Input: points = [[1,1],[2,2],[3,3]]
    Output: 3

Example 2:
    Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4
 

Constraints:
    1 <= points.length <= 300
    points[i].length == 2
    -104 <= xi, yi <= 104
    All the points are unique.
*/

class Solution {
    public int maxPoints(int[][] points) {
        if (points.length <= 2)
            return points.length;
        
        int max = 0;
		int i = 1;
        for (int point[]: points) 
            max = Math.max(max, getMaxCount(point[0], point[1], points, i++));
        
        return max;
    }
    
    int getMaxCount(int x, int y, int[][] points, int start) {
        int vLine = Integer.MAX_VALUE;   // vertical line
        int hLine = Integer.MIN_VALUE;   // horizontal line
        Map<Double, Integer> counts = new HashMap<>();
        
        int max = 0;
        for (int i=start; i<points.length; i++) {
            int x2 = points[i][0];
            int y2 = points[i][1];
              
            double key = (x == x2 ? vLine : y == y2 ? hLine : (double) (y2 - y) / (x2 - x));
            counts.merge(key, 2, (a, b) -> a + b - 1);
            max = Math.max(max, counts.get(key));
        }        
        return max;
    }
}
