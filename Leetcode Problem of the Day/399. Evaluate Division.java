/*
You are given an array of variable pairs equations and an array of real numbers values, where 
equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 

Example 1:
    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
    Explanation: 
    Given: a / b = 2.0, b / c = 3.0
    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
    return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
    Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
    Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:
    1 <= equations.length <= 20
    equations[i].length == 2
    1 <= Ai.length, Bi.length <= 5
    values.length == equations.length
    0.0 < values[i] <= 20.0
    1 <= queries.length <= 20
    queries[i].length == 2
    1 <= Cj.length, Dj.length <= 5
    Ai, Bi, Cj, Dj consist of lower case English letters and digits.
*/

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // creating graph
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        
        // building graph:
        graph = buildGraph(equations, values);
        
        // storing ans:
        double[] res = new double[queries.size()];
        
        // dfs traversal:)
        for(int i=0; i<queries.size(); i++){
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), graph);
        }
        
        
        return res;
    }
    
    public double dfs(String start, String end, Set<String> visited, HashMap<String, HashMap<String, Double>> graph){
        
        // not found:(
        if(!graph.containsKey(start))
            return -1.0;
        
        // already evalued case:)
        if(graph.get(start).containsKey(end))
            return graph.get(start).get(end);
        
        visited.add(start);
        
        for(Map.Entry<String, Double> neighbour : graph.get(start).entrySet()){
            String nextStr = neighbour.getKey();
            
            if(!visited.contains(nextStr)){
                double productWeight = dfs(nextStr, end, visited, graph);
                if(productWeight != -1.0){
                    return neighbour.getValue() * productWeight;
                }
            }
        }
        
        return -1.0;
    }
    
    public HashMap<String, HashMap<String, Double>> buildGraph(List<List<String>> eqs, double[] vals){
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        
        String u,v;
        double val;
        
        for(int i=0; i<eqs.size(); i++){
            u = eqs.get(i).get(0);
            v = eqs.get(i).get(1);
            val = vals[i];
            
            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, val);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1/val);
            
        }
        
        return graph;
    }
} 
