package øving_10;

import static hjelp.ColorPrint.println_Color;
import static hjelp.ColorPrint.string_Color;

import java.util.regex.Pattern;

public class oppgave6
{
	public static void main(String[] args)
	{
		println_Color("blue" , "\na) inneholder et siffer");
		System.out.println( regex(".*\\d.*" , "ab3"));
		System.out.println( regex(".*\\d.*" , "abc"));

		println_Color("blue" , "\nb) har datoformatet DD/MM/YYYY");
		System.out.println( regex("\\d{2}/\\d{2}/\\d{4}" , "25/05/1997"));
		System.out.println( regex("\\d{2}/\\d{2}/\\d{4}" , "21/5/1997"));

		println_Color("blue" , "\nc) har minst 10 tegn");
		System.out.println( regex(".{10}.*" , "12345678910"));
		System.out.println( regex(".{10}.*" , "123456789"));

		println_Color("blue" , "\nd) Inneholder andre tegn enn bokstaver");
		System.out.println( regex(".*\\W.*" , "abc 123"));
		System.out.println( regex(".*\\W.*" , "abc123"));
	}



	private static String regex(String regex , String test)
	{
		Pattern pattern = Pattern.compile(regex);
		String result = test + "\t  matches " + "\t" + regex + "\t?\t";

		if(pattern.matcher(test).matches())
		{
			result += string_Color("green" , "YES");
		}
		else
		{
			result += string_Color("red" , "NO");
		}
		return result;
	}
}
