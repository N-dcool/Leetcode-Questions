class Solution {

    public long minimumCost(
        String source,
        String target,
        char[] original,
        char[] changed,
        int[] cost
    ) {
        // Create a graph representation of character conversions
        List<int[]>[] adjacencyList = new List[26];
        for (int i = 0; i < 26; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Populate the adjacency list with character conversions
        int conversionCount = original.length;
        for (int i = 0; i < conversionCount; i++) {
            adjacencyList[original[i] - 'a'].add(
                    new int[] { changed[i] - 'a', cost[i] }
                );
        }

        // Calculate shortest paths for all possible character conversions
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            minConversionCosts[i] = dijkstra(i, adjacencyList);
        }

        // Calculate the total cost of converting source to target
        long totalCost = 0;
        int stringLength = source.length();
        for (int i = 0; i < stringLength; i++) {
            if (source.charAt(i) != target.charAt(i)) {
                long charConversionCost =
                    minConversionCosts[source.charAt(i) - 'a'][target.charAt(
                            i
                        ) -
                        'a'];
                if (charConversionCost == -1) {
                    return -1; // Conversion not possible
                }
                totalCost += charConversionCost;
            }
        }
        return totalCost;
    }

    // Find minimum conversion costs from a starting character to all other characters
    private long[] dijkstra(int startChar, List<int[]>[] adjacencyList) {
        // Priority queue to store characters with their conversion cost, sorted by cost
        PriorityQueue<Pair<Long, Integer>> priorityQueue = new PriorityQueue<>(
            (e1, e2) -> Long.compare(e1.getKey(), e2.getKey())
        );

        // Initialize the starting character with cost 0
        priorityQueue.add(new Pair<>(0L, startChar));

        // Array to store the minimum conversion cost to each character
        long[] minCosts = new long[26];
        // Initialize all costs to -1 (unreachable)
        Arrays.fill(minCosts, -1L);

        while (!priorityQueue.isEmpty()) {
            Pair<Long, Integer> currentPair = priorityQueue.poll();
            long currentCost = currentPair.getKey();
            int currentChar = currentPair.getValue();

            if (
                minCosts[currentChar] != -1L &&
                minCosts[currentChar] < currentCost
            ) continue;

            // Explore all possible conversions from the current character
            for (int[] conversion : adjacencyList[currentChar]) {
                int targetChar = conversion[0];
                long conversionCost = conversion[1];
                long newTotalCost = currentCost + conversionCost;

                // If we found a cheaper conversion, update its cost
                if (
                    minCosts[targetChar] == -1L ||
                    newTotalCost < minCosts[targetChar]
                ) {
                    minCosts[targetChar] = newTotalCost;
                    // Add the updated conversion to the queue for further exploration
                    priorityQueue.add(new Pair<>(newTotalCost, targetChar));
                }
            }
        }
        // Return the array of minimum conversion costs from the starting character to all others
        return minCosts;
    }
}