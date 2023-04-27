package src.utils;

import static src.Printer.red;

import src.Printer;

public class Scanner
{
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static final String INPUT_COLOR = "magenta";

    public static int getInt(String prompt) { return getInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE); }
    public static int getInt(String prompt, int min, int max)
    {
        System.out.print(prompt);
        try
        {
            Printer.setConsoleColor(INPUT_COLOR);
            int input = scanner.nextInt();
            Printer.resetConsoleColor();
            if (input < min || max < input) throw new RuntimeException("Input out of bounds");
            return input;
        }
        catch (Exception e)
        {
            System.out.println(red("Input not valid must be: " + min + " <= ? <= " + max));
            return getInt(prompt, min, max);
        }
    }

    public static char getChar(char[] validChars, String prompt)
    {
        System.out.print(prompt);
        try
        {
            String line;
            Printer.setConsoleColor(INPUT_COLOR);
            while ((line = scanner.nextLine()).length() == 0) { }
            Printer.resetConsoleColor();
            if (line.length() != 1) throw new RuntimeException("Input not valid");
            char input = line.charAt(0);
            for (char valid : validChars)
            {
                if (input == valid) return input;
            }
            throw new RuntimeException("Input not valid");
        }
        catch (Exception e)
        {
            System.out.println(red("Input not valid, must be one of: " + Printer.toString(validChars)));
            return getChar(validChars, prompt);
        }
    }

    public static String getString(String prompt)
    {
        System.out.print(prompt);
        try
        {
            String line;
            Printer.setConsoleColor(INPUT_COLOR);
            while ((line = scanner.nextLine()).length() == 0) { }
            Printer.resetConsoleColor();
            return line;
        }
        catch (Exception e)
        {
            System.out.println(red("Input not valid"));
            return getString(prompt);
        }
    }

    public static void main(String[] args)
    {
        System.out.println(getInt("Enter a number: ", 1, 3));
        System.out.println(getChar(new char[] { 'a', 'b', 'c' }, "Enter a char: "));
        System.out.println(getString("Enter a string: "));
    }
}
