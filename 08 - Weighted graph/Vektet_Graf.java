
import static src.ColorPrint.print;
import static src.ColorPrint.println;
import static src.ColorPrint.string;
import static src.Program.vent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import src.Kø;
import src.Program;

public class Vektet_Graf
{
    private final String filnavn;
    private Hjørne[] HJØRNER;
    private int MAKS_FLYT;

    private int SLUK;
    private int KILDE;

    Vektet_Graf(String filnavn)
    {
        this.filnavn = filnavn;
        BufferedReader fil = null;
        try
        {
            String path = "src/øving_8/filer/";
            System.out.print("leser fil: " + filnavn + " - ");
            fil = new BufferedReader(new java.io.FileReader(path + filnavn));

            opprett_GRAF(fil.readLine());

            String linje = fil.readLine();
            while (linje != null)
            {
                ny_kant(linje);
                linje = fil.readLine();
            }
            System.out.print("ant_hjørner: ");
            print("green", HJØRNER.length + "\n");
        }
        catch (FileNotFoundException fnfe)
        {
            print("red", "File not found\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            Program.close(fil);
        }
    }

    private void opprett_GRAF(String første_linje_fra_fil)
    {
        String[] info_split = første_linje_fra_fil.trim().split("\\s+");
        int ant_hjørner = Integer.parseInt(info_split[0]);

        this.HJØRNER = new Hjørne[ant_hjørner];
        for (int i = 0; i < HJØRNER.length; i++)
        {
            HJØRNER[i] = new Hjørne(i);
        }
    }

    private void ny_kant(String linje_fra_fil)
    {
        String[] kant_split = linje_fra_fil.trim().split("\\s+");

        int fra = Integer.parseInt(kant_split[0]);
        int til = Integer.parseInt(kant_split[1]);
        int kapasitet = Integer.parseInt(kant_split[2]);

        HJØRNER[fra].ny_kant(new Kant(HJØRNER[fra], HJØRNER[til], kapasitet));
    }

    void edmond_karp()
    {
        finn_KILDE_og_SLUK();
        edmond_karp(KILDE, SLUK);
    }

    private void finn_KILDE_og_SLUK()
    {
        boolean[] sjekk_kilde = new boolean[HJØRNER.length];
        boolean[] sjekk_sluk = new boolean[HJØRNER.length];
        for (int i = 0; i < HJØRNER.length; i++)
        {
            sjekk_kilde[i] = true;
            sjekk_sluk[i] = true;
        }

        for (Hjørne hjørne : HJØRNER)
        {
            Kant k = hjørne.KANT;
            while (k != null)
            {
                if (k.KAPASITET != 0)
                {
                    sjekk_sluk[k.FRA.NR] = false;
                    sjekk_kilde[k.TIL.NR] = false;
                }
                k = k.NESTE;
            }
        }
        for (int i = 0; i < HJØRNER.length; i++)
        {
            if (sjekk_kilde[i])
            {
                KILDE = i;
            }
            else if (sjekk_sluk[i])
            {
                SLUK = i;
            }
        }
    }

    void edmond_karp(int kilde, int sluk)
    {
        print_info(kilde, sluk);
        while (finn_flytsøkende_vei(kilde, sluk))
        {
            vent(500);
            // fortsett å finne flytsøkende veier
        }
        print_resultat();
    }

    private void print_info(int kilde, int sluk)
    {
        println("blue", "\n" + filnavn);
        print("gray", "Maksimal flyt fra ");
        print("yellow", kilde + "");
        print("gray", " til ");
        print("yellow", sluk + "");
        print("gray", " med Edmund Karp\n");
        println("def", "Økning : Flytøkende vei");
    }

    private boolean finn_flytsøkende_vei(int kilde, int sluk) // Bredde først søk
    {
        Kø kø = new Kø(HJØRNER[kilde]);

        while (kø.FIRST != null)
        {
            Hjørne hjørne = (Hjørne)kø.get_Next_In_Line();

            Kant kant = hjørne.KANT;
            while (kant != null)
            {
                // kan ikke returnere til kilden, og ønkser ikke besøke noder vi allerede har besøkt (vil ha en forgjenger)
                if (kant.TIL.NR != kilde && kant.TIL.FORGJENGER == null && kant.restkapasitet() > 0)
                {
                    kant.TIL.FORGJENGER = hjørne;
                    kø.add(kant.TIL);

                    if (kant.TIL.NR == sluk) // kommet frem til sluket
                    {
                        øk_flyt(kilde, sluk); // print og beregn flyt
                        nullstill_forgjengere();
                        return true;
                    }
                }
                kant = kant.NESTE;
            }
        }
        return false;
    }

    private void øk_flyt(int kilde, int sluk)
    {
        int økning = 1000000000;
        Kø kø = new Kø();                         // kø for å beholde kantene vi skal endre senere
        StringBuilder path = new StringBuilder(); // formater en sterg med ruten fra kilden til sluket
        Hjørne h = HJØRNER[sluk];

        while (h.FORGJENGER != null && h != HJØRNER[kilde])
        {
            Kant k = h.FORGJENGER.KANT;
            while (k != null)
            {
                if (k.TIL.NR == h.NR)
                {
                    kø.add(k);
                    path.insert(0, "  " + h.NR);
                    if (økning > k.restkapasitet())
                    {
                        økning = k.restkapasitet();
                    }
                    break;
                }
                k = k.NESTE;
            }
            h = h.FORGJENGER;
        }
        MAKS_FLYT += økning;

        while (!kø.isEmpty())
        {
            Kant k = (Kant)kø.get_Next_In_Line();
            k.add_flyt(økning);
        }

        if (økning >= 10)
        {
            System.out.println("  " + økning + "   :  " + kilde + path);
        }
        else
        {
            System.out.println("   " + økning + "   :  " + kilde + path);
        }
    }

    private void nullstill_forgjengere()
    {
        for (Hjørne hjørne : HJØRNER)
        {
            hjørne.FORGJENGER = null;
        }
    }

    private void print_resultat()
    {
        print("gray", "Maksimal flyt ble ");
        println("green", MAKS_FLYT + "");
        resett_alt();
    }

    private void resett_alt()
    {
        MAKS_FLYT = 0;
        for (Hjørne hjørne : HJØRNER)
        {
            hjørne.FORGJENGER = null;
            Kant k = hjørne.KANT;
            while (k != null)
            {
                k.FLYT = 0;
                k.MOTSATT.FLYT = 0;
                k = k.NESTE;
            }
        }
    }

    public String toString()
    {
        String kanter = "";
        for (Hjørne hjørne : HJØRNER)
        {
            Kant k = hjørne.KANT;
            while (k != null)
            {
                if (k.KAPASITET != 0)
                {
                    kanter += "\n" + k.FRA.NR + " -> " + k.TIL.NR + " : " + k.FLYT + "/" + k.KAPASITET;
                    kanter += "   -   " + k.MOTSATT.FRA.NR + " -> " + k.MOTSATT.TIL.NR + " : " + k.MOTSATT.FLYT + "/" + k.MOTSATT.KAPASITET;
                }
                k = k.NESTE;
            }
        }
        return string("blue", "\n\n" + filnavn + "\n") + "KILDE: " + KILDE + "  ->  SLUK: " + SLUK + "\n" + kanter + "\n";
    }
}
