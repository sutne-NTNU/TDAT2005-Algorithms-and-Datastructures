package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import src.utils.MapLoader;

public class MapGraph
{
    public Node13[] nodes;
    public HashMap<String, Integer[]> nameIndices;

    public enum Method
    {
        A_STAR,
        DIJKSTRA
    }
    public enum Priority
    {
        TIME,    // fastest path
        DISTANCE // shortest path
    }

    public MapGraph()
    {
        MapLoader.MapContent map = MapLoader.load("map");

        this.nodes = new Node13[map.nodes.length];
        for (MapLoader.Node node : map.nodes)
        {
            this.nodes[node.index] = new Node13(node.index, node.latitude, node.longitude);
        }
        for (MapLoader.Edge edge : map.edges)
        {
            Road road = new Road(edge.toIndex, edge.timeS, edge.lengthM, edge.speedLimitKmH);
            this.nodes[edge.fromIndex].addRoad(road);
        }
        this.nameIndices = map.names;
        this.nameIndices.forEach((name, indices) -> {
            for (int index : indices)
            {
                this.nodes[index].name = name;
            }
        });
    }

    public Result astar(int from, int to, Priority target)
    {
        return this.findOptimalPath(from, to, Method.A_STAR, target);
    }

    public Result dijkstra(int from, int to, Priority target)
    {
        return this.findOptimalPath(from, to, Method.DIJKSTRA, target);
    }

    private Result findOptimalPath(int originIndex, int destinationIndex, Method method, Priority priority)
    {
        long startTime = System.currentTimeMillis();

        Node13 start = this.nodes[originIndex];
        start.timeFromOrigin = 0;

        PriorityQueue<Node13> queue = switch (priority)
        {
            case TIME -> new PriorityQueue<Node13>(new TimeComparator());
            case DISTANCE -> new PriorityQueue<Node13>(new DistanceComparator());
        };
        queue.add(start);

        int numNodesChecked = 0;
        Node13 visiting;
        while ((visiting = queue.poll()) != null)
        {
            if (visiting.index == destinationIndex) break; // found shortest path to destination
            numNodesChecked++;
            for (Road road : visiting.roads)
                {
                    Node13 neighbor = this.nodes[road.toIndex];
                    int newTimeFromOrigin = visiting.timeFromOrigin + road.time;
                    int newDistanceFromOrigin = visiting.distanceFromOrigin + road.length;
                    if (priority == Priority.TIME && neighbor.timeFromOrigin <= newTimeFromOrigin) continue;
                    if (priority == Priority.DISTANCE && neighbor.distanceFromOrigin <= newDistanceFromOrigin) continue;

                    queue.remove(neighbor);
                    if (method == Method.A_STAR)
                    {
                        // A Star will update the neighbors estimated distances, (if it is not already calculated)
                        // This distance is part of the priority queue comparator
                        neighbor.estimateTimeTo(this.nodes[destinationIndex]);
                        neighbor.estimateDistanceTo(this.nodes[destinationIndex]);
                    }

                    neighbor.parent = visiting;
                    neighbor.timeFromOrigin = newTimeFromOrigin;
                    neighbor.distanceFromOrigin = newDistanceFromOrigin;
                    queue.add(neighbor); // add back to queue
                }
        }
        if (visiting == null) return null; // no path found
        long time = System.currentTimeMillis() - startTime;
        Node13[] path = this.getPath(originIndex, destinationIndex);

        this.reset(); // reset all search values before next search
        return new Result(path, method, priority, time, numNodesChecked);
    }

    /**
     * @return the path from the start node to the end node in order.
     */
    private Node13[] getPath(int originIndex, int destinationIndex)
    {
        ArrayList<Node13> path = new ArrayList<Node13>();
        Node13 node = this.nodes[destinationIndex];
        while (node != null)
        {
            path.add(0, node);
            if (node.index == originIndex) break;
            node = node.parent;
        }
        return path.toArray(new Node13[path.size()]);
    }

    private void reset()
    {
        for (Node13 node : this.nodes) node.reset();
    }

    public class Result
    {
        public Node13[] path;
        public long time;
        public int numNodesChecked;
        public Method method;
        public Priority priority;
        public Result(Node13[] path, Method method, Priority priority, long time, int numNodesChecked)
        {
            this.path = path;
            this.method = method;
            this.priority = priority;
            this.time = time;
            this.numNodesChecked = numNodesChecked;
        }
    }
}
