
public class Tabell
{
    private int[] tabell;
    private int[] usortert;

    public Tabell(int lengde)
    {
        int[] tabell = new int[lengde];
        for (int i = 0; i < lengde; i++)
        {
            tabell[i] = (int)(1000 * Math.random());
        }
        this.tabell = kopier(tabell);
        this.usortert = kopier(tabell);
    }

    public int[] kopier(int[] original)
    {
        int[] kopi = new int[original.length];
        for (int i = 0; i < original.length; i++)
        {
            kopi[i] = original[i];
        }
        return kopi;
    }

    public int[] getSortert()
    {
        return tabell;
    }

    public int[] getUsortert()
    {
        return usortert;
    }

    public void reset()
    {
        this.tabell = kopier(usortert);
    }

    public void sorter()
    {
        quicksort(0, tabell.length - 1);
        // quicksort(0, tabell.length-1);
    }

    public void sorter(int bubble_grense)
    {
        quicksort_medBubblesort(0, tabell.length - 1, bubble_grense);
        // quicksort_medBubblesort(0, tabell.length-1, bubble_grense);
    }

    public void quicksort(int v, int h)
    {
        if (h - v > 2)
        {
            int delepos = splitt(v, h);
            quicksort(v, delepos - 1);
            quicksort(delepos + 1, h);
        }
        else
        {
            median3sort(v, h);
        }
    }

    private void bytt(int i, int j)
    {
        int k = this.tabell[j];
        this.tabell[j] = this.tabell[i];
        this.tabell[i] = k;
    }

    private int splitt(int v, int h)
    {
        int iv, ih;
        int m = median3sort(v, h);
        int dv = tabell[m];
        bytt(m, h - 1);
        for (iv = v, ih = h - 1;;)
        {
            while (tabell[++iv] < dv)
            {
                ;
            }
            while (tabell[--ih] > dv)
            {
                ;
            }
            if (iv >= ih) break;
            bytt(iv, ih);
        }
        bytt(iv, h - 1);
        return iv;
    }

    private int median3sort(int v, int h)
    {
        int m = (v + h) / 2;
        if (tabell[v] > tabell[m]) bytt(v, m);
        if (tabell[m] > tabell[h])
        {
            bytt(m, h);
            if (tabell[v] > tabell[m]) bytt(v, m);
        }
        return m;
    }

    public void quicksort_medBubblesort(int v, int h, int grense)
    {
        if (h - v > grense)
        {
            int delepos = splitt(v, h);
            quicksort_medBubblesort(v, delepos - 1, grense);
            quicksort_medBubblesort(delepos + 1, h, grense);
        }
        else
        {
            bubbleSort(v, h);
        }
    }

    public void bubbleSort(int v, int h)
    {
        for (int i = 0; i < h - v; i++)
        {
            for (int j = v; j + i < h; j++)
            {
                if (tabell[j] > tabell[j + 1])
                {
                    bytt(j, j + 1);
                }
            }
        }
    }
}
