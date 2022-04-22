package øving_5.oppgave_1;

public class HashTabell_1
{
	private final int M;
	private int kollisjoner;
	private Node[] tabell;



	private HashTabell_1(int m)
	{
		this.M = m;
		this.tabell = new Node[m];
		kollisjoner = 0;
	}



	public static void main(String[] args)
	{
		int m = 128;
		String[] navneliste = Navneliste.hent_navn();
		HashTabell_1 hashtabell = new HashTabell_1(m);

		for(String navn : navneliste)
		{
			hashtabell.insert(navn);
		}

		int ant_pers = hashtabell.getAntallElementer();
		hashtabell.printTabell();

		System.out.println("pos til Utne,Sivert:    " + hashtabell.get_pos("Utne,Sivert"));
		System.out.println("pos til Nordmann,Ola:   " + hashtabell.get_pos("Nordmann,Ola") + " (finnes ikke i tabellen)\n");

		System.out.println(hashtabell.kollisjoner + " kollisjoner med lastfaktor: " + (ant_pers * 1.0) / m + " (" + ant_pers + " personer og m = " + m + ")");
		System.out.println("Dette gir gjennomsnitt på " + (hashtabell.kollisjoner * 1.0) / ant_pers + " kolllisjoner pr. pers");
	}



	private int hash(String navn)
	{
		if(navn == null)
		{
			return -1;
		}

		int indeks = 0;
		for(int i = 0 ; i < navn.length() ; i++)
		{
			if(navn.charAt(i) != ',')
			{
				indeks += (i + 1) * navn.charAt(i);
			}
		}
		return indeks % M;
	}



	private void insert(String navn)
	{
		if(navn == null) //navn er tomt, eller allerede registrert
		{
			return;
		}

		int indeks = hash(navn);

		if(tabell[indeks] == null)  //plassen er ledig
		{
			tabell[indeks] = new Node(navn);
			return;
		}
		kollisjon(indeks , tabell[indeks] , 1 , navn);
	}



	private void kollisjon(int indeks , Node n , int kollisjon_nr , String navn)
	{
		kollisjoner++;
		System.err.println("[" + indeks + "]    " + navn + "     kolliderte med      " + n.navn);

		if(n.neste == null)
		{
			n.neste = new Node(navn);
			return;
		}
		kollisjon(indeks , n.neste , kollisjon_nr + 1 , navn);
	}



	private int get_pos(String navn)
	{
		int indeks = hash(navn);
		if(tabell[indeks] == null)
		{
			return -1;
		}
		if(tabell[indeks].navn.equals(navn))
		{
			return indeks;
		}
		if(navn_i_node(tabell[indeks] , navn))
		{
			return indeks;
		}
		return -1;
	}



	private boolean navn_i_node(Node node , String navn)
	{
		System.err.println(navn + "     kolliderte i søk med      " + node.navn);
		if(node.navn.equals(navn))
		{
			return true;
		}
		if(node.neste == null)
		{
			return false;
		}
		return navn_i_node(node.neste , navn);
	}



	private int getAntallElementer( )
	{
		int antall = 0;
		for(Node n : tabell)
		{
			if(n != null)
			{
				antall++;

				Node temp = n;
				while(temp.neste != null)
				{
					antall++;
					temp = temp.neste;
				}
			}
		}
		return antall;
	}



	private void printTabell( )
	{
		System.out.println("\n\n\nTabell:");
		for(int i = 0 ; i < tabell.length ; i++)
		{
			System.out.println(i + " - " + printNode(tabell[i]));
		}
		System.out.println("\n");
	}



	private String printNode(Node n)
	{
		if(n == null)
		{
			return "[]";
		}

		String print = n.navn;

		if(n.neste != null)
		{
			print += "  ->  " + printNode(n.neste);
		}
		return print;
	}
}
