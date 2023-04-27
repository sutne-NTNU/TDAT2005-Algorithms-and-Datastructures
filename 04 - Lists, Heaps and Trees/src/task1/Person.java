package src.task1;

public class Person
{
    final int startPosition;
    Person next;

    Person(int startPosition)
    {
        this.startPosition = startPosition;
    }

    @Override
    public String toString()
    {
        return "Person(position=" + this.startPosition + ", next=" + this.next.startPosition + ")";
    }
}
