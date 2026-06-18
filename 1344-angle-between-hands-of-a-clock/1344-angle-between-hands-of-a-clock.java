class Solution {
    public double angleClock(int hour, int minutes) {
        int hourHand = hour == 12 ? 0 : hour;

        double hourDegree = calculateHoursDegree(hourHand, minutes);
        double minDegree = calculateMinDegree(minutes);

        double diff = Math.abs(minDegree - hourDegree);

        return Math.min(360-diff, diff);
    }

    public double calculateHoursDegree(int h, int m) {
        return (30*h) + (m/2d);

    }

    public double calculateMinDegree(int m) {
        return 6d*m;
    }
}

/*
60 = 360
30 = 180
x = ?

? = (180*x)/30

minutesDegree = 6*(x min); 
------------------
each hours degree separation = 360/12 => 30^

60 min => 30^
30 min => 15^
x min => ?

hoursDegree = (x * 15)/30 => (x min)/2

-------------------
*/