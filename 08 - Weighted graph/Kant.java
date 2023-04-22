package øving_8;

class Kant
{
	final Hjørne FRA;
	final Hjørne TIL;
	final Kant MOTSATT;
	int KAPASITET;
	Kant NESTE;       //lenket liste med alle kanter fra samme node
	int FLYT = 0;



	Kant(Hjørne fra , Hjørne til , int kapasitet)
	{
		FRA = fra;
		TIL = til;
		KAPASITET = kapasitet;
		MOTSATT = new Kant(til , fra , this);
		TIL.ny_kant(MOTSATT);
	}



	private Kant(Hjørne fra , Hjørne til , Kant motsatt)  //Motsatt kant
	{
		FRA = fra;
		TIL = til;
		KAPASITET = 0;
		MOTSATT = motsatt;
	}



	void neste_kant(Kant k)
	{
		if(k.equals(this))
		{
			this.KAPASITET += k.KAPASITET;
			return;
		}
		if(NESTE == null)
		{
			NESTE = k;
			return;
		}
		NESTE.neste_kant(k);
	}



	void add_flyt(int flyt)
	{
		FLYT += flyt;
		MOTSATT.FLYT -= flyt;
	}



	public String toString( )
	{
		return "Kant: " + FRA.NR + " -> " + TIL.NR + " - Restk.: " + restkapasitet();
	}



	int restkapasitet( )
	{
		return KAPASITET - FLYT;
	}



	private boolean equals(Kant k)
	{
		return (TIL == k.TIL);
	}
}
