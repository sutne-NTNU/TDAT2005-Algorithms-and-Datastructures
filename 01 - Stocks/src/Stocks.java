package src;

import java.util.ArrayList;

public class Stocks
{
    public static Day[] initializeDays(int numDays)
    {
        Day.nrCounter = 0; // reset static counter
        Day[] days = new Day[numDays];
        for (int i = 0; i < numDays; i++)
        {
            Day d = new Day();
            days[i] = d;
        }
        return days;
    }

    /**
     * Time Complexity: n
     *
     * Extracts all days at local minimums
     */
    public static ArrayList<Day> getBuyDays(Day[] days)
    {
        ArrayList<Day> buyDays = new ArrayList<Day>();
        buyDays.add(days[0]);
        for (int i = 1; i < days.length - 1; i++)
        {
            Day yesterday = days[i - 1];
            Day today = days[i];
            Day tomorrow = days[i + 1];
            if (yesterday.value > today.value && today.value < tomorrow.value)
                buyDays.add(today);
        }
        return buyDays;
    }

    /**
     * Time Complexity: n
     *
     * Extracts all days at local maximums (peaks)
     */
    public static ArrayList<Day> getSellDays(Day[] days)
    {
        ArrayList<Day> sellDays = new ArrayList<Day>();
        for (int i = 1; i < days.length - 1; i++)
        {
            Day yesterday = days[i - 1];
            Day today = days[i];
            Day tomorrow = days[i + 1];
            if (yesterday.value < today.value && today.value > tomorrow.value)
                sellDays.add(today);
        }
        sellDays.add(days[days.length - 1]);
        return sellDays;
    }

    /**
     * Time Complexity: `O(n^2)`
     */
    public static void findBestBuySell(Day[] days)
    {
        double profitPercent = 0;
        double profitValue = 0;
        int bestBuyDay = 0;
        int bestSellDay = 0;
        for (Day buyDay : getBuyDays(days))
        {
            for (Day sellDay : getSellDays(days))
            {
                if (sellDay.nr <= buyDay.nr) continue; // can't sell before buying
                double sellValue = sellDay.value - buyDay.value;
                if (sellValue <= profitValue) continue; // previous found profit is better
                profitValue = sellValue;
                bestBuyDay = buyDay.nr;
                bestSellDay = sellDay.nr;
                profitPercent = profitValue / buyDay.value * 100;
            }
        }
        System.out.println("Results: "
                           + "\n\tBuy on day:  " + bestBuyDay + " (value: " + days[bestBuyDay].value + ")"
                           + "\n\tSell on day: " + bestSellDay + " (value: " + days[bestSellDay].value + ")"
                           + "\n\tProfit:      " + ColorPrint.string("green", profitPercent + "%"));
    }
}
