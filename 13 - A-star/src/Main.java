package src;

import static src.Printer.green;
import static src.Printer.yellow;

import src.MapGraph.Priority;
import src.utils.ResultWriter;
import src.utils.Scanner;

public class Main
{
    static MapGraph graph;

    public static void main(String[] args)
    {
        System.out.println("\n" + yellow("Loading map..."));
        graph = new MapGraph();
        while (true)
        {
            int from;
            while ((from = getNodeIndex(green("\nFrom: "))) == -1) { };
            int to;
            while ((to = getNodeIndex(green("To: "))) == -1) { };
            Priority priority = promptPriority();

            ResultWriter.write(graph.dijkstra(from, to, priority));
            ResultWriter.write(graph.astar(from, to, priority));
        }
    }

    private static int getNodeIndex(String desc)
    {
        String name = Scanner.getString(desc);
        try
        {
            int index = Integer.parseInt(name);
            if (index < 0 || index >= graph.nodes.length)
            {
                System.out.println("Node index out of bounds: " + index);
                return -1;
            }
            return index;
        }
        catch (NumberFormatException e)
        {
            // Ignore, user wrote name of node, not index
        }
        Integer[] fromIndices = graph.nameIndices.get(name);
        if (fromIndices == null)
        {
            System.out.println("Could not find node with name: " + name);
            return -1;
        }
        else if (fromIndices.length == 1)
        {
            return fromIndices[0];
        }
        else
        {
            System.out.println("Found multiple nodes with name " + name);
            for (int i = 0; i < fromIndices.length; i++)
            {
                System.out.println("   " + (i + 1) + " - " + graph.nodes[fromIndices[i]].toString());
            }
            System.out.println("Which one did you mean?");
            int input = Scanner.getInt("Node NR: ", 1, fromIndices.length);
            return fromIndices[input - 1];
        }
    }

    private static Priority promptPriority()
    {
        char response = Scanner.getChar(new char[] { 's', 'f' },
                                        "\nChoose Search Type: "
                                            + "\n   Shortest Path (" + yellow("s") + ")"
                                            + "\n   Fastest Path  (" + yellow("f") + ")"
                                            + "\nChoice: ");
        return switch (response)
        {
                case 's' -> Priority.DISTANCE;
                case 'f' -> Priority.TIME;
                default -> throw new IllegalStateException("Unexpected value: " + response);
            };
            }
        }
