/*
You are given an m x n grid grid where:

'.' is an empty cell.
'#' is a wall.
'@' is the starting point.
Lowercase letters represent keys.
Uppercase letters represent locks.
You start at the starting point and one move consists of walking one space in one of the four cardinal directions. 
You cannot walk outside the grid, or walk into a wall.

If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.

For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English 
alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the 
letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys. If it is impossible, return -1.

 

Example 1:
    Input: grid = ["@.a..","###.#","b.A.B"]
    Output: 8
    Explanation: Note that the goal is to obtain all the keys not to open all the locks.

Example 2:
    Input: grid = ["@..aA","..B#.","....b"]
    Output: 6

Example 3:
    Input: grid = ["@Aa"]
    Output: -1
 

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 30
    grid[i][j] is either an English letter, '.', '#', or '@'.
    The number of keys in the grid is in the range [1, 6].
    Each key in the grid is unique.
    Each key in the grid has a matching lock.
*/

class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        Queue<int[]> queue = new LinkedList<>();
        int[][] moves = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        
        // seen['key'] is only for BFS with key state equals 'keys'
        Map<Integer, Set<Pair<Integer, Integer>>> seen = new HashMap<>();
        
        Set<Character> keySet = new HashSet<>();
        Set<Character> lockSet = new HashSet<>();
        int allKeys = 0;
        int startR = -1, startC = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i].charAt(j);
                if (cell >= 'a' && cell <= 'f') {
                    allKeys += (1 << (cell - 'a'));
                    keySet.add(cell);
                }
                if (cell >= 'A' && cell <= 'F') {
                    lockSet.add(cell);
                }
                if (cell == '@') {
                    startR = i;
                    startC = j;
                }
            }
        }

        // [row, column, key state, distance]
        queue.offer(new int[] {startR, startC, 0, 0});
        seen.put(0, new HashSet<>());
        seen.get(0).add(new Pair<>(startR, startC));    
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0], curC = cur[1], keys = cur[2], dist = cur[3];
            for (int[] move : moves) {
                int newR = curR + move[0], newC = curC + move[1];
                
                // If this cell (newR, newC) is reachable.
                if (newR >= 0 && newR < m && newC >= 0 && newC < n && grid[newR].charAt(newC) != '#') {
                    char cell = grid[newR].charAt(newC);
                    
                    // If it is a key:
                    if (keySet.contains(cell)) {
                        // If we have collected it before, no need to revisit this cell.
                        if (((1 << (cell - 'a')) & keys) != 0) {
                            continue;
                        }
                        
                        // Otherwise, we can walk to this cell and pick it up.
                        int newKeys = (keys | (1 << (cell - 'a')));
                        
                        // If we collect all keys, return dist + 1. 
                        // Otherwise, just add this state to seen and queue.
                        if (newKeys == allKeys) {
                            return dist + 1;
                        }
                        seen.putIfAbsent(newKeys, new HashSet<>());
                        seen.get(newKeys).add(new Pair<>(newR, newC));
                        queue.offer(new int[] {newR, newC, newKeys, dist + 1});
                    }
                    
                    // If it is a lock and we don't have its key, continue.
                    if (lockSet.contains(cell) && ((keys & (1 << (cell - 'A'))) == 0)) {
                        continue;
                    }
                        
                    // We can walk to this cell if we haven't been here before with the same key state.
                    else if (!seen.get(keys).contains(new Pair<>(newR, newC))) {
                        seen.get(keys).add(new Pair<>(newR, newC));
                        queue.offer(new int[] {newR, newC, keys, dist + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}
