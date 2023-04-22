package src;
/**
 * Basic Object to store the data for each day
 */
public class Day
{
    public int nr;
    public double change;
    public double value;

    private static double prevValue = 100.0;
    public static int nrCounter;

    public Day()
    {
        this.nr = nrCounter;
        this.change = 3.0 - 6.0 * Math.random(); // -3.0% to +3.0%
        this.value = prevValue * (100.0 + change) / 100.0;

        nrCounter += 1;
        prevValue = this.value;
    }

    @Override
    public String toString()
    {
        return String.format("Day: (nr: %d, change: %.2f%%, value: %.2f)", nr, change, value);
    }
}
