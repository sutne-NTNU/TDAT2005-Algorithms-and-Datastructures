package src;

import static src.ColorPrint.println;

import java.io.File;
import src.util.Config;

public class Main
{

    public static void main(String[] args)
    {
        testOnFile("problemer.txt");
        testOnFile("oppgavetekst.txt");
        testOnFile("forelesningen.txt");
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

        println("blue", "\n" + file);
        System.out.println("Original Size: \t\t" + originalSize + " byte");
        double ratio = 1.0 * compressedSize / originalSize;
        System.out.print("Compressed Size: \t" + compressedSize + " byte \t- ");
        println("yellow", String.format("%.1f%% Of Original", 100 * ratio));
        System.out.print("Decompressed Size: \t" + decompressedSize + " byte\t- ");
        if (originalSize == decompressedSize)
            println("green", "MATCH");
        else
            println("red", "ERROR");
    }
}
