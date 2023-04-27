package src.util;
import static src.Printer.red;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Reader
{
    private DataInputStream reader;

    public Reader(String path)
    {
        try
        {
            this.reader = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
        }
        catch (FileNotFoundException e)
        {
            String searchPath = System.getProperty("user.dir") + "/" + path;
            System.out.println(red("File Not Found: " + searchPath));
            System.exit(1);
        }
        catch (Exception e)
        {
            System.out.println(red("ERROR"));
            e.printStackTrace();
        }
    }

    public short readShort()
    {
        try
        {
            return this.reader.readShort();
        }
        catch (EOFException e)
        {
            // reached end of file
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public byte readByte()
    {
        try
        {
            return this.reader.readByte();
        }
        catch (EOFException eofe)
        {
            // reached end of file
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public char readChar()
    {
        return (char)this.readByte();
    }

    public void close()
    {
        if (this.reader == null) return;
        try
        {
            this.reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
