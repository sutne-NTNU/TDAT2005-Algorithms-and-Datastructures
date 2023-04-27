package src;

public class Node08
{
    final int id;
    Edge[] edges = new Edge[0];
    Node08 parent = null; // BFS

    public Node08(int nr)
    {
        this.id = nr;
    }

    void addEdge(Edge edge)
    {
        // see if we update an existing edge
        for (int i = 0; i < this.edges.length; i++)
        {
            if (this.edges[i].to == edge.to)
            {
                int newCapacity = this.edges[i].capacity + edge.capacity;
                this.edges[i] = new Edge(edge.to, newCapacity);
                return;
            }
        }
        // add new edge
        Edge[] newEdges = new Edge[this.edges.length + 1];
        for (int i = 0; i < this.edges.length; i++)
        {
            newEdges[i] = this.edges[i];
        }
        newEdges[this.edges.length] = edge;
        this.edges = newEdges;
    }

    Edge getEdgeTo(Node08 node) { return this.getEdgeTo(node.id); }
    Edge getEdgeTo(int id)
    {
        for (Edge edge : this.edges)
        {
            if (edge.to == id)
            {
                return edge;
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        String parent = this.parent == null ? "none" : String.valueOf(this.parent.id);
        String str = String.format("Node(id=%d,parent=%s)", this.id, parent);
        for (Edge edge : this.edges)
        {
            str += "\n\t" + edge;
        }
        return str;
    }
}
