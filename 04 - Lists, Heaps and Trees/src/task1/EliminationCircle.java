package src.task1;

import static src.Printer.*;

public class EliminationCircle
{
    private Person firstPerson;

    public static void main(String[] args)
    {
        final int NUM_PEOPLE = 40;
        final int INTERVAL = 3;

        EliminationCircle example = new EliminationCircle(NUM_PEOPLE);
        int bestPosition = example.findBestPosition(INTERVAL);
        System.out.println("\nJosephus should stand as person " + blue(bestPosition));
    }

    public EliminationCircle(int numPeople)
    {
        firstPerson = new Person(1);
        Person previous = firstPerson;

        for (int i = 2; i <= numPeople; i++)
        {
            Person ny = new Person(i);
            previous.next = ny;
            ny.next = firstPerson;
            previous = ny;
        }
    }

    public int findBestPosition(int interval)
    {
        Person previous = null;
        Person person = firstPerson;
        int counter = 0;

        while (person.next != person)
        {
            counter++;
            if (counter == interval)
            {
                previous.next = person.next;
                counter = 0;
            }
            previous = person;
            person = person.next;
        }
        return person.startPosition;
    }

    public void print(int limit)
    {
        Person person = firstPerson;
        for (int i = limit; i > 0; i--)
        {
            System.out.println(person);
            person = person.next;
        }
    }
}
