package src;

public class Road
{
    public int toIndex;
    /**
     * Time to drive this road in 1/100 seconds
     */
    public int time;
    /**
     * Speed Limit in km/h
     */
    public int speedLimit;
    /**
     * length in meters
     */
    public int length;

    Road(int toIndex, int time, int length, int speedLimit)
    {
        this.toIndex = toIndex;
        this.time = time;
        this.length = length;
        this.speedLimit = speedLimit;
    }
}
