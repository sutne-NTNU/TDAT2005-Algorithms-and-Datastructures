package øving_13;

class Road
{
	int TO;
	int drivetime;       // 1/100 sekunder
	int length;         // meter

	Road next;



	Road(int to , int driveTime , int length , int fartsgrense)
	{
		this.TO = to;
		this.drivetime = driveTime;
		this.length = length;
	}



	void new_road(Road nyRoad)
	{
		if(next == null)
		{
			next = nyRoad;
			return;
		}
		next.new_road(nyRoad);
	}
}
