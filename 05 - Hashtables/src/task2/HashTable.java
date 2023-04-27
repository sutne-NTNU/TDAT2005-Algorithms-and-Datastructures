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
        int hash = number % this.CAPACITY;
        if (this.hashTable[hash] == 0) return hash;

        int numCollitions = 0;
        while (this.hashTable[hash] != 0)
        {
            this.numCollisions++;
            hash = (hash + number + numCollitions + 1) % this.CAPACITY;
        }
        return hash;
    }

    void insert(int nr)
    {
        int hash = this.hash(nr);
        this.hashTable[hash] = nr;
    }

    double getLoadFactor()
    {
        double counter = 0;
        for (int i = 0; i < this.hashTable.length; i++)
        {
            if (this.hashTable[i] != 0) counter++;
        }
        return counter / this.hashTable.length;
    }

    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < this.hashTable.length; i++)
        {
            str += i + " - " + this.hashTable[i];
        }
        return str;
    }
}
