class LRUCache {
    class Node{
        Node next;
        Node prev;
        int key;
        int value;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer, Node> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;

    }
    
    public int get(int key) {
        if(!cache.containsKey(key)) 
            return -1;

        Node cur = cache.get(key);
        updateNode(cur);

        return cur.value;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            removeNode(node);
            cache.remove(key);
        }

        if(capacity == cache.size()){
            Node LRUNode = getAndRemoveLRUNode();
            cache.remove(LRUNode.key);
            removeNode(LRUNode);
        }
        addNode(key, value);
    }

    public void removeNode(Node cur){
        
        Node prev = cur.prev;
        Node next = cur.next;

        prev.next = next;
        next.prev = prev;
    }

    public void addNode(int key, int value){
        Node cur = new Node(key, value);
        Node next = head.next;

        head.next = cur;
        next.prev = cur;

        cur.prev = head;
        cur.next = next;

        cache.put(key, cur);
    }

    public Node getAndRemoveLRUNode(){
        return tail.prev;
    }

    public void updateNode(Node cur){
        removeNode(cur);
        addNode(cur.key, cur.value);
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */