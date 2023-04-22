package øving_7;

class Node
{
	static final int UENDELIG = -1;

	//brukes under BFS
	int dist_til_startnode = UENDELIG;
	int indeks_forgjenger = -1;

	//brukes under DFS
	boolean besøkt = false;

	int indeks;
	Node nabo;



	Node(int indeks)
	{
		this.indeks = indeks;
		this.nabo = null;
	}



	void ny_nabo(Node n)
	{
		if(this.nabo == null)
		{
			this.nabo = n;
			return;
		}
		this.nabo.ny_nabo(n);
	}



	public String toString( )
	{
		if(dist_til_startnode == -1)
		{
			return indeks + "";
		}
		if(indeks_forgjenger == -1)
		{
			return indeks + "          " + "     " + "       " + dist_til_startnode;
		}
		return indeks + "            " + indeks_forgjenger + "         " + dist_til_startnode;
	}
}
