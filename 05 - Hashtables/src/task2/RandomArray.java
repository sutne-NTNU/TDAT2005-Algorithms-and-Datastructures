package src.task2;

import java.util.Random;

class RandomArray
{
    static int[] get(int length, int valueLimit)
    {
        Random random = new Random();
        random.setSeed(valueLimit);

        int[] array = new int[length];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(1, valueLimit);
        }
        return array;
    }
}
