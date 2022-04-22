package øving_5.oppgave_2;

import java.util.HashMap;

public class HashTabell_2
{
	private final int M;
	private int[] tabell;
	private int ant_kollisjoner = 0;



	private HashTabell_2(int m)
	{
		this.M = m;
		this.tabell = new int[m];
	}



	public static void main(String[] args)
	{
		final int M = 5000011;
		final double lastfaktor = 0.99;

		System.out.println("oppretter tabell...\n");
		int[] random_tabell = RandomTabell.randomTabell((int)(M * lastfaktor) , 50000000);
		HashTabell_2 tabell = new HashTabell_2(M);

		double min_tid = tabell.taTiden_MIN(random_tabell);
		System.out.println("min insert tid:    " + min_tid + " Sekunder");
		System.out.println("antall kollisjoner: " + tabell.ant_kollisjoner);
		System.out.println("gjennomsnittlig antall kollisjoner per insert: " + Math.round(((tabell.ant_kollisjoner * 1.0) / M) * 100.0) / 100.0);
		System.out.println("lastfaktor =    " + tabell.get_lastfaktor());

		double java_tid = tabell.taTiden_JAVA(random_tabell);
		System.out.println("\nJava insert tid:   " + java_tid + " Sekunder");
		double diff = java_tid / min_tid;
		System.out.println("min er " + Math.round(100.0 * diff) / 100.0 + " ganger raskere");

		//		int sjekk_indeks = 0;
		//		int element = tabell.tabell[sjekk_indeks];
		//		System.out.println("tabell[" + sjekk_indeks + "] = " + element + "      hash(" + element + ") = " + tabell.get_indeks(element));
		//		System.out.println("java.get(" + element + ") =    " + tabell.java_get(element));
		//
		//		tabell.print();
	}



	private int hash(int number)
	{
		int indeks = number % M;
		if(tabell[indeks] == 0)
		{
			return indeks;
		}
		int kollisjons_nr = 0;

		while(tabell[indeks] != 0)
		{
			ant_kollisjoner++;
			kollisjons_nr++;
			indeks = (indeks + number + kollisjons_nr) % M;
		}
		return indeks;
	}



	private void insert(int nr)
	{
		tabell[hash(nr)] = nr;
	}



	private double taTiden_MIN(int[] insert)
	{
		if(insert.length >= tabell.length)
		{
			System.err.println("Tabellen har ikke plass til så mange elementer");
			return 0.0;
		}
		long start = System.currentTimeMillis();
		for(int nr : insert)
		{
			insert(nr);
		}
		long slutt = System.currentTimeMillis();

		double tid = ((double)slutt - (double)start) / 1000;
		return Math.round(100.0 * tid) / 100.0;
	}



	private double taTiden_JAVA(int[] insert)
	{
		if(insert.length >= tabell.length)
		{
			System.err.println("Tabellen har ikke plass til så mange elementer");
			return 0.0;
		}
		HashMap <Integer, Integer> java = new HashMap <>();

		long start = System.currentTimeMillis();
		for(int nr : insert)
		{
			java.put(nr , nr);
		}
		long slutt = System.currentTimeMillis();

		double tid = ((double)slutt - (double)start) / 1000;
		return Math.round(100.0 * tid) / 100.0;
	}



	private double get_lastfaktor( )
	{
		int teller = 0;
		for(int i = 0 ; i < tabell.length ; i++)
		{
			if(tabell[i] != 0) teller++;
		}
		return Math.round((teller * 1.0 / tabell.length) * 100.0) / 100.0;
	}




	/*public int hash_2(int number , int forrige_indeks , int kollisjon_nr)
	{
		ant_kollisjoner++;

		int ny_indeks = (forrige_indeks + number + kollisjon_nr) % M;

		if(tabell[ny_indeks] == number)
		{
			return ny_indeks;
		}

		if(tabell[ny_indeks] != 0)
		{
			ny_indeks = hash_2(number , ny_indeks , kollisjon_nr + 1);
		}

		return ny_indeks;
	}




	public int get_indeks(int number)
	{
		return hash(number);
	}



	public int java_get(int number)
	{
		return java.get(number);
	}*/



	public void print( )
	{
		System.out.println("\n\nTabell:");
		for(int i = 0 ; i < tabell.length ; i++)
		{
			System.out.println(i + " - " + tabell[i]);
		}
	}
}
