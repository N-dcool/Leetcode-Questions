/*
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei]
indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is 
no such route, return -1.

 
Example 1:
    Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
    Output: 700
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
    Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

Example 2:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.

Example 3:
    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The graph is shown above.
    The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 

Constraints:
    1 <= n <= 100
    0 <= flights.length <= (n * (n - 1) / 2)
    flights[i].length == 3
    0 <= fromi, toi < n
    fromi != toi
    1 <= pricei <= 104
    There will not be any multiple flights between two cities.
    0 <= src, dst, k < n
    src != dst
*/


class Solution {
    record Tuple(int stop, int node, int cost){ }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> adj = new HashMap<>();
        
        for(int[] f : flights)
            adj.computeIfAbsent(f[0], v -> new ArrayList()).add(new int[] {f[1], f[2]});
        
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(0, src, 0));   
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        while(!q.isEmpty()){
            Tuple curFlight = q.poll();
            int stop = curFlight.stop;
            int node = curFlight.node;
            int cost = curFlight.cost;
            
            if(stop > k || !adj.containsKey(node))
                continue;
            for(int[] pair: adj.get(node)){
                int adjNode = pair[0];
                int travelCost = pair[1];
                
                if(dist[adjNode] > travelCost+cost && stop<=k){
                    dist[adjNode] = travelCost+cost;
                    q.offer(new Tuple(stop+1, adjNode, dist[adjNode]));
                }
            } 
        }
        
        return dist[dst]==Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> adj = new HashMap<>();
        
        for(int[] f : flights)
            adj.computeIfAbsent(f[0], value-> new ArrayList<>()).add(new int[]{f[1], f[2]});
        
        for(int i=0; i<=3; i++)
            if(adj.containsKey(i))
                for(int[] e: adj.get(i))
                    System.out.println(i + ": " + e[0] + " " + e[1]);
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {src, 0});
        int stops = 0;
        
        while(!q.isEmpty() && stops<=k) {
            int size = q.size();
            
            while(size-->0){
                int[] temp = q.poll();
                int curNode = temp[0];
                int curCost = temp[1];
                
                //if(curNode == dst)
                System.out.println(curNode);
                
                if(!adj.containsKey(curNode))
                    continue;
                
                for(int[] e : adj.get(curNode)){
                    int nextNode = e[0];
                    int price = e[1];
                    price += curCost;
                    
                    q.offer(new int[] {nextNode, price});
                }
            }
            stops++;
        }
        
        return -1;
    }
}
