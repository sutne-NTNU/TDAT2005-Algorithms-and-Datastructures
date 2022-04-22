package øving_13;

class Node
{
	final int NR;
	String name; //hvis stedet har blitt navngitt
	Road road;      //lenket liste med kanter fra denne noden

	// Both
	Node previous;
	int drivetime = -1; //tiden det tar å kjøre til denne noden fra startnoden
	int distance = -1; //hvor langt det er til denne noden fra startnoden

	//A*
	int distance_to_end = 0; //samlet verdi for avstanden fra noden til mål + avstanden å komme dit.

	//used for putting coordinates on the map
	private double lateitude;
	private double longitude;

	//used for calculation of distance
	private double latitude_rad;
	private double longitude_rad;
	private double cos_longitude;



	Node(int nr , double breddegrad_grd , double lengdegrad_grd)
	{
		this.NR = nr;
		this.lateitude = breddegrad_grd;
		this.longitude = lengdegrad_grd;

		//converting from degrees to radians for use later
		this.latitude_rad = breddegrad_grd * 0.01745329252;
		this.longitude_rad = lengdegrad_grd * 0.01745329252;

		//calculating cos_bredde for later use
		this.cos_longitude = Math.cos(this.latitude_rad);
	}



	void newRoad(Road newRoad)
	{
		if(road == null)
		{
			road = newRoad;
			return;
		}
		road.new_road(newRoad);
	}



	// A*
	int distance_to(Node n)
	{
		double sin_bredde = Math.sin((this.latitude_rad - n.latitude_rad) / 2.0);
		double sin_lengde = Math.sin((this.longitude_rad - n.longitude_rad) / 2.0);
		return (int)(35285538.46153846153846153846 * Math.asin(Math.sqrt(sin_bredde * sin_bredde + this.cos_longitude * n.cos_longitude * sin_lengde * sin_lengde)));
	}



	String position( )
	{
		return lateitude + ", " + longitude;
	}



	@Override
	public String toString( )
	{
		String str = NR + ": \t";
		if(name != null)
		{
			str += "(" + name + ") \t - ";
		}
		return str + (lateitude + ", " + longitude);
	}
}


