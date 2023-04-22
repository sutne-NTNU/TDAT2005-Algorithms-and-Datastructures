package øving_4.oppgave_1;

public class SelvmordsSirkel
{
	private Person første;



	public SelvmordsSirkel(int ant_personer)
	{
		første = new Person(1);
		Person forrige = første;

		for(int i = 2 ; i <= ant_personer ; i++)
		{
			Person ny = new Person(i);
			forrige.neste = ny;
			ny.neste = første;
			forrige = ny;
		}
	}



	public static void main(String[] args)
	{
		final int ant_personer = 40;
		final int intervall = 3;

		SelvmordsSirkel eksempel = new SelvmordsSirkel(ant_personer);
		//eksempel.print(ant_personer);

		int beste_posisjon = eksempel.finn_beste_posisjon(intervall);
		System.out.println("\nJosephus bør stå i posisjon nr " + beste_posisjon);
	}



	public int finn_beste_posisjon(int intervall)
	{
		Person forrige = null;
		Person pers = første;
		int teller = 0;
		int loopCounter = 0;            // looper (antall * intervall) - intervall   ganger         (n * k) - k  = lineær

		while(pers.neste != pers)  //fortsetter helt til en person må drepe seg selv, denne personen er da den siste igjen
		{
			loopCounter++;
			teller++;
			if(teller == intervall)
			{
				//System.out.println(forrige.start_posisjon + " drepte " + pers.start_posisjon);
				forrige.neste = pers.neste;
				teller = 0;
			}
			forrige = pers;
			pers = pers.neste;
		}
		System.out.println("loopet " + loopCounter + " ganger");
		return pers.start_posisjon;
	}



	public void print(int grense)
	{
		Person pers = første;
		for(int i = grense ; i > 0 ; i--)
		{
			System.out.println(pers);
			pers = pers.neste;
		}
	}
}


