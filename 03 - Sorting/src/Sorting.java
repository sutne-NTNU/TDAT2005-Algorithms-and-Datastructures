package src;

import static src.Printer.blue;
import static src.Printer.yellow;

public class Sorting
{

    public static void main(String[] args)
    {
        MyArray array = new MyArray(1_000_000);

        test(array, 0);
        test(array, 2);
        test(array, 3);
        test(array, 5);
        test(array, 25);
        test(array, 500);
    }

    public static void test(MyArray array, int bubbleLimit)
    {
        if (bubbleLimit <= 0)
        {
            System.out.println("\nQuicksort:");
        }
        else
        {
            System.out.println("\nMy Quicksort, using BubbleSort Limit: " + blue(bubbleLimit));
        }
        long start = System.currentTimeMillis();
        {
            array.sort(bubbleLimit);
        }
        double time = System.currentTimeMillis() - start;

        System.out.println("\tTime:      " + yellow(time + "ms"));
        System.out.println("\tisSorted:  " + Printer.toString(array.isSorted()));
        System.out.println("\ttestSum:   " + Printer.toString(array.hasSameSum()));
    }
}
