package src;

import static src.Printer.blue;
import static src.Printer.green;
import static src.Printer.red;
import static src.Printer.yellow;

import java.util.regex.Pattern;

public class Task6
{
    public static void main(String[] args)
    {
        String regex;

        System.out.println(blue("\na)") + " Contains a digit");
        regex = ".*\\d.*";
        System.out.println("Regex: " + yellow(regex));
        System.out.println(regex(regex, "abcdef"));
        System.out.println(regex(regex, "abcd3f"));

        System.out.println(blue("\nb)") + " Has Date Format DD/MM/YYYY");
        regex = "[0-3]+[0-9]+/[0-1]+[0-9]+/\\d{4}";
        System.out.println("Regex: " + yellow(regex));
        System.out.println(regex(regex, "25/12/1997"));
        System.out.println(regex(regex, "12/25/1997"));
        System.out.println(regex(regex, "1/1/1997"));

        System.out.println(blue("\nc)") + " Has at least 10 characters");
        regex = ".{10}.*";
        System.out.println("Regex: " + yellow(regex));
        System.out.println(regex(regex, "abcdefghij"));
        System.out.println(regex(regex, "123456789"));

        System.out.println(blue("\nd)") + " Contains other characters than letters");
        regex = ".*\\W.*";
        System.out.println("Regex: " + yellow(regex));
        System.out.println(regex(regex, "abc 123"));
        System.out.println(regex(regex, "abc123") + "\n");
    }

    private static String regex(String regex, String test)
    {
        Pattern pattern = Pattern.compile(regex);
        boolean matches = pattern.matcher(test).matches();
        return matches ? green(test) : red(test);
    }
}
