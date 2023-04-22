
public class Oppgave
{
    public static void main(String[] agrs)
    {
        Vektet_Graf graf1 = new Vektet_Graf("flytgraf1");
        Vektet_Graf graf2 = new Vektet_Graf("flytgraf2");
        Vektet_Graf graf3 = new Vektet_Graf("flytgraf3");

        graf1.edmond_karp();
        graf2.edmond_karp();
        graf3.edmond_karp();
    }
}
