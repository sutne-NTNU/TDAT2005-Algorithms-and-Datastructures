package hjelp;

import java.io.BufferedReader;

public class Program
{
	public static void vent(int millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public static void close(Object obj)
	{
		if(obj == null)
		{
			return;
		}
		try
		{
			if(obj instanceof BufferedReader)
			{
				((BufferedReader)obj).close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

