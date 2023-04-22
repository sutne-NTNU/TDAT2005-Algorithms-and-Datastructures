package øving_12.Lempel_Ziv;

import øving_12.Reader;
import øving_12.Writer;

public class Utpakking_LZ
{
	private final int BUFFER_SIZE = 32768;

	StringBuffer buffer = new StringBuffer(BUFFER_SIZE);
	private Reader reader;
	private Writer writer;



	public Utpakking_LZ(String filnavn)
	{
		String path = "/filer/";
		this.reader = new Reader(path + "komprimert/" + filnavn);
		this.writer = new Writer(path + "utpakket/" + filnavn + ".txt");

		pakk_ut();

		reader.close();
		writer.close();
	}



	private void pakk_ut()
	{

		short b;
		int curIndex = 0; //current position in the uncompressed file
		int offset;

		while((b = reader.readShort()) != 0)
		{
			if(b > 0) //b is how many clean characters that follow
			{
				//				print_Color("yellow" , "[" + b + "]");
				for(int i = 0 ; i < b ; i++)
				{
					char c = (char)reader.readByte();
					writer.writeChar(c);
					buffer.append(c);
					//					System.out.print(c);
					curIndex++;
				}
				//offset in  reference will be the next short in file
				offset = reader.readShort();
			}
			else  //b is offset in a reference
			{
				offset = b;
			}

			int start = curIndex + offset; //point in buffer to start reading from
			int length = reader.readByte();
			//			print_Color("yellow" , "[" + offset + "," + length + "]");
			for(int indeks = start ; indeks < (start + length) ; indeks++)
			{
				char c = buffer.charAt(indeks);
				//				print_Color("blue" , "" + c);
				writer.writeChar(c);
				buffer.append(c);
				curIndex++;
			}
			trimBuffer();
		}
	}



	private void trimBuffer()
	{
		if(buffer.length() > BUFFER_SIZE)
		{
			buffer.delete(0 , buffer.length() - BUFFER_SIZE);
		}
	}



	public static void main(String[] args)
	{
		Utpakking_LZ problemer_lz = new Utpakking_LZ("problemer_LZ");
		Utpakking_LZ oppgavetekst_lz = new Utpakking_LZ("oppgavetekst_LZ");
		Utpakking_LZ forelesningen_lz = new Utpakking_LZ("forelesningen_LZ");
	}
}
