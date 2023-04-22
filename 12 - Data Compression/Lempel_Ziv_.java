package

import java.io.File;

import static hjelp.ColorPrint.println_Color;

12;


public class Lempel_Ziv_ {
    private final static String PATH = "src/12 - Data Compression/filer/";


    public static void main(String[] args) {
        printFilRatio("problemer");
        printFilRatio("oppgavetekst");
        printFilRatio("forelesningen");
    }


    private static void printFilRatio(String filnavn) {

        File orig = new File(PATH + "original/" + filnavn + ".txt");
        File kompr = new File(PATH + "komprimert/" + filnavn + "_LZ");
        File utpa = new File(PATH + "utpakket/" + filnavn + "_LZ.txt");
        int original = (int) orig.length();
        int komprimert = (int) kompr.length();
        int utpakket = (int) utpa.length();

        println_Color("blue", "\n\n\n" + filnavn);
        System.out.println("original størrelse: \t" + original + " byte");

        System.out.print("komprimert størrelse: \t" + komprimert + " byte \t- ");
        println_Color("yellow", 1.0 * ((int) ((10000.0 * (original - komprimert)) / original)) / 100 + "% spart");

        System.out.print("utpakket størrelse: \t" + utpakket + " byte\t- ");
        if (original == utpakket) {
            println_Color("green", "LIKT");
        } else {
            println_Color("red", "ULIKT");
        }
    }
}
