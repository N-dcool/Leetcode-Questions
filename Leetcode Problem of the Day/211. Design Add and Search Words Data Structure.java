/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. 
word may contain dots '.' where dots can be matched with any letter.
 

Example:
    Input
    ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
    [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    Output
    [null,null,null,null,false,true,true,true]
Explanation
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    wordDictionary.search("pad"); // return False
    wordDictionary.search("bad"); // return True
    wordDictionary.search(".ad"); // return True
    wordDictionary.search("b.."); // return True
 

Constraints:
    1 <= word.length <= 25
    word in addWord consists of lowercase English letters.
    word in search consist of '.' or lowercase English letters.
    There will be at most 3 dots in word for search queries.
    At most 104 calls will be made to addWord and search.
*/

class WordDictionary {
    
    TrieNode trieNode;
    public WordDictionary() {
        trieNode = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word){
            if(!node.contains(c))
                node.put(c, new TrieNode());
            node = node.get(c);
        }
        
        node.setEnd();
    }
    
    public boolean search(String word) {
        return advanceSearch(root, word, 0);
    }
    
    public boolean advanceSearch(TrieNode node, String word, int i){
        
    }
}
class TrieNode{
    private boolean isEnd = false;
    private trieNode;
    public TrieNode(){
        node = new TrieNode[26];
    }
    
    public boolean contains(char c){
        return trieNode[c - 'a'] != null;
    }
    
    public void setEnd(){
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
    
    public void put(char c, TrieNode node){
        trieNode[c - 'a'] = node;
    }
    
    public TrieNode get(char c){
        return trieNode[c-'a'];
    }
    
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
