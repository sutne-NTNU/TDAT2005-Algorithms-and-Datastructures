package src.util;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import src.ColorPrint;

public class Writer
{
    private DataOutputStream writer;
    private final boolean VISUALIZE;

    public Writer(String path, boolean VISUALIZE)
    {
        this.VISUALIZE = VISUALIZE;
        try
        {
            this.writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
        }
        catch (Exception e)
        {
            ColorPrint.print("red", "Error");
            System.out.println(": " + System.getProperty("user.dir") + "/" + path);
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void writeChar(char c)
    {
        try
        {
            writer.writeByte((byte)c);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void writeShort(int value)
    {
        try
        {
            writer.writeShort(value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void writeByte(int value)
    {
        try
        {
            writer.writeByte(value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // [offset,length]
    public void writeReference(int offset, int length)
    {
        if (this.VISUALIZE)
        {
            this.writeString("[" + offset + "," + length + "]");
        }
        else
        {
            this.writeShort(offset);
            this.writeByte(length);
        }
    }

    // [length]string
    public void writeStringWithLength(String str)
    {
        if (this.VISUALIZE)
        {
            this.writeString("[" + str.length() + "]" + str);
        }
        else
        {
            this.writeShort(str.length());
            this.writeString(str);
        }
    }

    public void writeString(String str)
    {
        try
        {
            char[] strArray = str.toCharArray();
            for (char c : strArray)
            {
                this.writeChar(c);
            }
        }
        catch (Exception e)
        {
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
}
