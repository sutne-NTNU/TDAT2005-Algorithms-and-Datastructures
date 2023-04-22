package utils;

public class ColorPrint
{
    private final static String OPEN = "\033[";
    private final static String BOLD = ";1m";
    private final static String CLOSE = "\033[0m";

    public static void main(String[] args)
    {
        println("gray", "TEST");
        println("red", "TEST");
        println("yellow", "TEST");
        println("green", "TEST");
        println("cyan", "TEST");
        println("blue", "TEST");
        println("magenta", "TEST");
        println("black", "TEST");
    }

    public static void print(String color, Object obj)
    {
        String colorString = ColorPrint.string(color, obj);
        System.out.print(colorString);
    }

    public static void println(String color, Object obj)
    {
        String colorString = ColorPrint.string(color, obj);
        System.out.println(colorString);
    }

    public static String string(String color, Object obj)
    {
        String str = "";
        if (obj instanceof Integer)
            str = "" + (int)obj;
        else if (obj instanceof String)
            str = (String)obj;

        String color_code = "00";
        switch (color)
        {
            case "black": color_code = "30"; break;
            case "red": color_code = "31"; break;
            case "green": color_code = "32"; break;
            case "yellow": color_code = "33"; break;
            case "blue": color_code = "34"; break;
            case "magenta": color_code = "35"; break;
            case "cyan": color_code = "36"; break;
            case "gray": color_code = "37"; break;
            default:
                throw new IllegalArgumentException("Unknown color: " + color);
        }
        return OPEN + color_code + BOLD + str + CLOSE;
    }
}