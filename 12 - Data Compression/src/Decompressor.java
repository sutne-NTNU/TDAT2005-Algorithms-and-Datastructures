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
        this.writer = new Writer(Config.DECOMPRESSED_PATH + file);

        decompress();

        reader.close();
        writer.close();
    }

    private void decompress()
    {

        short b;
        int curIndex = 0; // current position in the uncompressed file
        int offset;

        while ((b = reader.readShort()) != 0)
        {
            if (b > 0) // b is how many clean characters that follow
            {
                for (int i = 0; i < b; i++)
                {
                    char c = (char)reader.readByte();
                    writer.writeChar(c);
                    buffer.append(c);
                    curIndex++;
                }
                // offset in  reference will be the next short in file
                offset = reader.readShort();
            }
            else // b is offset in a reference
            {
                offset = b;
            }

            int start = curIndex + offset; // point in buffer to start reading from
            int length = reader.readByte();
            for (int indeks = start; indeks < (start + length); indeks++)
            {
                char c = buffer.charAt(indeks);
                writer.writeChar(c);
                buffer.append(c);
                curIndex++;
            }
            trimBuffer();
        }
    }

    private void trimBuffer()
    {
        if (buffer.length() <= Config.BUFFER_SIZE) return;
        buffer.delete(0, buffer.length() - Config.BUFFER_SIZE);
    }
}
