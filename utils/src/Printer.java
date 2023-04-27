package src;

public class Printer
{
    private final static String OPEN = "\033[";
    private final static String BOLD = ";1m";
    private final static String CLOSE = "\033[0m";

    public static void main(String[] args)
    {
        System.out.println();
        String[] colors = new String[] {
            "gray", "red", "yellow", "green", "cyan", "blue", "magenta", "black"
        };
        for (String color : colors) System.out.println(coloured(color, color));
        System.out.println("\nArray: " + black(new int[] { 1, 2, 3 }));
        System.out.println("Boolean: " + toString(true) + " " + toString(false));
        System.out.println("Double/Float: " + black(11.0 / 7.0));
        System.out.println("Null value: " + black(null) + "\n");
    }

    private static String getColorCode(String color)
    {
        String colorCode = "00";
        colorCode = switch (color)
        {
            case "black" -> "30";
            case "red" -> "31";
            case "green" -> "32";
            case "yellow" -> "33";
            case "blue" -> "34";
            case "magenta" -> "35";
            case "cyan" -> "36";
            case "gray" -> "37";
            default -> throw new IllegalArgumentException("Unknown color: " + color);
        };
        return colorCode;
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
        if (obj instanceof int[])
        {
            return coloured(color, toString((int[])obj));
        }
        return OPEN + getColorCode(color) + BOLD + obj.toString() + CLOSE;
    }

    public static String black(Object obj) { return coloured("black", obj); }
    public static String red(Object obj) { return coloured("red", obj); }
    public static String green(Object obj) { return coloured("green", obj); }
    public static String yellow(Object obj) { return coloured("yellow", obj); }
    public static String blue(Object obj) { return coloured("blue", obj); }
    public static String magenta(Object obj) { return coloured("magenta", obj); }
    public static String cyan(Object obj) { return coloured("cyan", obj); }
    public static String gray(Object obj) { return coloured("gray", obj); }

    public static void setConsoleColor(String color)
    {
        System.out.print(OPEN + getColorCode(color) + BOLD);
    }

    public static void resetConsoleColor()
    {
        System.out.print(CLOSE);
    }

    public static String toString(boolean b)
    {
        if(b) return coloured("green", "true");
        return coloured("red", "false");
    }

    public static String toString(int[] array)
    {
        String str = "[";
        if (array.length > 0)
        {
            for (int i = 0; i < array.length - 1; i++)
            {
                str += array[i] + ",";
            }
            str += array[array.length - 1];
        }
        return str + "]";
    }

    public static String toString(char[] array)
    {
        String str = "[";
        if (array.length > 0)
        {
            for (int i = 0; i < array.length - 1; i++)
            {
                str += "'" + array[i] + "',";
            }
            str += "'" + array[array.length - 1] + "'";
        }
        return str + "]";
    }

    public static String toString(double d)
    {
        return String.format("%.2f", d);
    }
}
