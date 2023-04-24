package src;

public class Queue
{
    public QueueMember first;

    public Queue()
    {
    }

    public Queue(Object first)
    {
        this.first = new QueueMember(first);
    }

    public Object next()
    {
        Object next = first.obj;
        this.first = first.next;
        return next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void add(Object obj)
    {
        if (first == null)
        {
            first = new QueueMember(obj);
            return;
        }
        first.behind(obj);
    }
}

class QueueMember
{
    QueueMember next = null;
    Object obj;

    QueueMember(Object obj)
    {
        this.obj = obj;
    }

    void behind(Object obj)
    {
        if (next == null)
        {
            next = new QueueMember(obj);
            return;
        }
        next.behind(obj);
    }
}
