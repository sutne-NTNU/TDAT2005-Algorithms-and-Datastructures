package src;

import static src.Printer.blue;
import static src.Printer.yellow;

public class Flow
{
    public static void main(String[] args)
    {
        test("graph-1.txt");
        test("graph-2.txt");
        test("graph-3.txt");
    }

    private static void test(String filename)
    {
        System.out.println("\nLoading " + yellow(filename));
        WeightedGraph graph = new WeightedGraph(filename);
        System.out.println(blue(graph.edmondKarp() + " Maximum Flow\n"));
    }
}
