package stocks;

import java.util.ArrayList;

public class Aksjer
{
	private static int besteKjøpedag;
	private static int besteSelgedag;
	private static int fortjeneste;
	private static double tid_10_000;



	public static Dag[] createTable(int n)
	{
		Dag[] dager = new Dag[n];
		dager[0] = new Dag(1 , 0);
		for(int i = 1 ; i < n ; i++)
		{
			dager[i] = new Dag(i + 1 , dager[i - 1].getVerdi());
		}
		return dager;
	}



	public static ArrayList <Dag> kjøpedager(Dag[] n)
	{       //n
		ArrayList <Dag> kjøp = new ArrayList <Dag>();
		Dag forrigeDag = n[0];
		for(Dag dag : n)
		{
			if(forrigeDag.getEndring() < 0 && dag.getEndring() >= 0)
			{
				kjøp.add(forrigeDag);
			}
			forrigeDag = dag;
		}
		return kjøp;
	}



	public static ArrayList <Dag> selgedager(Dag[] n)    //n
	{
		ArrayList <Dag> selg = new ArrayList <Dag>();
		Dag forrigeDag = n[0];
		for(Dag dag : n)
		{
			if(forrigeDag.getEndring() > 0 && dag.getEndring() <= 0)
			{
				selg.add(forrigeDag);
			}
			forrigeDag = dag;
		}
		selg.add(n[n.length - 1]);
		return selg;
	}



	public static void stonks(Dag[] n)     // n + n + n^2 = O = n^2        ,  beste utfall: n + n + 2     = n
	{
		fortjeneste = 0;
		int selgeverdi;
		for(Dag kjøpedag : kjøpedager(n))
		{
			for(Dag selgedag : selgedager(n))
			{
				if(kjøpedag.getDagNR() < selgedag.getDagNR())
				{
					selgeverdi = selgedag.getVerdi() - kjøpedag.getVerdi();
					if(selgeverdi > fortjeneste)
					{
						fortjeneste = selgeverdi;
						besteKjøpedag = kjøpedag.getDagNR();
						besteSelgedag = selgedag.getDagNR();
					}
				}
			}
		}
	}



	public static double profits(int antallDager)
	{
		Dag[] dager = createTable(antallDager);

		long start = System.currentTimeMillis();
		stonks(dager);
		long tid = System.currentTimeMillis() - start;

		double tidS = (double)tid / 1000;
		System.out.println("\nTid for " + antallDager + " dager: " + tidS + "s");
		if(antallDager == 10000)
		{
			tid_10_000 = tidS;
		}
		else
		{
			System.out.println(antallDager / 10000 + "x mer dager: " + (double)((int)(tidS * 10 / tid_10_000)) / 10 + "x så mye tid");
		}
		System.out.println("kjøp på dag: " + besteKjøpedag + " Selg på dag: " + besteSelgedag + " fortjeneste: " + fortjeneste + "%");
		return tidS;
	}



	public static double test( )
	{
		Dag[] dager = { new Dag(1 , -1 , -1) , new Dag(2 , 3 , 2) , new Dag(3 , -9 , -7) , new Dag(4 , 2 , -5) , new Dag(5 , 2 , -3) , new Dag(6 , -1 , -4) , new Dag(7 , 2 , -2) , new Dag(8 , -1 , -3) , new Dag(9 , -5 , -8) };
		long start = System.currentTimeMillis();
		stonks(dager);
		long tid = System.currentTimeMillis() - start;
		double tidS = (double)tid / 1000;
		System.out.println("TEST - kjøp på dag: " + besteKjøpedag + " Selg på dag: " + besteSelgedag + " fortjeneste: " + fortjeneste + "%");
		return tidS;
	}



	public static void main(String[] args)
	{

		test();

		profits(10000);
		profits(2000000);
	}
}
