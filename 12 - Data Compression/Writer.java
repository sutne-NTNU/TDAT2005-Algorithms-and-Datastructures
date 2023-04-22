package øving_12;

import static hjelp.ColorPrint.println_Color;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Writer
{
    private DataOutputStream writer;



    public Writer(String fil)
    {
        try
        {
            String path = "src/øving_12/";
            System.out.print("Skriver til: \t" + fil);
            this.writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + fil)));
            println_Color("green", " OK");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void close()
    {
        if (writer == null)
        {
            return;
        }
        try
        {
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void writeChar(char c)
    {
        byte b = (byte)c;
        try
        {
            writer.writeByte(b);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void writeShort(int byte_value)
    {
        try
        {
            writer.writeShort((short)byte_value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void writeReference(int offset, int length)
    {
        //[offset,length]
        try
        {
            writer.writeShort(offset);
            writer.writeByte((byte)length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void print(String str)
    {
        try
        {
            char[] strArray = str.toCharArray();
            for (char c : strArray)
            {
                writer.writeByte((byte)c);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
