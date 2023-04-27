package src;

import static src.Printer.blue;
import static src.Printer.green;
import static src.Printer.red;
import static src.Printer.yellow;

import java.io.File;
import java.nio.file.Files;
import src.util.Config;

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

    public static void main(String[] args)
    {
        testOnFile("Dylan Thomas.txt");
    }

    private static void testOnFile(String file)
    {
        File original = new File(Config.ORIGINAL_PATH + file);
        LempelZiv.compress(file);
        File compressed = new File(Config.COMPRESSED_PATH + file);
        LempelZiv.decompress(file);
        File decompressed = new File(Config.DECOMPRESSED_PATH + file);

        long originalSize = original.length();
        long compressedSize = compressed.length();
        long decompressedSize = decompressed.length();

        System.out.println(blue("\n" + file));
        System.out.println("Original Size: \t\t" + originalSize + " byte");
        double ratio = 1.0 * compressedSize / originalSize;
        System.out.print("Compressed Size: \t" + compressedSize + " byte \t- ");
        System.out.println(yellow(String.format("%.1f%% Of Original", 100 * ratio)));
        System.out.print("Decompressed Size: \t" + decompressedSize + " byte\t- ");
        if (filesAreEqual(original, decompressed))
            System.out.println(green("MATCH"));
        else
            System.out.println(red("ERROR - Content Not Equal"));
        System.out.println("");
    }

    private static boolean filesAreEqual(File file1, File file2)
    {
        long file1Size = file1.length();
        long file2Size = file2.length();
        if (file1Size != file2Size) return false;
        try
        {
            return Files.mismatch(file1.toPath(), file2.toPath()) == -1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
