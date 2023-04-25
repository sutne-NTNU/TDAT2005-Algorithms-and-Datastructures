package src;

import java.util.ArrayList;
import src.utils.UnweightedLoader;

class UnweightedGraph
{
    Node07[] nodes;

    UnweightedGraph(String filename)
    {
        UnweightedLoader.FileContent content = UnweightedLoader.load(filename);
        this.nodes = new Node07[content.numNodes];
        for (int i = 0; i < this.nodes.length; i++)
        {
            this.nodes[i] = new Node07(i);
        }
        for (UnweightedLoader.Edge edge : content.edges)
        {
            this.nodes[edge.from].addNeighbor(edge.to);
        }
    }

    /**
     * Breadth First Search from 'start' to all other nodes in the graph
     */
    String BFS(int start)
    {
        return this.BFS(start, -1);
    }

    /**
     * Depth First Search from 'start' to 'end', can toggle printing of nodes on the path between them.
     * If 'end' is -1, the search will be performed on the entire graph.
     */
    String BFS(int start, int end)
    {
        this.reset();
        boolean foundEnd = false;

        this.nodes[start].distToStartNode = 0;
        this.nodes[start].isVisited = true;
        Queue<Node07> queue = new Queue<Node07>(this.nodes[start]);

        while (queue.first != null && !foundEnd)
        {
            Node07 node = queue.next();
            for (int neighbor : node.neighborIndices)
            {
                if (this.nodes[neighbor].isVisited) continue;

                this.nodes[neighbor].distToStartNode = node.distToStartNode + 1;
                this.nodes[neighbor].parentIndex = node.index;
                this.nodes[neighbor].isVisited = true;
                if (neighbor == end)
                {
                    foundEnd = true;
                    break;
                }
                queue.add(this.nodes[neighbor]);
            }
        }
        if (end == -1)
            return this.getAllPaths(start);
        else
            return this.getPath(start, end);
    }

    private String getPath(int start, int end)
    {
        StringBuilder path = new StringBuilder("" + end);
        Node07 node = this.nodes[end];
        while (node.index != start)
        {
            path.insert(0, node.parentIndex + " > ");
            node = this.nodes[node.parentIndex];
        }
        return path.toString();
    }

    private String getAllPaths(int start)
    {
        String str = "";
        for (Node07 n : this.nodes)
        {
            if (n.index == start) continue;
            str += this.getPath(start, n.index) + "\n";
        }
        return str;
    }

    /**
     * Sort the graph topologically using DFS
     */
    Node07[] sortTopologically()
    {
        this.reset();
        ArrayList<Node07> sorted = new ArrayList<Node07>();
        for (int i = 0; i < this.nodes.length; i++)
        {
            this.visit(i, sorted);
        }
        return sorted.toArray(new Node07[sorted.size()]);
    }

    private void visit(int index, ArrayList<Node07> sorted)
    {
        if (this.nodes[index].isVisited) return;
        this.nodes[index].isVisited = true;
        for (int neighbor : this.nodes[index].neighborIndices)
        {
            this.visit(neighbor, sorted);
        }
        sorted.add(0, this.nodes[index]); // add to the front of the list
    }

    void reset()
    {
        for (Node07 n : this.nodes)
        {
            n.distToStartNode = Node07.INFINITY;
            n.parentIndex = -1;
            n.isVisited = false;
        }
    }

    @Override
    public String toString()
    {
        String str = "";
        for (Node07 n : this.nodes)
        {
            if (n == null)
                str += "null\n";
            else
                str += n + "\n";
        }
        return str;
    }
}