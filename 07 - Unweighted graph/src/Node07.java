package src;

class Node07 // 07 to specify that this is the Node class for the 07 - Unweighted Graph Assignment
{
    static final int INFINITY = -1;

    int distToStartNode = INFINITY;
    boolean isVisited = false;
    int parentIndex = -1;

    int index;
    int[] neighborIndices;

    public Node07(int index)
    {
        this.index = index;
        this.neighborIndices = new int[0];
    }

    void addNeighbor(int neighbor)
    {
        int[] newNeighborIndices = new int[this.neighborIndices.length + 1];
        for (int i = 0; i < this.neighborIndices.length; i++)
        {
            newNeighborIndices[i] = this.neighborIndices[i];
        }
        newNeighborIndices[this.neighborIndices.length] = neighbor;
        this.neighborIndices = newNeighborIndices;
    }

    @Override
    public String toString()
    {
        String str = "Node(index=" + this.index
                   //    + ",isVisited=" + this.isVisited
                   //    + ",parentIndex=" + this.parentIndex
                   //    + ",distToStartNode=" + this.distToStartNode
                   + ",neighbors=" + Printer.toString(this.neighborIndices)
                   + ")";
        return str;
    }
}
