package src;

import static src.Printer.blue;
import static src.Printer.green;
import static src.Printer.yellow;

public class Search
{
    // Node indices for scandinavia.txt, extracted from scandinavia-names.txt
    private static final int DRAMMEN = 65205;
    private static final int HELSINKI = 3378527;
    private static final int MOHOLT = 18058;
    private static final int KALVSKINNET = 37774;

    public static void main(String[] args)
    {
        System.out.println(blue("\nTask 1 - BFS"));
        System.out.println(yellow("graph-1 - All paths from 5"));
        UnweightedGraph graph1 = new UnweightedGraph("graph-1.txt");
        System.out.println(green(graph1.BFS(5)));

        System.out.println(yellow("\ngraph-2 - Path from 5 to 39"));
        UnweightedGraph graph2 = new UnweightedGraph("graph-2.txt");
        System.out.println(green(graph2.BFS(5, 39)));

        System.out.println(yellow("\ngraph-3 - Path from 5 to 2996"));
        UnweightedGraph graph3 = new UnweightedGraph("graph-3.txt");
        System.out.println(green(graph3.BFS(5, 2996)));

        UnweightedGraph scandinavia = new UnweightedGraph("scandinavia.txt");

        System.out.println(yellow("\nMoholt -> Kalvskinnet"));
        scandinavia.BFS(MOHOLT, KALVSKINNET);
        System.out.println("Length of shortest path: " + green(scandinavia.nodes[KALVSKINNET].distToStartNode));

        System.out.println(yellow("\nDrammen -> Helsinki"));
        scandinavia.BFS(DRAMMEN, HELSINKI);
        System.out.println("Length of shortest path: " + green(scandinavia.nodes[HELSINKI].distToStartNode));

        System.out.println(blue("\nTask 2 - Topological Sort using DFS"));
        System.out.println(yellow("graph-5"));
        UnweightedGraph graph5 = new UnweightedGraph("graph-5.txt");
        System.out.println(graph5);
        System.out.print("Topologically Sorted: ");
        for (Node07 n : graph5.sortTopologically())
        {
            System.out.print(green(n.index) + " ");
        }
        System.out.println("\n");
    }
}
