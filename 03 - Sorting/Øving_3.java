package øving_3;

public class Øving_3
{
	private static double tid_vanlig;



	private static boolean test_sortert(int[] sortert)
	{
		for(int i = 0 ; i < sortert.length - 1 ; i++)
		{
			if(sortert[i + 1] < sortert[i]) return false;
		}
		return true;
	}



	private static boolean test_sum(int[] original , int[] sortert)
	{
		if(original.length != sortert.length) return false;

		int sum_original = 0;
		int sum_sortert = 0;
		for(int i = 0 ; i < original.length ; i++)
		{
			sum_original += original[i];
			sum_sortert += sortert[i];
		}

		return sum_original == sum_sortert;
	}



	private static void printTabell(int[] original , int[] sortert)
	{
		System.out.println("\nsortert     usortert");
		for(int i = 0 ; i < original.length ; i++)
		{
			System.out.println("    " + original[i] + "        " + sortert[i]);
		}
	}



	public static void taTiden_vanlig(Tabell tabell)
	{
		double tid = 0;
		for(int i = 0 ; i < 9 ; i++)
		{
			long start = System.currentTimeMillis();
			tabell.sorter();
			long slutt = System.currentTimeMillis();
			tid += slutt - start;
			tabell.reset();
		}
		System.out.println("\nVANLIG QUICKSORT:");
		long start = System.currentTimeMillis();
		tabell.sorter();
		long slutt = System.currentTimeMillis();
		tid += slutt - start;
		System.out.println("sortert:   " + test_sortert(tabell.getSortert()));
		System.out.println("sjekksum:  " + test_sum(tabell.getSortert() , tabell.getUsortert()));
		double avg_tid = tid / 10;
		System.out.println("tid         " + avg_tid + "ms");
		tabell.reset();
		tid_vanlig = avg_tid;
	}



	public static void taTiden_medBubblesort(Tabell tabell , int grense)
	{
		double tid = 0;
		for(int i = 0 ; i < 9 ; i++)
		{
			long start = System.currentTimeMillis();
			tabell.sorter(grense);
			long slutt = System.currentTimeMillis();
			tid += slutt - start;
			tabell.reset();
		}
		System.out.println("\nQUICK MED BUBBLESORT - Grense: " + grense);
		long start = System.currentTimeMillis();
		tabell.sorter(grense);
		long slutt = System.currentTimeMillis();
		tid += slutt - start;
		System.out.println("sortert:   " + test_sortert(tabell.getSortert()));
		System.out.println("sjekksum:  " + test_sum(tabell.getSortert() , tabell.getUsortert()));
		double avg_tid = tid / 10;
		System.out.println("tid         " + avg_tid + "ms");
		//printTabell(tabell.getSortert(), tabell.getUsortert());
		tabell.reset();

		double relativ_differanse = 1 - avg_tid / tid_vanlig;
		int forkorting = (int)(10000 * relativ_differanse);
		double prosent_forbedring = (double)forkorting / 100;

		System.out.println("\nprosent forbedring: " + prosent_forbedring + "%");
	}



	public static void main(String[] args)
	{
		Tabell tabell = new Tabell(1000000);

		taTiden_vanlig(tabell);
		taTiden_medBubblesort(tabell , 4);
		taTiden_medBubblesort(tabell , 10);
		taTiden_medBubblesort(tabell , 15);
		taTiden_medBubblesort(tabell , 20);
		taTiden_medBubblesort(tabell , 25);
		taTiden_medBubblesort(tabell , 50);
		taTiden_medBubblesort(tabell , 100);
		taTiden_medBubblesort(tabell , 250);
		taTiden_medBubblesort(tabell , 500);
	}
}
