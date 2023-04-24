package src;

public class Printer
{
    private final static String OPEN = "\033[";
    private final static String BOLD = ";1m";
    private final static String CLOSE = "\033[0m";

    public static void main(String[] args)
    {
        String[] colors = new String[] {
            "gray", "red", "yellow", "green", "cyan", "blue", "magenta", "black"
        };
        for (String color : colors) System.out.println(coloured(color, color));
    }

    private static String coloured(String color, Object obj)
    {
        if (obj == null)
        {
            return coloured("red", "null");
        }
        if (obj instanceof Double || obj instanceof Float)
        {
            return coloured(color, toString((double)obj));
        }

        String colorCode = "00";
        switch (color)
        {
            case "black": colorCode = "30"; break;
            case "red": colorCode = "31"; break;
            case "green": colorCode = "32"; break;
            case "yellow": colorCode = "33"; break;
            case "blue": colorCode = "34"; break;
            case "magenta": colorCode = "35"; break;
            case "cyan": colorCode = "36"; break;
            case "gray": colorCode = "37"; break;
            default:
                throw new IllegalArgumentException("Unknown color: " + color);
        }
        return OPEN + colorCode + BOLD + obj.toString() + CLOSE;
    }

    public static String black(Object obj) { return coloured("black", obj); }
    public static String red(Object obj) { return coloured("red", obj); }
    public static String green(Object obj) { return coloured("green", obj); }
    public static String yellow(Object obj) { return coloured("yellow", obj); }
    public static String blue(Object obj) { return coloured("blue", obj); }
    public static String magenta(Object obj) { return coloured("magenta", obj); }
    public static String cyan(Object obj) { return coloured("cyan", obj); }
    public static String gray(Object obj) { return coloured("gray", obj); }

    public static String toString(boolean b)
    {
        return coloured(b ? "green" : "red", b);
    }

    public static String toString(int[] array)
    {
        String str = "[";
        for (int i = 0; i < array.length - 2; i++)
        {
            str += array[i] + ", ";
        }
        str += array[array.length - 1];
        return str + "]";
    }

    public static String toString(double d)
    {
        return String.format("%.2f", d);
    }
}