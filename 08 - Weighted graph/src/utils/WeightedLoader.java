package src.utils;

import static src.Printer.red;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class WeightedLoader
{
    public static class FileContent
    {
        public int numNodes;
        public int numEdges;
        public Edge[] edges;
        FileContent() { }
    }

    public static class Edge
    {
        public int from;
        public int to;
        public int weight;
        Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static final String DATA_PATH = "08 - Weighted Graph/data/";

    // First line of file: "<numNodes> <numEdges>"
    // Remaining lines:    "<fromIndex> <toIndex> <capacity"

    public static FileContent load(String filename)
    {
        String path = WeightedLoader.DATA_PATH + filename;
        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            FileContent content = new FileContent();

            String line = reader.readLine().trim();
            String[] info = line.split(" ");
            content.numNodes = Integer.parseInt(info[0]);
            content.numEdges = Integer.parseInt(info[1]);

            ArrayList<Edge> edges = new ArrayList<Edge>();
            while ((line = reader.readLine()) != null)
            {
                info = line.trim().split("\\s+");
                int from = Integer.parseInt(info[0]);
                int to = Integer.parseInt(info[1]);
                int capacity = Integer.parseInt(info[2]);
                edges.add(new Edge(from, to, capacity));
            }
            content.edges = edges.toArray(new Edge[edges.size()]);
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
}
