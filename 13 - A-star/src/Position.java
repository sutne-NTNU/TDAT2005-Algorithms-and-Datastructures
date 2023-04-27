package src;

public class Position
{
    private final double latitudeDeg;
    private final double longitudeDeg;
    private final double latitudeRad;
    private final double longitudeRad;

    private final double cosLatitudeDeg;

    public Position(double latitudeDeg, double longitudeDeg)
    {
        this.latitudeDeg = latitudeDeg;
        this.longitudeDeg = longitudeDeg;
        this.latitudeRad = latitudeDeg * Math.PI / 180.0;
        this.longitudeRad = longitudeDeg * Math.PI / 180.0;
        this.cosLatitudeDeg = Math.cos(this.latitudeRad); // saves time by not recalculating this many times
    }

    private static final double MAX_SPEED_KMH = 130.0;
    private static final double HS_IN_AN_HOUR = 60 * 60 * 100;
    private static final double KM_TO_TIME_MULTIPLIER = HS_IN_AN_HOUR / MAX_SPEED_KMH;
    /**
     * Time from this position to the other position in hundredths of a second.
     */
    public int timeTo(Position other)
    {
        return (int)(KM_TO_TIME_MULTIPLIER * this.distanceTo(other));
    }

    /**
     * Distance from this position to the other position in km.
     */
    public double distanceTo(Position other)
    {
        return Position.haversine(this, other);
    }

    @Override
    public String toString()
    {
        return this.latitudeDeg + "," + this.longitudeDeg;
    }

    // https://en.wikipedia.org/wiki/Haversine_formula
    private static final double EARTH_RADIUS_KM = 6371.0;
    public static double haversine(Position a, Position b)
    {
        double sinLatResult = Math.sin((b.latitudeRad - a.latitudeRad) / 2.0);
        double sinLonResult = Math.sin((b.longitudeRad - a.longitudeRad) / 2.0);
        double sinLatSquared = sinLatResult * sinLatResult;
        double sinLonSquared = sinLonResult * sinLonResult;
        double cosProduct = a.cosLatitudeDeg * b.cosLatitudeDeg;
        return 2 * EARTH_RADIUS_KM * Math.asin(Math.sqrt(sinLatSquared + cosProduct * sinLonSquared));
    }
}
