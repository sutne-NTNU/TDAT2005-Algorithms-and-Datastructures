package src;

import static src.Printer.black;
import static src.Printer.gray;

import java.util.Comparator;

public class Node13
{
    public final int index;
    public final Position position;
    public String name;
    public Road[] roads = new Road[0];

    // Search variables
    Node13 parent;
    int timeFromOrigin = Integer.MAX_VALUE;
    int distanceFromOrigin = Integer.MAX_VALUE;
    int estimatedTimeToDestination = 0;
    int estimatedDistanceToDestination = 0;

    public Node13(int index, double latitudeDeg, double longitudeDeg)
    {
        this.index = index;
        this.position = new Position(latitudeDeg, longitudeDeg);
    }

    public void addRoad(Road newRoad)
    {
        Road[] newRoads = new Road[this.roads.length + 1];
        for (int i = 0; i < this.roads.length; i++)
        {
            newRoads[i] = this.roads[i];
        }
        newRoads[this.roads.length] = newRoad;
        this.roads = newRoads;
    }

    public Road getRoadTo(Node13 other)
    {
        for (Road road : this.roads)
        {
            if (road.toIndex == other.index) return road;
        }
        return null;
    }

    public void estimateTimeTo(Node13 destination)
    {
        if (this.estimatedTimeToDestination != 0) return; // already calculated
        this.estimatedTimeToDestination = this.position.timeTo(destination.position);
    }

    public void estimateDistanceTo(Node13 destination)
    {
        if (this.estimatedDistanceToDestination != 0) return; // already calculated
        this.estimatedDistanceToDestination = (int)this.position.distanceTo(destination.position);
    }

    /**
     * reset all search values to their default
     */
    public void reset()
    {
        this.parent = null;
        this.timeFromOrigin = Integer.MAX_VALUE;
        this.distanceFromOrigin = Integer.MAX_VALUE;
        this.estimatedTimeToDestination = 0;
        this.estimatedDistanceToDestination = 0;
    }

    @Override
    public String toString()
    {
        String name = this.name == null ? "" : this.name;
        return String.format("%s: %s %s", black(this.index), gray(name), black(this.position.toString()));
    }
}

class DistanceComparator implements Comparator<Node13>
{
    @Override
    public int compare(Node13 a, Node13 b)
    {
        int aDistance = (a.distanceFromOrigin + a.estimatedDistanceToDestination);
        int bDistance = (b.distanceFromOrigin + b.estimatedDistanceToDestination);
        return aDistance - bDistance;
    }
}
class TimeComparator implements Comparator<Node13>
{
    @Override
    public int compare(Node13 a, Node13 b)
    {
        int aTime = (a.timeFromOrigin + a.estimatedTimeToDestination);
        int bTime = (b.timeFromOrigin + b.estimatedTimeToDestination);
        return aTime - bTime;
    }
}