

import static src.ColorPrint.print;
import static src.ColorPrint.println;
import static src.ColorPrint.string;

import java.util.Scanner;
import src.Graf;
import src.Node;

public class Main
{
    private static boolean loop = true;
    private static boolean norden_loaded, island_loaded = false;

    private static Scanner scanner = new Scanner(System.in);

    //	Tur	                    Fra node	Til node	    noder i vei     	Reisetid
    //	Reykjavík–Hafnarfjörður	  30236	     14416	            113         	0:11:27
    //	Hafnarfjörður–Reykjavík	  14416	     30236	            109 	        0:11:26
    //	Kårvåg–Gjemnes	          5709083	 5108028	        287	            0:40:41
    //	Gjemnes–Kårvåg	          5108028	 5709083	        287	            0:40:41

    public static void main(String[] args)
    {
        Graf island = null;
        Graf norden = null;
        System.out.print("write (" + string("red", "s") + ") to stop, or (" + string("red", "r") + ") to restart");

        while (loop)
        {
            try
            {
                System.out.println("\n\nChoose map: (" + string("yellow", "n") + ")norden or (" + string("yellow", "i") + ")island");
                String choice = scanner.nextLine();
                switch (choice)
                {
                    case "i":
                        if (!island_loaded)
                        {
                            island = new Graf("Island", "island/noder.txt", "island/kanter.txt", "island/interesse_pkt.txt");
                            island_loaded = true;
                        }
                        println("yellow", "\nIsland:");
                        checkGraf(island);
                        break;

                    case "n":
                        if (!norden_loaded)
                        {
                            norden = new Graf("Norden", "norden/noder.txt", "norden/kanter.txt", "norden/interesse_pkt.txt");
                            norden_loaded = true;
                        }
                        println("yellow", "\nNorden:");
                        checkGraf(norden);
                        break;

                    case "s":
                        loop = false;
                        break;
                    default:
                        System.err.println("Not valid input");
                }
            }
            catch (NumberFormatException e)
            {
                break;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        println("red", "Program was stopped");
    }

    private static void checkGraf(Graf graf)
    {
        int from, to;
        print("blue", "Fra: ");
        from = checkInput(graf, scanner.nextLine().trim());
        if (from == -1)
        {
            loop = false;
            return;
        }
        if (from == -2)
        {
            return;
        }

        print("blue", "Til: ");
        to = checkInput(graf, scanner.nextLine().trim());
        if (to == -1)
        {
            loop = false;
            return;
        }
        if (to == -2)
        {
            return;
        }
        graf.A_star_and_Dijkstra(from, to, false);
        graf.A_star_and_Dijkstra(from, to, true);
    }

    private static int checkInput(Graf map, String scannerinput)
    {
        int index;
        try
        {
            index = Integer.parseInt(scannerinput);
            if (index < 0 || index > (map.GRAF.length - 1))
            {
                System.out.print("\nnode does not exist \ntry another: ");
                return checkInput(map, scanner.nextLine().trim());
            }
            return index;
        }
        catch (Exception e)
        {
            if (scannerinput.equals("s"))
            {
                return -1;
            }
            if (scannerinput.equals("r"))
            {
                return -2;
            }
            Node[] matches = map.getNodes(scannerinput);
            if (matches.length == 0)
            {
                System.out.print("\ncould not find location,\ntry again: ");
                return checkInput(map, scanner.nextLine().trim());
            }
            if (matches.length == 1)
            {
                return matches[0].NR;
            }
            for (int i = 0; i < matches.length; i++)
            {
                System.out.println((i + 1) + " - " + matches[i]);
            }
            System.err.print("which one though?\n");
            return matches[checkInput(map, scanner.nextLine().trim()) - 1].NR;
        }
    }
}