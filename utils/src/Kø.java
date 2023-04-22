package src;

public class Kø
{
    public Kø_medlem FIRST;

    public Kø()
    {
    }

    public Kø(Object first)
    {
        this.FIRST = new Kø_medlem(first);
    }

    public Object get_Next_In_Line()
    {
        Object neste = FIRST.obj;
        this.FIRST = FIRST.nextInLine;
        return neste;
    }

    public boolean isEmpty()
    {
        return FIRST == null;
    }

    public void add(Object obj)
    {
        if (FIRST == null)
        {
            FIRST = new Kø_medlem(obj);
            return;
        }
        FIRST.behind_me(obj);
    }
}

class Kø_medlem
{
    Kø_medlem nextInLine = null;
    Object obj;

    Kø_medlem(Object obj)
    {
        this.obj = obj;
    }

    void behind_me(Object obj)
    {
        if (nextInLine == null)
        {
            nextInLine = new Kø_medlem(obj);
            return;
        }
        nextInLine.behind_me(obj);
    }
}
