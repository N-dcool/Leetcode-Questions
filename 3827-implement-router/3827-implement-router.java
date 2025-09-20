class Router {

    class Packet{
        int source;
        int destination;
        int timestamp;

        public Packet(int s, int d, int t){
            this.source = s;
            this.destination = d;
            this.timestamp = t;
        }

        // Getters for properties
        public int getSource() { return source; }
        public int getDestination() { return destination; }
        public int getTime() { return timestamp; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            
            Packet packet = (Packet) o;
            return source == packet.source &&
                destination == packet.destination &&
                timestamp == packet.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, timestamp);
        }

        @Override
        public String toString(){
            return "Packet{ source= " + source + ", destination= " + destination + ", timestamp=" + timestamp + "}";
        }
    }

    HashSet<Packet> curPacketSet;
    HashMap<Integer, List<Integer>> destinationToTimestamp;
    Queue<Packet> packetQueue;
    int limit;

    public Router(int memoryLimit) {
        curPacketSet = new HashSet<>();
        destinationToTimestamp = new HashMap<>();
        packetQueue = new LinkedList<>();
        limit = memoryLimit;
        
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        Packet p = new Packet(source, destination, timestamp);
        if(curPacketSet.contains(p)) return false;

        
        int curSize = curPacketSet.size();

        if(curSize !=0 && curSize >= limit){
            forwardPacket();
        }

        curPacketSet.add(p);
        destinationToTimestamp.computeIfAbsent(destination, k-> new ArrayList<>()).add(timestamp);
        packetQueue.add(p);

        return true;
    }
    
    public int[] forwardPacket() {
        if(curPacketSet.size() == 0) return new int[0];

        Packet p = packetQueue.poll();
        curPacketSet.remove(p);
        destinationToTimestamp.get(p.destination).remove(0);

        return new int[]{p.source, p.destination, p.timestamp};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> timestampList = destinationToTimestamp.getOrDefault(destination, new ArrayList<>());
        int size = timestampList.size();

        int startIdx = lowerBound(timestampList, startTime, size);
        int endIdx = lowerBound(timestampList, endTime+1, size);

        return endIdx - startIdx;

    }

    private int lowerBound(List<Integer> arr, int target, int n){
        int left = 0;
        int right = n-1;
        int idx = n;

        while(left <= right){
            int mid = (right+left)/2;

            if(arr.get(mid) >= target){
                idx = mid;
                right = mid-1;
            } else{
                left = mid+1;
            }
        }

        return idx;
    }

}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */