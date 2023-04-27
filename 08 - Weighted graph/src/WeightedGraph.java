package src;

import static src.Printer.black;
import static src.Printer.blue;
import static src.Printer.green;
import static src.Program.sleep;

import src.utils.WeightedLoader;

public class WeightedGraph
{
    private Node08[] nodes;

    private int DRAIN;
    private int SOURCE;

    WeightedGraph(String file)
    {
        WeightedLoader.FileContent content = WeightedLoader.load(file);
        this.nodes = new Node08[content.numNodes];
        for (int i = 0; i < this.nodes.length; i++)
        {
            this.nodes[i] = new Node08(i);
        }
        for (WeightedLoader.Edge edge : content.edges)
        {
            this.nodes[edge.from].addEdge(new Edge(edge.to, edge.weight));
            // also add reverse edge, to allow backtracking
            this.nodes[edge.to].addEdge(new Edge(edge.from, 0));
        }
        this.findSourceAndDrain();
    }

    private void findSourceAndDrain()
    {
        boolean[] isSource = new boolean[this.nodes.length];
        boolean[] isDrain = new boolean[this.nodes.length];
        for (int i = 0; i < this.nodes.length; i++)
        {
            isSource[i] = true;
            isDrain[i] = true;
        }
        for (Node08 node : this.nodes)
        {
            for (Edge edge : node.edges)
            {
                if (edge.capacity == 0) continue;
                isDrain[node.id] = false;  // has outgoing edge
                isSource[edge.to] = false; // has incoming edge
            }
        }
        for (int i = 0; i < this.nodes.length; i++)
        {
            if (isSource[i]) this.SOURCE = i;
            if (isDrain[i]) this.DRAIN = i;
        }
    }

    int edmondKarp()
    {
        return this.edmondKarp(this.SOURCE, this.DRAIN);
    }

    int edmondKarp(int source, int drain)
    {
        System.out.println(String.format("Using Edmond-Karp from %s to %s", blue(source), blue(drain)));
        int maxFlow = 0;
        while (true)
        {
            int increase = this.findFlowIncreasingPath(source, drain);
            if (increase == 0) break;
            maxFlow += increase;
            sleep(100);
        }
        return maxFlow;
    }

    /**
     * BFS
     */
    private int findFlowIncreasingPath(int source, int drain)
    {
        Queue<Node08> queue = new Queue<Node08>(this.nodes[source]);
        while (queue.first != null)
        {
            Node08 node = queue.getNext();
            for (Edge edge : node.edges)
            {
                if (edge.to == source) continue;
                if (edge.isFull()) continue;
                Node08 target = this.nodes[edge.to];
                if (target.parent != null) continue; // already checked

                target.parent = node;
                queue.add(target);
                if (edge.to == drain)
                {
                    return this.increaseFlow(source, drain);
                }
            }
        }
        return 0; // no path found
    }

    // Increase the flow as much as possible along the path
    // created by the parents of the nodes (from the drain)
    private int increaseFlow(int sourceId, int drainId)
    {
        // find maximum flow we can increase along this path (following parents)
        int maxFlowIncrease = Integer.MAX_VALUE;
        Node08 node = this.nodes[drainId];
        while (node.id != sourceId)
        {
            Edge edge = node.parent.getEdgeTo(node);
            maxFlowIncrease = Math.min(maxFlowIncrease, edge.getRemainingCapacity());
            node = node.parent;
        }

        // update the flow along the path
        StringBuilder path = new StringBuilder();
        node = this.nodes[drainId];
        while (node.id != sourceId)
        {
            path.insert(0, node.id + " ");
            Node08 parent = node.parent;
            // get correct edge
            Edge edge = parent.getEdgeTo(node);
            Edge reverse = node.getEdgeTo(parent);
            // change flow
            edge.addFlow(maxFlowIncrease);
            reverse.addFlow(-maxFlowIncrease);
            // move up path
            node = parent;
        }
        // Print all flow increasing paths
        String flowPath = String.format("%s: \t%s %s", green(maxFlowIncrease), black(sourceId), black(path.toString()));
        System.out.println(flowPath);
        // Reset all parents for next search
        for (Node08 n : this.nodes) n.parent = null;
        return maxFlowIncrease;
    }

    @Override
    public String toString()
    {
        String str = String.format("Source: %d  ->  Drain: %d\n", this.SOURCE, this.DRAIN);
        for (Node08 node : this.nodes) str += node + "\n";

        return str;
    }
}
