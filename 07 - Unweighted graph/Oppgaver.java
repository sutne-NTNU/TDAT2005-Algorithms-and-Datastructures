
import static src.ColorPrint.print;
import static src.ColorPrint.println;

public class Oppgaver
{
    private static final int DRAMMEN = 65205;
    private static final int HELSINKI = 3378527;
    private static final int MOHOLT = 18058;
    private static final int KALVSKINNET = 37774;

    public static void main(String[] args)
    {
        Graf L7g1 = new Graf("L7g1");
        Graf L7g2 = new Graf("L7g2");
        Graf L7g3 = new Graf("L7g3");
        Graf L7g5 = new Graf("L7g5");
        Graf L7Skandinavia = new Graf("L7Skandinavia");

        print("blue", "\n\n\nL7g1 - ");
        print("yellow", "deloppgave 1\n");
        L7g1.BFS(5);
        L7g1.BFS(5, 4, true);

        println("blue", "\nL7g2");
        L7g2.BFS(5, 39, true);

        println("blue", "\nL7g3");
        L7g3.BFS(5, 2996, true);

        print("blue", "\nL7g5 - ");
        print("yellow", "deloppgave 2\n");
        L7g5.topolopogisk_sort();

        print("blue", "\n\n\nMoholt -> Kalvskinnet - ");
        print("yellow", "ekstra\n");
        L7Skandinavia.BFS(MOHOLT, KALVSKINNET, false);

        println("blue", "\nDrammen -> Helsinki");
        L7Skandinavia.BFS(DRAMMEN, HELSINKI, false);
    }
}
