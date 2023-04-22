package src.util;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import src.ColorPrint;

public class Writer
{
    private DataOutputStream writer;

    public Writer(String path)
    {
        try
        {
            this.writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
        }
        catch (Exception e)
        {
            ColorPrint.print("red", "Error");
            System.out.println(": " + System.getProperty("user.dir") + path);
            e.printStackTrace();
        }
    }

    public void close()
    {
        if (writer == null) return;
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
