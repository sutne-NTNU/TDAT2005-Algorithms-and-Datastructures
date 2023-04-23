package src;

import src.util.Config;
import src.util.Reader;
import src.util.Writer;

public class Decompressor
{

    StringBuffer buffer = new StringBuffer(Config.BUFFER_SIZE);
    private Reader reader;
    private Writer writer;

    public Decompressor(String file)
    {
        this.reader = new Reader(Config.COMPRESSED_PATH + file);
        this.writer = new Writer(Config.DECOMPRESSED_PATH + file, false);

        decompress();

        reader.close();
        writer.close();
    }

    public static void main(String[] args)
    {
        new Decompressor("Great Expectations.txt");
    }

    private void decompress()
    {
        short offset;
        while ((offset = reader.readShort()) != 0)
        {
            if (offset > 0)
            {
                // offset is number of regular characters that follow in file
                for (int i = 0; i < offset; i++)
                {
                    char c = reader.readChar();
                    writer.writeChar(c);
                    addToBuffer(c);
                }
            }
            else
            {
                // Offset is negative, its a reference, read the length from file as well
                int length = reader.readByte();
                // look backwards in buffer to find correct chars
                int start = buffer.length() + offset;
                int end = start + length;
                String chars = "";
                for (int i = start; i < end; i++)
                {
                    char c = buffer.charAt(i);
                    chars += c;
                    writer.writeChar(c);
                }
                for (int i = 0; i < length; i++)
                {
                    addToBuffer(chars.charAt(i));
                }
            }
        }
    }

    private void addToBuffer(char c)
    {
        buffer.append(c);
        if (buffer.length() == Config.BUFFER_SIZE + 1)
        {
            buffer.delete(0, 1);
        }
    }
}
