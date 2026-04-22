class Solution {

    static class TrieNode {

        TrieNode[] child = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.child[idx] == null) node.child[idx] = new TrieNode();
            node = node.child[idx];
        }
        node.isEnd = true;
    }

    boolean dfs(String word, int i, TrieNode node, int cnt) {
        if (cnt > 2 || node == null) return false;

        if (i == word.length()) return node.isEnd;

        int idx = word.charAt(i) - 'a';

        // no changes made
        if (node.child[idx] != null) {
            if (dfs(word, i + 1, node.child[idx], cnt)) return true;
        }

        // made changes
        if (cnt < 2) {
            for (int c = 0; c < 26; c++) {
                if (c == idx) continue;
                if (node.child[c] != null) {
                    if (dfs(word, i + 1, node.child[c], cnt + 1)) return true;
                }
            }
        }

        return false;
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        for (String w : dictionary) insert(w);

        List<String> res = new ArrayList<>();
        for (String q : queries) {
            if (dfs(q, 0, root, 0)) {
                res.add(q);
            }
        }
        return res;
    }
}