class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();

        for(int i=0; i<wordsContainer.length; i++) {
            trie.insertReverse(wordsContainer[i], i);
        }

        int size = wordsQuery.length;
        int[] res = new int[size];

        for(int i=0; i<size; i++){
            res[i] = trie.longestCommonSuffix(wordsQuery[i]);
        }

        return res;
    }

    class TrieNode {
        private TrieNode[] children;
        public int minLen = Integer.MAX_VALUE;
        public int idx = Integer.MAX_VALUE;

        public TrieNode() {
            children = new TrieNode[26];
        }

        public boolean containsChar(char c) {
            return children[c-'a'] != null;
        }

        public void addChar(char c) {
            children[c-'a'] = new TrieNode();
        }

        public TrieNode next(char c) {
            return children[c-'a'];
        }
        
    }

    class Trie {
        private static TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        private String reverseWord(String word) {
            return new StringBuilder(word).reverse().toString();
        }

        public void insertReverse(String word, int idx) {
            TrieNode cur = root;
            int len = word.length();

            if(len < cur.minLen) {
                cur.minLen = len;
                cur.idx = idx;
            }

            for(char c : reverseWord(word).toCharArray()) {
                if(!cur.containsChar(c)) {
                    cur.addChar(c);
                }
                cur = cur.next(c);
                if(len < cur.minLen) {
                    cur.minLen = len;
                    cur.idx = idx;
                }
            }
        }

        public int longestCommonSuffix(String query) {
            TrieNode cur = root;
            for(char c : reverseWord(query).toCharArray()) {
                if(!cur.containsChar(c)){
                    break;
                }
                cur = cur.next(c);
            }

            return cur.idx;
        }
    }

}




