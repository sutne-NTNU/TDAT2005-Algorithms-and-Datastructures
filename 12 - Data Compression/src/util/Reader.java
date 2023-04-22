package src.util;
import static src.ColorPrint.print;
import static src.ColorPrint.println;

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
            print("red", "File not found");
            System.out.println(": " + System.getProperty("user.dir") + path);
        }
        catch (Exception e)
        {
            println("red", "ERROR");
            e.printStackTrace();
        }
    }

    public short readShort()
    {
        short sh = 0;
        try
        {
            sh = reader.readShort();
        }
        catch (EOFException e)
        {
            // reached end of file
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sh;
    }

    public byte readByte()
    {
        byte b = 0;
        try
        {
            b = reader.readByte();
        }
        catch (EOFException eofe)
        {
            // reached end of file
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }

    public void close()
    {
        if (reader == null) return;
        try
        {
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
