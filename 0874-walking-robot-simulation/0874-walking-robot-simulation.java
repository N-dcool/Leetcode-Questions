class Solution {

    private static final long HASH_MULTIPLIER = 60013; // Slightly larger than 2 * max coordinate value

    public int robotSim(int[] commands, int[][] obstacles) {
        // Store obstacles in an HashSet for efficient lookup
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(hashCoordinates(obstacle[0], obstacle[1]));
        }

        // Define direction vectors: North, East, South, West
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        int[] currentPosition = { 0, 0 };
        int maxDistanceSquared = 0;
        int currentDirection = 0; // 0: North, 1: East, 2: South, 3: West

        for (int command : commands) {
            if (command == -1) {
                // Turn right
                currentDirection = (currentDirection + 1) % 4;
                continue;
            }
            if (command == -2) {
                // Turn left
                currentDirection = (currentDirection + 3) % 4;
                continue;
            }

            // Move forward
            int[] direction = directions[currentDirection];
            for (int step = 0; step < command; step++) {
                int nextX = currentPosition[0] + direction[0];
                int nextY = currentPosition[1] + direction[1];
                if (obstacleSet.contains(hashCoordinates(nextX, nextY))) {
                    break;
                }
                currentPosition[0] = nextX;
                currentPosition[1] = nextY;
            }

            maxDistanceSquared = Math.max(
                maxDistanceSquared,
                currentPosition[0] * currentPosition[0] +
                currentPosition[1] * currentPosition[1]
            );
        }

        return maxDistanceSquared;
    }

    // Hash function to convert (x, y) coordinates to a unique integer value
    private long hashCoordinates(long x, long y) {
        return x + HASH_MULTIPLIER * y;
    }
}