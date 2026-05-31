class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long totalPlanetMass = mass;

        for(int asteroid: asteroids) {
            if(totalPlanetMass < asteroid) {
                return false;
            }
            totalPlanetMass += asteroid;
        }

        return true;
    }
}