class Solution {
    public int maximumProduct(int[] A) {
        int a = -1001, b = a, c = b;
        int x =  1001, y = x;

        for (int n : A) {
            int pa = a, pb = b, px = x;
            
            a = Math.max(a, n);
            b = Math.max(b, Math.min(pa, n));
            c = Math.max(c, Math.min(pb, n));
            
            x = Math.min(x, n);
            y = Math.min(y, Math.max(px, n));
        }

        return Math.max(a * b * c, a * x * y);
    }
}