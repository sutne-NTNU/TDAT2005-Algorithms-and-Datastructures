package src;

public class LempelZiv
{
    public static void compress(String file)
    {
        new Compressor(file);
    }

    public static void decompress(String file)
    {
        new Decompressor(file);
    }
}
