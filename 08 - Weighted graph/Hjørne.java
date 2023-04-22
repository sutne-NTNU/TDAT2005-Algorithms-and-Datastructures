package øving_8;

public class Hjørne
{
	final int NR;
	Kant KANT;  //lenket liste med alle kanter fra denne Noden

	//BFS
	Hjørne FORGJENGER = null;



	public Hjørne(int nr)
	{
		this.NR = nr;
	}



	void ny_kant(Kant k)
	{
		if(KANT == null)
		{
			KANT = k;
			return;
		}
		KANT.neste_kant(k);
	}



	public String toString( )
	{
		return "Hjørne-" + NR;
	}
}
