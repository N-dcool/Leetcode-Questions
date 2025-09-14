class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        HashSet<String> exactMatchSet = new HashSet<>();
        HashMap<String,Integer> lowerCaseMap = new HashMap<>();
        HashMap<String,Integer> vowelMap = new HashMap<>();

        for(int i=0; i<wordlist.length; i++){
            String word = wordlist[i];

            exactMatchSet.add(word);
            lowerCaseMap.putIfAbsent(word.toLowerCase(), i);
            vowelMap.putIfAbsent(encode(word.toLowerCase()), i);
            

        }

        int n = queries.length;
        String[] res = new String[n];

        for(int i=0; i<n; i++){
            String q = queries[i];
            String encodedString = encode(q);
            System.out.println(i + " " + encodedString);
            if(exactMatchSet.contains(q)){
                res[i] = q;
            } else if(lowerCaseMap.containsKey(q.toLowerCase())){
                res[i] = wordlist[lowerCaseMap.get(q.toLowerCase())];
            } else if(vowelMap.containsKey(encode(q.toLowerCase()))){
                res[i] = wordlist[vowelMap.get(encode(q.toLowerCase()))];
            } else{
                res[i] = "";
            }
        }

        return res; 
    }

    public boolean isVowel(char c){
        String vowel = "aeiouAEIOU";

        for(char v : vowel.toCharArray()){
            if(c == v) return true;
        }

        return false;
    }

    public String encode(String word){

        StringBuilder sb = new StringBuilder();

        for(char c : word.toCharArray()){
            sb.append(isVowel(c) ? '*' : c);
        }

        return sb.toString();
    }
    
}

class TrieNode{
    TrieNode[] children;
    boolean isEnd;

    public void Tries(){
        children = new TrieNode[26];
        isEnd = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }
}

