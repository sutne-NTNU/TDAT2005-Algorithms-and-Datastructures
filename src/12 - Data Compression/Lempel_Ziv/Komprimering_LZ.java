package øving_12.Lempel_Ziv;

import øving_12.Reader;
import øving_12.Writer;

public class Komprimering_LZ
{
	private static final int BUFFER_SIZE = 32768;
	private static final int SIZE_OF_REFERENCE = 3;

	private StringBuffer searchBuffer = new StringBuffer(BUFFER_SIZE);   //buffer we look for matches in
	private Reader reader;
	private Writer writer;



	public Komprimering_LZ(String filnavn)
	{
		String path = "/filer/";
		this.reader = new Reader(path + "original/" + filnavn + ".txt");
		this.writer = new Writer(path + "komprimert/" + filnavn + "_LZ");

		komprimer();

		reader.close();
		writer.close();
	}



	private void komprimer( )
	{

		char nextChar;                          //next charcater from file
		int matchIndex = 0, tempMatchIndex;     //keeps track of where a match is in the new file
		int currentIndex = -1;                  //index of the character in file we are curently looking at
		String currentMatch = "";               //characters matching something in the buffer
		String not_matched = "";                //keeps track of characters that doesnt match and matches that arent long enough

		while((nextChar = (char)reader.readByte()) != 0)
		{
			currentIndex++;
			tempMatchIndex = searchBuffer.indexOf(currentMatch + nextChar); //look in our search buffer for a match

			searchBuffer.append(nextChar); //add the character to the search buffer

			if(tempMatchIndex != -1 && currentMatch.length() < 127) // if match is found, and currentmatch is not longer than a byte can reference
			{
				currentMatch += nextChar;       // add nextChar to currentMatch
				matchIndex = tempMatchIndex;    // update index of match
			}
			else //match is not found
			{
				if(currentMatch.length() == 0) //no matching charachters
				{
					not_matched += nextChar;
				}
				else if(currentMatch.length() > SIZE_OF_REFERENCE) //match long enough to be replaced is found
				{
					//first: print number of unmatched characters, and the unmatched charaters (if there are any)
					if(not_matched.length() > 0)
					{
						write_unmatched(not_matched);
					}
					//					print_Color("blue" , currentMatch);
					//then the reference to the macthed sequence we have found
					write_reference(currentIndex , matchIndex , currentMatch.length());

					//the next char is not included in the matched string, but it can still match whats behind it
					if(hasMatch(searchBuffer , nextChar))
					{
						currentMatch = "" + nextChar;
						not_matched = "";
					}
					else
					{
						currentMatch = "";
						not_matched = "" + nextChar;
					}
				}
				else //match is found, but its not long enough to replace with a reference
				{
					if(hasMatch(searchBuffer , nextChar))
					{
						not_matched += currentMatch;
						currentMatch = "" + nextChar;
					}
					else
					{
						not_matched += currentMatch + nextChar;
						currentMatch = "";
					}
				}
				//Trim search buffer if it has gotten too long
				trimBuffer();
			}
		}
		currentIndex++;
		// flush any match/unMatch we may have had when we reached end of file
		if(currentMatch.length() > SIZE_OF_REFERENCE)
		{
			if(not_matched.length() > 0)
			{
				write_unmatched(not_matched);
			}
			//			print_Color("blue" , currentMatch);
			write_reference(currentIndex , matchIndex , currentMatch.length());
		}
		else
		{
			not_matched += currentMatch;
			write_unmatched(not_matched);
		}
	}



	private void trimBuffer( )
	{
		if(searchBuffer.length() > BUFFER_SIZE)
		{
			searchBuffer.delete(0 , searchBuffer.length() - BUFFER_SIZE);
		}
	}



	/*
			[offset, length]
	 */
	private void write_reference(int curIndex , int match_index , int length_of_match)
	{
		int offset = match_index - (curIndex - length_of_match);
		//		print_Color("yellow" , "[" + offset + "," + length_of_match + "]");
		writer.writeReference(offset , length_of_match);
	}



	/*
			[length]unmathed_sequence
	 */
	private void write_unmatched(String unMatched)
	{
		//		print_Color("yellow" , "[" + unMatched.length() + "]");
		writer.writeShort(unMatched.length());
		//		System.out.print(unMatched);
		writer.print(unMatched);
	}



	private boolean hasMatch(StringBuffer buffer , char c)
	{
		return buffer.indexOf("" + c) != -1;
	}



	public static void main(String[] args)
	{
		Komprimering_LZ problemer = new Komprimering_LZ("problemer");
		Komprimering_LZ oppgavetekst = new Komprimering_LZ("oppgavetekst");
		Komprimering_LZ forelesningen = new Komprimering_LZ("forelesningen");
	}
}
