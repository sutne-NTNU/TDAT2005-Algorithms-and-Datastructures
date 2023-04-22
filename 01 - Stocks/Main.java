import src.Day;
import src.Stocks;

public class Main
{
    public static void main(String[] args)
    {
        final int NUM_DAYS = 10_000;
        Day[] days = Stocks.initializeDays(NUM_DAYS);

        long start = System.currentTimeMillis();
        Stocks.findBestBuySell(days);
        long timeMs = System.currentTimeMillis() - start;

        System.out.printf("Time for %d days: %d ms\n", NUM_DAYS, timeMs);
    }
}
