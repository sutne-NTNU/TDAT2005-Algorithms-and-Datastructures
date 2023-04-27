package src;

import static src.Printer.yellow;

import src.MapGraph.Priority;
import src.utils.ResultWriter;

public class Test
{
    private static final int Moholt = 5884922;
    private static final int Kalvskinnet = 1328152;

    public static void main(String[] args)
    {
        System.out.println(yellow("Loading Map..."));
        MapGraph graph = new MapGraph();

        ResultWriter.write(graph.dijkstra(Moholt, Kalvskinnet, Priority.TIME));
        ResultWriter.write(graph.astar(Kalvskinnet, Moholt, Priority.DISTANCE));
    }
}
