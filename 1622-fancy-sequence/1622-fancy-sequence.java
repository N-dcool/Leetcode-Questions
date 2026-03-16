class Fancy {

    static final int MOD = 1000000007;
    List<Integer> v;
    int a;
    int b;

    public Fancy() {
        v = new ArrayList<Integer>();
        a = 1;
        b = 0;
    }

    public void append(int val) {
        v.add((int) (((long) ((val - b + MOD) % MOD) * inv(a)) % MOD));
    }

    public void addAll(int inc) {
        b = (b + inc) % MOD;
    }

    public void multAll(int m) {
        a = (int) ((((long) a * m) % MOD));
        b = (int) ((((long) b * m) % MOD));
    }

    public int getIndex(int idx) {
        if (idx >= v.size()) {
            return -1;
        }
        int ans = (int) (((((long) a * v.get(idx)) % MOD) + b) % MOD);
        return ans;
    }

    // fast exponentiation
    private int quickmul(int x, int y) {
        long ret = 1;
        long cur = x;
        while (y != 0) {
            if ((y & 1) != 0) {
                ret = (ret * cur) % MOD;
            }
            cur = (cur * cur) % MOD;
            y >>= 1;
        }
        return (int) ret;
    }

    // multiplicative inverse
    private int inv(int x) {
        return quickmul(x, MOD - 2);
    }
}