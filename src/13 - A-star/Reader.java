package øving_13;

import static hjelp.ColorPrint.print_Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader
{
	private BufferedReader reader;



	Reader(String filnavn)
	{
		System.out.print("reading from file: " + filnavn + " - ");
		try
		{
			String path = "src/øving_13/filer/";
			reader = new BufferedReader(new FileReader(path + filnavn));
		}
		catch(FileNotFoundException fnfe)
		{
			print_Color("red" , "File not found\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	void close( )
	{
		if(reader == null)
		{
			return;
		}
		try
		{
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	String[] readStringArray(int antall_felt)
	{
		String linje = readline();
		if(linje == null)
		{
			return null;
		}
		String[] felt = new String[antall_felt];
		int j = 0;
		int lengde = linje.length();
		for(int i = 0 ; i < antall_felt ; ++i)
		{
			//Hopp over innledende blanke, finn starten på ordet
			while(linje.charAt(j) <= ' ') ++j;
			int ordstart = j;

			//Finn slutten på ordet, hopp over ikke-blanke
			while(j < lengde && linje.charAt(j) > ' ') ++j;
			felt[i] = linje.substring(ordstart , j);
		}
		return felt;
	}



	private String readline( )
	{
		if(reader == null)
		{
			return null;
		}
		try
		{
			return reader.readLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}



	int readInt( )
	{
		if(reader == null)
		{
			return 0;
		}
		try
		{
			return Integer.parseInt(reader.readLine().trim());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
