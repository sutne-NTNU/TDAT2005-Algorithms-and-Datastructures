package src.utils;

import static src.Printer.blue;
import static src.Printer.green;
import static src.Printer.yellow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import src.MapGraph;
import src.MapGraph.Priority;
import src.Node13;
import src.Road;

public class ResultWriter
{
    private static final String DATA_PATH = "13 - A-star/data/routes/";

    /**
     * Writes the result summary of number of nodes checked, time etc to console
     * and write coordinates of the path to a .csv file for viewing on a map.
     */
    public static void write(MapGraph.Result result)
    {
        Node13 origin = result.path[0];
        Node13 destination = result.path[result.path.length - 1];
        String originStr = origin.name == null ? "" + origin.index : origin.name;
        String destinationStr = destination.name == null ? "" + destination.index : destination.name;
        String method = result.method == src.MapGraph.Method.A_STAR ? "A*" : "Dijkstra";
        String priority = result.priority == Priority.TIME ? "Fastest" : "Shortest";

        String filename = String.format("%s > %s - %s.csv", originStr, destinationStr, priority);
        new java.io.File(DATA_PATH).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_PATH + filename)))
        {
            writer.write("Latidude, Longitude\n");
            System.out.println("\n" + green(method) + ": " + yellow(originStr) + " -> " + yellow(destinationStr));
            System.out.println("\tFinding:        " + green(priority + " path"));
            System.out.println("\tTime to find:   " + blue(result.time) + " ms");
            System.out.println("\tNodes checked:  " + blue(result.numNodesChecked));
            System.out.println("\tNodes on path:  " + blue(result.path.length));

            int totalTime = 0;
            int totalDistance = 0;
            for (int i = 0; i < result.path.length - 1; i++)
            {
                Node13 fromNode = result.path[i];
                Node13 toNode = result.path[i + 1];
                Road connectingRoad = fromNode.getRoadTo(toNode);
                totalTime += connectingRoad.time;
                totalDistance += connectingRoad.length;
                writer.write(fromNode.position.toString() + "\n");
            }
            writer.write(result.path[result.path.length - 1].position.toString() + "\n");

            System.out.println("\tPath time:      " + blue(new Time(totalTime).toString()));
            String distance = totalDistance < 10_000 ? blue(totalDistance) + " meters" : blue(totalDistance / 1000) + " km";
            System.out.println("\tPath Distance:  " + distance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
