package src;

public class Queue<T>
{
    public QueueMember<T> first;

    public Queue() { }
    public Queue(T first)
    {
        this.first = new QueueMember<T>(first);
    }

    public T getNext()
    {
        T next = this.first.obj;
        this.first = this.first.next;
        return next;
    }

    public boolean isEmpty()
    {
        return this.first == null;
    }

    public void add(T obj)
    {
        if (this.first == null)
        {
            this.first = new QueueMember<T>(obj);
            return;
        }
        this.first.addNext(obj);
    }
}

class QueueMember<T>
{
    QueueMember<T> next = null;
    T obj;

    QueueMember(T obj)
    {
        this.obj = obj;
    }

    void addNext(T obj)
    {
        if (this.next == null)
        {
            this.next = new QueueMember<T>(obj);
            return;
        }
        this.next.addNext(obj);
    }
}
