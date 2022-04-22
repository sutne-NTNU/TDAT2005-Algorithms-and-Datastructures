package hjelp;

public class ColorPrint {
    private final static String open = "\033[";
    private final static String bold = ";1m";
    private final static String close = "\033[0m";


    // Testprogram to make sure they all work
    public static void main(String[] args) {
        println_Color("black", "TEST");
        println_Color("red", "TEST");
        println_Color("green", "TEST");
        println_Color("yellow", "TEST");
        println_Color("blue", "TEST");
        println_Color("magenta", "TEST");
        println_Color("cyan", "TEST");
        println_Color("gray", "TEST");
        println_Color("dark gray", "TEST");
        println_Color("bright red", "TEST");
        println_Color("bright green", "TEST");
        println_Color("bright yellow", "TEST");
        println_Color("bright blue", "TEST");
        println_Color("bright magenta", "TEST");
        println_Color("bright cyan", "TEST");

        print_Color("black", "\n\nTEST ");
        print_Color("red", "TEST ");
        print_Color("green", "TEST ");
        print_Color("yellow", "TEST ");
        print_Color("blue", "TEST ");
        print_Color("magenta", "TEST ");
        print_Color("cyan", "TEST ");
        print_Color("gray", "TEST ");
        print_Color("dark gray", "TEST ");
        print_Color("bright red", "TEST ");
        print_Color("bright green", "TEST ");
        print_Color("bright yellow", "TEST ");
        print_Color("bright blue", "TEST ");
        print_Color("bright magenta", "TEST ");
        print_Color("bright cyan", "TEST ");
    }


    public static void print_Color(String color, Object obj) {
        String tekst = "";
        if (obj instanceof Integer) {
            tekst = "" + (int) obj;
        } else if (obj instanceof String) {
            tekst = (String) obj;
        }
        String color_code = "00";

        switch (color) {
            case "black":
                color_code = "30";
                break;
            case "red":
                color_code = "31";
                break;
            case "green":
                color_code = "32";
                break;
            case "yellow":
                color_code = "33";
                break;
            case "blue":
                color_code = "34";
                break;
            case "magenta":
                color_code = "35";
                break;
            case "cyan":
                color_code = "36";
                break;
            case "gray":
                color_code = "37";
                break;
            case "dark gray":
                color_code = "37;1";
                break;
            case "bright red":
                color_code = "31;1";
                break;
            case "bright green":
                color_code = "32;1";
                break;
            case "bright yellow":
                color_code = "33;1";
                break;
            case "bright blue":
                color_code = "34;1";
                break;
            case "bright magenta":
                color_code = "35;1";
                break;
            case "bright cyan":
                color_code = "36;1";
                break;
            default:
                System.out.print(tekst);
                return;
        }
        System.out.print(open + color_code + bold + tekst + close);
    }


    public static void println_Color(String color, Object obj) {
        String tekst = "";
        if (obj instanceof Integer) {
            tekst = "" + (int) obj;
        } else if (obj instanceof String) {
            tekst = (String) obj;
        }
        String color_code = "00";

        switch (color) {
            case "black":
                color_code = "30";
                break;
            case "red":
                color_code = "31";
                break;
            case "green":
                color_code = "32";
                break;
            case "yellow":
                color_code = "33";
                break;
            case "blue":
                color_code = "34";
                break;
            case "magenta":
                color_code = "35";
                break;
            case "cyan":
                color_code = "36";
                break;
            case "gray":
                color_code = "37";
                break;
            case "dark gray":
                color_code = "37;1";
                break;
            case "bright red":
                color_code = "31;1";
                break;
            case "bright green":
                color_code = "32;1";
                break;
            case "bright yellow":
                color_code = "33;1";
                break;
            case "bright blue":
                color_code = "34;1";
                break;
            case "bright magenta":
                color_code = "35;1";
                break;
            case "bright cyan":
                color_code = "36;1";
                break;
            default:
                System.out.println(tekst);
                return;
        }
        System.out.println(open + color_code + bold + tekst + close);
    }


    public static String string_Color(String color, Object obj) {
        String tekst = "";
        if (obj instanceof Integer) {
            tekst = "" + (int) obj;
        } else if (obj instanceof String) {
            tekst = (String) obj;
        }
        String color_code = "00";

        switch (color) {
            case "black":
                color_code = "30";
                break;
            case "red":
                color_code = "31";
                break;
            case "green":
                color_code = "32";
                break;
            case "yellow":
                color_code = "33";
                break;
            case "blue":
                color_code = "34";
                break;
            case "magenta":
                color_code = "35";
                break;
            case "cyan":
                color_code = "36";
                break;
            case "gray":
                color_code = "37";
                break;
            case "dark gray":
                color_code = "37;1";
                break;
            case "bright red":
                color_code = "31;1";
                break;
            case "bright green":
                color_code = "32;1";
                break;
            case "bright yellow":
                color_code = "33;1";
                break;
            case "bright blue":
                color_code = "34;1";
                break;
            case "bright magenta":
                color_code = "35;1";
                break;
            case "bright cyan":
                color_code = "36;1";
                break;
            default:
                return tekst;
        }
        return open + color_code + bold + tekst + close;
    }
}
