class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();

        int[] digitSum = new int[m + 1];
        int[] nonZeroCount = new int[m + 1];

        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';

            digitSum[i + 1] = digitSum[i] + digit;
            nonZeroCount[i + 1] = nonZeroCount[i] + (digit == 0 ? 0 : 1);
        }

        int k = nonZeroCount[m];

        long[] pow10 = new long[k + 1];
        long[] prefixValue = new long[k + 1];

        pow10[0] = 1;

        int idx = 0;

        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';

            if (digit != 0) {
                prefixValue[idx + 1] = (prefixValue[idx] * 10 + digit) % MOD;
                pow10[idx + 1] = (pow10[idx] * 10) % MOD;
                idx++;
            }
        }

        int[] answer = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];

            int sum = digitSum[r + 1] - digitSum[l];

            int left = nonZeroCount[l];
            int right = nonZeroCount[r + 1];

            int len = right - left;

            if (len == 0) {
                answer[qi] = 0;
                continue;
            }

            long x = prefixValue[right] - (prefixValue[left] * pow10[len]) % MOD;
            x = (x + MOD) % MOD;

            answer[qi] = (int) ((x * sum) % MOD);
        }

        return answer;
    }
}
/*
BAD & WRONG SOLUTION :(

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        SegmentTree st = new SegmentTree(s);
        int n = queries.length;
        int idx = 0;
        int[] res = new int[n];

        for(int[] query : queries) {
            int x = parseX(s, query[0], query[1]+1);
            int sum = st.rangeSum(query[0], query[1]);

            // System.out.println("x: "+ x + " sum: "+ sum);

            res[idx++] = x*sum;
        }

        return res;
    }

    public int parseX(String s, int start, int end) {
        // System.out.println("query: "+ s.substring(start,end+1));
        String temp = s.substring(start,end).replaceAll("0","");

        if(temp.isEmpty()) return 0;

        return Integer.parseInt(temp);
    }

    class Node {
        int start, end, sum;

        Node left, right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.left = null;
            this.right = null;
        }
    }

    class SegmentTree {
        private Node root;

        public SegmentTree(String s) {
            root = build(s, 0, s.length()-1);
        }

        private Node build(String s, int start, int end) {
            if(start > end) return null;

            Node node = new Node(start, end);
            if(start == end) {
                node.sum = s.charAt(start) - '0';
            } else {

                int mid = (start+end)/2;

                node.left = build(s, start, mid);
                node.right = build(s, mid+1, end);

                node.sum = node.left.sum + node.right.sum;
            }

            return node;
        }

        public int rangeSum(int i, int j) {
            return rangeSum(root, i, j);
        }

        private int rangeSum(Node node, int start, int end) {
            if(node == null || start > node.end || end < node.start) return 0;
            if(start<=node.start && end>=node.end) return node.sum;

            return rangeSum(node.left, start, end) + rangeSum(node.right, start, end);
        }
    }
}

*/