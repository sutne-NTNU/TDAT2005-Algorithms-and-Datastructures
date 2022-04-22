package øving_12;

import static hjelp.ColorPrint.println_Color;

import java.io.*;

public class Reader
{
	private DataInputStream reader;



	public Reader(String fil)
	{
		try
		{
			String path = "src/øving_12/";
			System.out.print("\nLeser fil:  \t" + fil + " - ");
			this.reader = new DataInputStream(new BufferedInputStream(new FileInputStream(path + fil)));
			println_Color("green" , "OK");
		}
		catch(FileNotFoundException fnfe)
		{
			println_Color("red" , "File not found\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void close( )
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



	public short readShort( )
	{
		short sh = 0;
		try
		{
			sh = reader.readShort();
		}
		catch(EOFException eofe)
		{
			//reached end of file
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sh;
	}



	public byte readByte( )
	{
		byte b = 0;
		try
		{
			b = reader.readByte();
		}
		catch(EOFException eofe)
		{
			//reached end of file
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
}
