package src;

import static src.ColorPrint.print;
import static src.ColorPrint.println;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Graf
{
    public Node[] GRAF;
    private String name;

    public Graf(String name, String filnavn_noder, String filnavn_kanter, String filnavn_interesse_pkt)
    {
        this.name = name;
        Reader fil = new Reader(filnavn_noder);

        int ant_noder = fil.readInt();
        this.GRAF = new Node[ant_noder];
        print("blue", GRAF.length + " noder\n");

        String[] node_info;
        while ((node_info = fil.readStringArray(3)) != null)
        {
            int indeks_NR = Integer.parseInt(node_info[0]);
            double breddegrad = Double.parseDouble(node_info[1]);
            double lengdegrad = Double.parseDouble(node_info[2]);
            GRAF[indeks_NR] = new Node(indeks_NR, breddegrad, lengdegrad);
        }
        fil.close();
        legg_til_kanter(filnavn_kanter);
        legg_til_stedsnavn(filnavn_interesse_pkt);
    }

    private void legg_til_kanter(String filnavn_kanter)
    {
        Reader fil = new Reader(filnavn_kanter);

        int ant_kanter = fil.readInt();
        print("blue", ant_kanter + " kanter\n");

        String[] node_info;
        while ((node_info = fil.readStringArray(5)) != null)
        {
            int fra_node = Integer.parseInt(node_info[0]);
            int til_node = Integer.parseInt(node_info[1]);
            int kjøretid = Integer.parseInt(node_info[2]);
            int lengde = Integer.parseInt(node_info[3]);
            int fartsgrense = Integer.parseInt(node_info[4]);
            this.GRAF[fra_node].newRoad(new Road(til_node, kjøretid, lengde, fartsgrense));
        }
        fil.close();
    }

    private void legg_til_stedsnavn(String filnavn_interesse_pkt)
    {
        Reader fil = new Reader(filnavn_interesse_pkt);

        int ant_navn = fil.readInt();
        print("blue", ant_navn + " navn\n");

        String[] nr_navn;
        while ((nr_navn = fil.readStringArray(3)) != null)
        {
            int nr = Integer.parseInt(nr_navn[0]);
            this.GRAF[nr].name = nr_navn[2].replace("\"", "");
        }
        fil.close();
    }

    public Node[] getNodes(String navn)
    {
        Node[] matches = new Node[0];
        int counter = 0;
        for (Node n : GRAF)
        {
            if (n.name != null && n.name.equalsIgnoreCase(navn))
            {
                Node[] temp = new Node[matches.length + 1];
                for (int i = 0; i < matches.length; i++)
                {
                    temp[i] = matches[i];
                }
                matches = temp;
                matches[counter] = n;
                counter++;
            }
        }
        return matches;
    }

    public void A_star_and_Dijkstra(int node_fra, int node_til, boolean a_star)
    {
        long start_tid = System.currentTimeMillis();

        int nodes_checked = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(new comparator_Astar_and_Dijkstra());

        Node start = GRAF[node_fra];
        start.drivetime = 0;
        queue.add(start);

        Node visiting;
        // hent node ut fra køen helt til noden vi henter ut er målnoden
        while ((visiting = queue.poll()) != null && visiting.NR != node_til)
        {
            nodes_checked++;

            Road road = visiting.road;
            // legg til naboer i køen
            while (road != null)
            {
                Node neighbour = GRAF[road.TO];
                if (neighbour.drivetime == -1 || (neighbour.drivetime) > (visiting.drivetime + road.drivetime))
                {
                    // hvis naboen allerede er i køen tar vi den ut og oppdatererer den
                    queue.remove(neighbour);
                    if (a_star && neighbour.distance_to_end == 0)
                    {
                        neighbour.distance_to_end = neighbour.distance_to(GRAF[node_til]);
                    }
                    neighbour.previous = visiting;
                    neighbour.drivetime = (visiting.drivetime + road.drivetime);
                    neighbour.distance = (visiting.distance + road.length);
                    queue.add(neighbour);
                }
                road = road.next;
            }
        }
        Node end = visiting;
        if (end == null)
        {
            System.err.println("Oppsiedoopsie");
            resetValues();
            return;
        }
        long slutt_tid = System.currentTimeMillis();
        int calculation_time = (int)(slutt_tid - start_tid);

        String method = (a_star ? "A*" : "Dijkstra");
        writeResults(method, start, end, calculation_time, new Tid(visiting.drivetime), visiting.distance, nodes_checked);
        resetValues();
    }

    private void resetValues()
    {
        for (Node n : GRAF)
        {
            n.drivetime = -1;
            n.distance = -1;
            n.previous = null;
        }
    }

    // writes info to the console, and that same info + a bunch more to a file
    private void writeResults(String method, Node fra_node, Node til_node, int tid_algoritme, Tid tid, int distance_meter, int nodes_checked)
    {
        PathWriter writer = new PathWriter(name, method, fra_node, til_node);
        double distance_km = 1.0 * distance_meter / 1000;

        writer.println("Map:        \t " + name);
        writer.println("Method:     \t " + method);
        System.out.print("\n" + method + ":");

        writer.println("Time (calculation):\t" + tid_algoritme + "ms\n");
        System.out.print("\n\tCalculation Time: ");
        print("green", (1.0 * tid_algoritme) / 1000 + "s");

        writer.println("From:               \t" + fra_node);
        writer.println("To:                 \t" + til_node + "\n");

        writer.println("Traveltime:         \t" + tid);
        System.out.print("\n\tTraveltime: ");
        print("green", "" + tid);

        writer.println("Distance:           \t" + distance_km + "km");
        System.out.print("\n\tLength of path: ");
        print("green", distance_km + "km");

        writer.println("Nodes checked:      \t" + nodes_checked + "\n");
        System.out.print("\n\tNodes checked: ");
        println("green", nodes_checked);

        int ant_noder_på_vei = 0;
        Node n = GRAF[til_node.NR];
        StringBuilder shortest_path = new StringBuilder();
        do
        {
            ant_noder_på_vei++;
            shortest_path.append(n.position()).append("\n");
            if (n.NR == fra_node.NR)
            {
                break;
            }
        } while ((n = n.previous) != null);

        writer.println("\n\nNodes on path:      \t" + ant_noder_på_vei + "\n");
        System.out.print("\tNodes on path: ");
        println("green", ant_noder_på_vei);

        writer.println("\n\nShortest path:");
        writer.println(shortest_path.toString());
        writer.close();
    }
}

class comparator_Astar_and_Dijkstra implements Comparator<Node>
{
    @Override
    public int compare(Node a, Node b)
    {
        return (a.distance_to_end + a.drivetime) - (b.distance_to_end + b.drivetime);
    }
}

class Tid
{
    private int timer;
    private int minutter;
    private int sekunder;

    Tid(int hundredeler)
    {
        double sekunder = 1.0 * hundredeler / 100;
        double minutter = sekunder / 60;
        double timer = minutter / 60;

        minutter = (timer % 1) * 60;
        sekunder = (minutter % 1) * 60;

        this.timer = (int)timer;
        this.minutter = (int)minutter;
        this.sekunder = (int)Math.round(sekunder);
    }

    public String toString()
    {
        return timer + "t " + minutter + "m " + sekunder + "s";
    }
}
