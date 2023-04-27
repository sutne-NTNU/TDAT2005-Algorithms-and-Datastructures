package src;

public class MyArray
{
    private final int[] original;
    private int[] sorted;

    public MyArray(int length)
    {
        this.original = new int[length];
        for (int i = 0; i < length; i++)
        {
            this.original[i] = (int)(1000 * Math.random());
        }
    }
    public int[] getOriginal() { return this.original; }
    public int[] getSorted() { return this.sorted; }

    public void sort()
    {
        this.sort(0);
    }

    public void sort(int bubbleSortLimit)
    {
        this.sorted = copy(this.original);
        if (bubbleSortLimit > 0)
        {
            this.myQuicksort(0, this.sorted.length - 1, bubbleSortLimit);
        }
        else
        {
            this.quicksort(0, this.sorted.length - 1);
        }
    }

    public void myQuicksort(int leftIndex, int rightIndex, int bubbleSortLimit)
    {
        int subsectionLength = rightIndex - leftIndex;
        if (subsectionLength <= bubbleSortLimit)
        {
            this.bubbleSort(leftIndex, rightIndex);
            return;
        }
        int pivotPoint = this.split(leftIndex, rightIndex);
        this.myQuicksort(leftIndex, pivotPoint - 1, bubbleSortLimit);
        this.myQuicksort(pivotPoint + 1, rightIndex, bubbleSortLimit);
    }

    public void bubbleSort(int leftIndex, int rightIndex)
    {
        for (int i = 0; i < rightIndex - leftIndex; i++)
        {
            for (int j = leftIndex; j + i < rightIndex; j++)
            {
                if (this.sorted[j] <= this.sorted[j + 1]) continue;
                this.swap(j, j + 1);
            }
        }
    }

    public void quicksort(int leftIndex, int rightIndex)
    {
        int subsectionLength = rightIndex - leftIndex;
        if (subsectionLength <= 2)
        {
            this.median3sort(leftIndex, rightIndex);
            return;
        }
        int delepos = this.split(leftIndex, rightIndex);
        this.quicksort(leftIndex, delepos - 1);
        this.quicksort(delepos + 1, rightIndex);
    }

    private void swap(int i, int j)
    {
        int temp = this.sorted[j];
        this.sorted[j] = this.sorted[i];
        this.sorted[i] = temp;
    }

    private int split(int left, int right)
    {
        int leftIndex, rightIndex;
        int median = this.median3sort(left, right);
        int dv = this.sorted[median];
        this.swap(median, right - 1);
        for (leftIndex = left, rightIndex = right - 1;;)
        {
            while (this.sorted[++leftIndex] < dv) { }
            while (this.sorted[--rightIndex] > dv) { }
            if (rightIndex <= leftIndex) break;
            this.swap(leftIndex, rightIndex);
        }
        this.swap(leftIndex, right - 1);
        return leftIndex;
    }

    private int median3sort(int leftIndex, int rightIndex)
    {
        int medianIndex = (leftIndex + rightIndex) / 2;
        if (this.sorted[medianIndex] < this.sorted[leftIndex]) this.swap(leftIndex, medianIndex);
        if (this.sorted[medianIndex] > this.sorted[rightIndex])
        {
            this.swap(medianIndex, rightIndex);
            if (this.sorted[leftIndex] > this.sorted[medianIndex]) this.swap(leftIndex, medianIndex);
        }
        return medianIndex;
    }

    public boolean isSorted()
    {
        for (int i = 0; i < this.sorted.length - 2; i++)
        {
            if (this.sorted[i] > this.sorted[i + 1]) return false;
        }
        return true;
    }

    public boolean hasSameSum()
    {
        if (this.original.length != this.sorted.length) return false;
        int sumOriginal = 0;
        int sumSorted = 0;
        for (int i = 0; i < this.original.length; i++)
        {
            sumOriginal += this.original[i];
            sumSorted += this.sorted[i];
        }
        return sumOriginal == sumSorted;
    }

    @Override
    public String toString()
    {
        String str = "";
        str += "Original: " + Printer.toString(this.original) + "\n";
        str += "Sorted:   " + Printer.toString(this.sorted);
        return str;
    }

    public static int[] copy(int[] original)
    {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++)
        {
            copy[i] = original[i];
        }
        return copy;
    }
}
