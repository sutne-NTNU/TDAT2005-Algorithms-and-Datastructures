package øving_4.oppgave_1;

public class Person
{
	final int start_posisjon;
	Person neste;



	Person(int start_posisjon)
	{
		this.start_posisjon = start_posisjon;
	}



	@Override
	public String toString( )
	{
		return "posisjon: " + start_posisjon + "   neste: " + neste.start_posisjon;
	}
}
