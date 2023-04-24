package src.task1;

class Node
{
    String name;
    Node next;

    Node(String name)
    {
        this.name = name;
        this.next = null;
    }

    public int getNumChildren()
    {
        if (this.next == null) return 0;
        return 1 + this.next.getNumChildren();
    }

    @Override
    public String toString()
    {
        String str = this.name;
        if (this.next != null) str += " -> " + this.next.toString();
        return str;
    }
}
