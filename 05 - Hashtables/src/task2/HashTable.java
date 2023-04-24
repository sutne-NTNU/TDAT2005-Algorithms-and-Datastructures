package src.task2;

public class HashTable
{
    final int CAPACITY;
    int[] hashTable;
    int numCollisions = 0;

    HashTable(int capacity)
    {
        this.CAPACITY = capacity;
        this.hashTable = new int[capacity];
    }

    int hash(int number)
    {
        int hash = number % CAPACITY;
        if (hashTable[hash] == 0) return hash;

        int numCollitions = 0;
        while (hashTable[hash] != 0)
        {
            numCollisions++;
            hash = (hash + number + numCollitions + 1) % CAPACITY;
        }
        return hash;
    }

    void insert(int nr)
    {
        int hash = hash(nr);
        hashTable[hash] = nr;
    }

    double getLoadFactor()
    {
        double counter = 0;
        for (int i = 0; i < hashTable.length; i++)
        {
            if (hashTable[i] != 0) counter++;
        }
        return counter / hashTable.length;
    }

    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < hashTable.length; i++)
        {
            str += i + " - " + hashTable[i];
        }
        return str;
    }
}
