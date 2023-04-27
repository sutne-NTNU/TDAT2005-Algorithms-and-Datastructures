package src.utils;

import static src.Printer.red;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class UnweightedLoader
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
        Edge(int from, int to)
        {
            this.from = from;
            this.to = to;
        }
    }

    private static final String DATA_PATH = "07 - Unweighted Graph/data/";

    // First line of file: "<numNodes> <numEdges>"
    // Remaining lines:    "<fromIndex> <toIndex>"

    public static FileContent load(String filename)
    {
        String path = UnweightedLoader.DATA_PATH + filename;
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
                edges.add(new Edge(from, to));
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
