package src;

class Edge
{
    final int to;
    final int capacity;
    int flow = 0;

    Edge(int to, int capacity)
    {
        this.to = to;
        this.capacity = capacity;
    }

    void addFlow(int flow)
    {
        this.flow += flow;
        if (this.flow > this.capacity)
        {
            throw new RuntimeException("Flow exceeds capacity");
        }
    }

    boolean isResidual()
    {
        return this.capacity == 0;
    }

    boolean isFull()
    {
        return this.flow == this.capacity;
    }

    int getRemainingCapacity()
    {
        return this.capacity - this.flow;
    }

    @Override
    public String toString()
    {
        return String.format(" > %d (%d/%d)", this.to, this.flow, this.capacity);
    }
}
