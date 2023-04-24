package src.task2;

import static src.Printer.black;
import static src.Printer.blue;
import static src.Printer.yellow;

import java.util.HashMap;

public class Main
{
    final static int CAPACITY = 5000011;
    final static int MAX_VALUE = 50000000;
    final static double LOAD_FACTOR = 0.99;

    static HashTable hashTable = new HashTable(CAPACITY);
    static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    public static void main(String[] args)
    {
        int numNumbers = (int)(CAPACITY * LOAD_FACTOR);

        System.out.println(black("\nTesting with " + blue(CAPACITY) + " capacity and " + blue(numNumbers) + " numbers."));
        int[] randomNumbers = RandomArray.get((int)(CAPACITY * LOAD_FACTOR), MAX_VALUE);

        long timeJava = timeJAVAHashMap(randomNumbers);
        System.out.println(blue("\nJAVA HashMap:"));
        System.out.println("\tInsertion Time: " + yellow(timeJava) + "ms");

        long time = timeMyHashTable(randomNumbers);
        System.out.println(blue("\nMy HashTable:"));
        System.out.println("\tInsertion Time: " + yellow(time) + "ms");
        System.out.println("\tCollisions:     " + yellow(hashTable.numCollisions)
                           + " (" + yellow(((double)hashTable.numCollisions) / CAPACITY) + "/insert)");
        System.out.println("\tLoad Factor:    " + yellow(hashTable.getLoadFactor()) + "\n");
    }

    private static long timeMyHashTable(int[] insert)
    {
        assert insert.length <= hashTable.CAPACITY;
        long start = System.currentTimeMillis();
        {
            for (int nr : insert)
            {
                hashTable.insert(nr);
            }
        }
        long time = System.currentTimeMillis() - start;
        return time;
    }

    private static long timeJAVAHashMap(int[] insert)
    {
        assert insert.length <= hashTable.CAPACITY;
        long start = System.currentTimeMillis();
        {
            for (int nr : insert)
            {
                hashMap.put(nr, nr);
            }
        }
        long time = System.currentTimeMillis() - start;
        return time;
    }
}
