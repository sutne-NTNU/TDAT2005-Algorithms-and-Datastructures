package src.utils;

public class Time
{
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hundredths)
    {
        double seconds = (double)hundredths / 100.0;
        double minutes = seconds / 60.0;
        double hours = minutes / 60.0;
        minutes = (hours % 1) * 60;
        seconds = (minutes % 1) * 60;
        this.hours = (int)hours;
        this.minutes = (int)minutes;
        this.seconds = (int)Math.round(seconds);
    }

    @Override
    public String toString()
    {
        String minutes = this.minutes < 10 ? "0" + this.minutes : "" + this.minutes;
        String seconds = this.seconds < 10 ? "0" + this.seconds : "" + this.seconds;
        return this.hours + ":" + minutes + ":" + seconds;
    }

    public static void main(String[] args)
    {
        int hours = 2 * (60 * 60 * 100);
        int minutes = 22 * (60 * 100);
        int seconds = 22 * (100);
        Time time = new Time(hours + minutes + seconds);
        System.out.println(time); // 2:22:22
    }
}
