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

        this.decompress();

        this.reader.close();
        this.writer.close();
    }

    private void decompress()
    {
        short offset;
        while ((offset = this.reader.readShort()) != 0)
        {
            if (offset > 0)
            {
                // offset is number of regular characters that follow in file
                for (int i = 0; i < offset; i++)
                {
                    char c = this.reader.readChar();
                    this.writer.writeChar(c);
                    this.addToBuffer(c);
                }
            }
            else
            {
                // Offset is negative, its a reference, read the length from file as well
                int length = this.reader.readByte();
                // look backwards in buffer to find correct chars
                int start = this.buffer.length() + offset;
                int end = start + length;
                String chars = "";
                for (int i = start; i < end; i++)
                {
                    char c = this.buffer.charAt(i);
                    chars += c;
                    this.writer.writeChar(c);
                }
                for (int i = 0; i < length; i++)
                {
                    this.addToBuffer(chars.charAt(i));
                }
            }
        }
    }

    private void addToBuffer(char c)
    {
        this.buffer.append(c);
        if (this.buffer.length() == Config.BUFFER_SIZE + 1)
        {
            this.buffer.delete(0, 1);
        }
    }
}
