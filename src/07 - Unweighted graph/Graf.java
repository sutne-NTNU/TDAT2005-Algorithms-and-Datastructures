package øving_7;

import hjelp.Kø;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static hjelp.ColorPrint.print_Color;

class Graf
{
	private Node[] GRAF;
	private StringBuilder topologisk_sortert;



	Graf(String filnavn)
	{
		BufferedReader fil = null;
		try
		{
			System.out.print("leser fil: " + filnavn + " - ");
			String path = "src/øving_7/filer/";
			fil = new BufferedReader(new FileReader(path + filnavn));

			opprett_GRAF(fil.readLine());

			String linje = fil.readLine();
			while(linje != null)
			{
				ny_Nabo(linje);
				linje = fil.readLine();
			}
			System.out.print("ant_hjørner: ");
			print_Color("green" , GRAF.length + "\n");
		}

		catch(FileNotFoundException fnfe)
		{
			print_Color("red" , "File not found\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fil != null)
			{
				try
				{
					fil.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}



	private void opprett_GRAF(String første_linje)
	{
		String[] info_split = første_linje.split(" ");
		int ant_hjørner = Integer.parseInt(info_split[0]);
		this.GRAF = new Node[ant_hjørner];
		for(int i = 0 ; i < GRAF.length ; i++)
		{
			GRAF[i] = new Node(i);
		}
	}



	private void ny_Nabo(String linje_fra_fil)
	{
		String linje_trim = linje_fra_fil.trim();
		String[] kant_split = linje_trim.split("\\s+");

		int fra = Integer.parseInt(kant_split[0]);
		int til = Integer.parseInt(kant_split[1]);

		GRAF[fra].ny_nabo(new Node(til));
	}



	void BFS(int node_start) //Bredde først søk
	{
		GRAF[node_start].dist_til_startnode = 0;                                        //start.dist = 0, alle andre .dist = uendelig
		Kø kø = new Kø(GRAF[node_start]);                                               //legg start inn i en kø

		while(kø.FIRST != null)                                                   //så lenge køen ikke er tom {
		{
			Node x = (Node)kø.get_Next_In_Line();                                             //  plukk ut en node x

			Node nabo = x.nabo;                                                         //  sjekk alle naboene til x
			while(nabo != null)
			{
				if(GRAF[nabo.indeks].dist_til_startnode == Node.UENDELIG)               //  hvis naboens avstand er uendelig {
				{
					GRAF[nabo.indeks].dist_til_startnode = x.dist_til_startnode + 1;    //      nabo.dist = x.dist + 1
					GRAF[nabo.indeks].indeks_forgjenger = x.indeks;                     //      nabo.forgjenger = x
					kø.add(GRAF[nabo.indeks]);                                          //      putt nabonoden i køen}}
				}
				nabo = nabo.nabo;
			}
		}
		print_BFS();
		reset_søk_verdier();
	}



	private void print_BFS( )
	{
		System.out.println("Node    Forgjenger   Dist");
		for(Node n : GRAF)
		{
			if(n == null)
			{
				System.out.println("");
			}
			else
			{
				System.out.println(n);
			}
		}
		System.out.print("\n");
	}



	private void reset_søk_verdier( )
	{
		for(Node n : GRAF)
		{
			if(n != null)
			{
				n.besøkt = false;
				n.indeks_forgjenger = -1;
				n.dist_til_startnode = Node.UENDELIG;
			}
		}
	}



	void BFS(int fra_node , int til_node , boolean print_kryss)
	{
		GRAF[fra_node].dist_til_startnode = 0;
		Kø kø = new Kø(GRAF[fra_node]);
		FERDIG:
		while(kø.FIRST != null)
		{
			Node x = (Node)kø.get_Next_In_Line();

			Node nabo = x.nabo;
			while(nabo != null)
			{
				if(GRAF[nabo.indeks].dist_til_startnode == Node.UENDELIG)
				{
					GRAF[nabo.indeks].dist_til_startnode = x.dist_til_startnode + 1;
					GRAF[nabo.indeks].indeks_forgjenger = x.indeks;
					kø.add(GRAF[nabo.indeks]);
				}
				if(nabo.indeks == til_node) //kommet frem dit vi skal
				{
					ferdig(fra_node , til_node , print_kryss);
					break FERDIG;
				}
				nabo = nabo.nabo;
			}
		}
		reset_søk_verdier();
	}



	private void ferdig(int start , int slutt , boolean print_kryss)
	{
		System.out.println("Antall Kanter mellom " + start + " og " + slutt + " = " + GRAF[slutt].dist_til_startnode);

		StringBuilder path = new StringBuilder("" + slutt);
		int ant_kryss = 0;
		String kryss = "kryss i: ";
		Node n = GRAF[slutt];
		while(n.indeks != start)
		{
			path.insert(0 , n.indeks_forgjenger + " -> ");
			if(GRAF[n.indeks].nabo.nabo != null && n.indeks != slutt){
				ant_kryss++;
				kryss += n.indeks + ", ";
			}
			n = GRAF[n.indeks_forgjenger];
		}
		if(GRAF.length < 3500)
		{
			System.out.print("Spor: " + path + "\n");
		}
		if(print_kryss)
		{
			System.out.println(kryss);
		}
		System.out.println("Antall kryss: " + ant_kryss);
	}



	private void DFS( ) // Dybde først søk
	{
		//		for(int i = GRAF.length - 1 ; i >= 0 ; i--)        // 0 6 3 2 5 4 1
		//		{
		//			visit(GRAF[i]);
		//		}

		for(Node n : GRAF)                                         // 6 0 3 2 4 5 1
		{
			visit(n);
		}
	}



	private void visit(Node n)
	{
		if(GRAF[n.indeks].besøkt) return;
		GRAF[n.indeks].besøkt = true;

		Node temp = n.nabo;
		while(temp != null)
		{
			visit(GRAF[temp.indeks]);
			temp = temp.nabo;
		}
		topologisk_sortert.insert(0 , n.indeks + " ");
	}



	void topolopogisk_sort( )
	{
		if(topologisk_sortert == null)
		{
			topologisk_sortert = new StringBuilder();
			DFS();
		}
		System.out.println("topologisk sortert: " + topologisk_sortert);
	}



	void printGraf( )
	{
		for(Node n : GRAF)
		{
			if(n != null)
			{
				System.out.println(print(n));
			}
		}
	}



	private String print(Node n)
	{
		if(n.nabo == null) return "" + n.indeks;
		return "" + n.indeks + " -> " + print(n.nabo);
	}
}