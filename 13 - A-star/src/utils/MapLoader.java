

package src.utils;

import static src.Printer.red;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MapLoader
{
    public static class MapContent
    {
        public Node[] nodes;
        public Edge[] edges;
        public HashMap<String, Integer[]> names; // name -> node indices with this name
    }

    public static class Node
    {
        public int index;
        public double latitude;
        public double longitude;
        public Node(int id, double lat, double lon)
        {
            this.index = id;
            this.latitude = lat;
            this.longitude = lon;
        }
    }

    public static class Edge
    {
        public int fromIndex;
        public int toIndex;
        public int timeS;
        public int lengthM;
        public int speedLimitKmH;
        Edge(int from, int to, int timeS, int lengthM, int speedLimitKmH)
        {
            this.fromIndex = from;
            this.toIndex = to;
            this.timeS = timeS;
            this.lengthM = lengthM;
            this.speedLimitKmH = speedLimitKmH;
        }
    }

    private static final String DATA_PATH = "13 - A-star/data/";

    public static MapContent load(String dirName)
    {
        String path = DATA_PATH + dirName;
        try (
            BufferedReader nodeReader = new BufferedReader(new FileReader(path + "/nodes.txt"));
            BufferedReader edgeReader = new BufferedReader(new FileReader(path + "/edges.txt"));
            BufferedReader nameReader = new BufferedReader(new FileReader(path + "/names.txt"));)
        {
            MapContent content = new MapContent();
            content.nodes = loadNodes(nodeReader);
            content.edges = loadEdges(edgeReader);
            content.names = loadNames(nameReader);
            return content;
        }
        catch (FileNotFoundException e)
        {
            String searchPath = System.getProperty("user.dir") + "/" + path;
            System.out.println(red("File Not Found: " + searchPath));
            System.exit(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // Node File:
    // First line of file: "       <numNodes>                "
    // Remaining lines:    "<nodeIndex> <latitude> <longitude"
    private static Node[] loadNodes(BufferedReader reader) throws IOException
    {
        String line = reader.readLine();
        int numNodes = Integer.parseInt(line.trim());
        Node[] nodes = new Node[numNodes];
        int i = 0;
        while ((line = reader.readLine()) != null)
        {
            fastSplit(line, 3);
            nodes[i] = new Node(
                Integer.parseInt(fields[0]),
                Double.parseDouble(fields[1]),
                Double.parseDouble(fields[2]));
            i++;
        }
        return nodes;
    }

    // Edge File:
    // first line:      "           <numEdges>                                  "
    // remaining lines: "<fromIndex> <toIndex> <timeS> <lengthM> <speedLimitKmH>"
    private static Edge[] loadEdges(BufferedReader reader) throws IOException
    {
        String line = reader.readLine();
        int numEdges = Integer.parseInt(line.trim());
        Edge[] edges = new Edge[numEdges];
        int i = 0;
        while ((line = reader.readLine()) != null)
        {
            fastSplit(line, 5);
            edges[i] = new Edge(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[1]),
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3]),
                Integer.parseInt(fields[4]));
            i++;
        }
        return edges;
    }

    // Name File:
    // first line:      "       <numNames>       "
    // remaining lines: "<nodeIndex> <?> "<name>""
    private static HashMap<String, Integer[]> loadNames(BufferedReader reader) throws IOException
    {
        String line = reader.readLine(); // clear first line
        HashMap<String, Integer[]> names = new HashMap<String, Integer[]>();
        while ((line = reader.readLine()) != null)
        {
            fastSplit(line, 3);
            int nodeIndex = Integer.parseInt(fields[0]);
            String name = fields[2].substring(1, fields[2].length() - 1); // remove quotes

            if (names.containsKey(name))
            {
                // extend index array
                Integer[] indices = names.get(name);
                Integer[] newIndices = new Integer[indices.length + 1];
                for (int i = 0; i < indices.length; i++)
                {
                    newIndices[i] = indices[i];
                }
                newIndices[indices.length] = nodeIndex;
                names.put(name, newIndices);
            }
            else
            {
                // create new index array
                Integer[] indices = new Integer[] { nodeIndex };
                names.put(name, indices);
            }
        }
        return names;
    }

    // Taken from assignment description.
    // Faster than String.trim().split(" ").
    // fields are loaded into global 'fields'
    // String[] to prevent unnecessary allocations.
    static String[] fields = new String[5];
    private static void fastSplit(String line, int numFields)
    {
        int i = 0;
        for (int field = 0; field < numFields; field++)
        {
            while (line.charAt(i) <= ' ') i++;
            int start = i;
            while (i < line.length() && line.charAt(i) > ' ') i++;
            fields[field] = line.substring(start, i);
        }
    }
}