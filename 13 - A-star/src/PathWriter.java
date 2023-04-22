package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Objects;

public class PathWriter
{
	private BufferedWriter writer;



	PathWriter(String map , String method , Node from_node , Node to_node)
	{
		String path = "src/øving_13/paths/";
		String filename = path + map + "___";
		if(from_node.name != null)
		{
			filename += from_node.name + "-->";
		}
		else
		{
			filename += from_node.NR + "-->";
		}
		filename += Objects.requireNonNullElseGet(to_node.name , ( ) -> to_node.NR);
		filename += "___";

		try
		{
			this.writer = new BufferedWriter(new FileWriter(filename + method + ".txt"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void close( )
	{
		if(writer == null)
		{
			return;
		}
		try
		{
			writer.flush();
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	public void println(String str)
	{
		try
		{
			writer.write(str + "\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
