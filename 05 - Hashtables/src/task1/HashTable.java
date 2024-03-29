package src.task1;

import static src.Printer.blue;
import static src.Printer.yellow;

public class HashTable
{
    public static void main(String[] args)
    {
        int capacity = 128;
        HashTable hashTable = new HashTable(capacity);

        for (String name : NameList.names) hashTable.insert(name);

        int numNames = hashTable.getNumElements();

        System.out.println("\nThe load factor was: " + yellow((numNames * 1.0) / capacity));
        System.out.println("There was " + yellow(hashTable.numCollitions) + " Collitions when inserting " + yellow(numNames) + " names ");
        System.out.println("This gives a average of " + yellow((hashTable.numCollitions * 1.0) / numNames) + " collitions/insertion\n");
        System.out.println("Index of " + blue("'Utne,Sivert'") + ": " + blue(hashTable.indexOf("Utne,Sivert")));
        System.out.println("Index of " + blue("'Not,Intable'") + ": " + blue(hashTable.indexOf("Not,Intable")));
        System.out.println("\nFilled HashTable:\n" + hashTable);
    }

    final int CAPACITY;
    Node[] table;
    int numCollitions = 0;

    HashTable(int capacity)
    {
        this.CAPACITY = capacity;
        this.table = new Node[capacity];
    }

    int hash(String name)
    {
        if (name == null) return -1;
        int hash = 0;
        for (int i = 0; i < name.length(); i++)
        {
            if (name.charAt(i) == ',') continue;
            hash += (i + 1) * name.charAt(i);
        }
        return hash % this.CAPACITY;
    }

    void insert(String name)
    {
        if (name == null) return;
        int hash = this.hash(name);

        boolean indexIsFree = this.table[hash] == null;
        if (indexIsFree)
        {
            this.table[hash] = new Node(name);
            return;
        }
        this.handleCollition(hash, this.table[hash], 1, name);
    }

    void handleCollition(int index, Node node, int collitionCounter, String name)
    {
        this.numCollitions++;
        if (node.next == null)
        {
            node.next = new Node(name);
            return;
        }
        this.handleCollition(index, node.next, collitionCounter + 1, name);
    }

    int indexOf(String name)
    {
        int index = this.hash(name);
        if (this.table[index] == null) return -1;
        if (this.nameIsInLinkedList(this.table[index], name))
        {
            return index;
        }
        return -1;
    }

    boolean nameIsInLinkedList(Node node, String name)
    {
        if (node.name.equals(name)) return true;
        if (node.next == null) return false;
        return this.nameIsInLinkedList(node.next, name);
    }

    int getNumElements()
    {
        int numElements = 0;
        for (Node n : this.table)
        {
            if (n == null) continue;
            numElements += 1 + n.getNumChildren();
        }
        return numElements;
    }

    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < this.table.length; i++)
        {
            str += i + ": \t";
            Node n = this.table[i];
            str += "[" + (n == null ? "" : n) + "]\n";
        }
        return str;
    }
}
