package øving_1;

public class Dag
{
	private int dagNR;
	private int endring;
	private int verdi;



	public Dag(int dagNR , int verdi)
	{
		this.dagNR = dagNR;
		this.endring = (int)(Math.random() * 20 - 10);
		this.verdi = verdi += endring;
	}



	public Dag(int dagNR , int endring , int verdi)
	{
		this.dagNR = dagNR;
		this.endring = endring;
		this.verdi = verdi;
	}



	public int getDagNR( )
	{
		return dagNR;
	}
	public double getEndring( )
	{
		return endring;
	}
	public int getVerdi( )
	{
		return verdi;
	}



	@Override
	public String toString( )
	{
		return "Dag: " + dagNR + ",     endring: " + endring + "    verdi: " + verdi;
	}
}
