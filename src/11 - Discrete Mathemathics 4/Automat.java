package øving_11;

import static hjelp.ColorPrint.println_Color;
import static hjelp.ColorPrint.string_Color;

class Automat
{
	private final char[] alfabet;
	private final int[][] neste_tilstands_funksjon;
	private int tilstand;
	private int[] aksepterende_tilstander;



	private Automat(String alfabet , int ant_tilsatnder , int[] aksepterende_tilstander)
	{
		this.alfabet = alfabet.toCharArray();
		this.neste_tilstands_funksjon = new int[ant_tilsatnder][this.alfabet.length];
		this.aksepterende_tilstander = aksepterende_tilstander;
	}



	public static void main(String[] args)
	{
		println_Color("blue" , "\nOppgave 8a: bitstreng som starter på 0, og inneholder nøyaktig 1 ener");
		int[] akseppterende_tilstander_8a = { 2 };
		Automat oppgave_8a = new Automat("01" , 4 , akseppterende_tilstander_8a);

		/*
		legger manuelt inn overganger slik da jeg synes det er mye mer oversiktlig, kunne også gitt en 2-dimensjonal array
		i konstruktøren men syntes det var vanskelig å se for seg de forskjellige overgangene mellom tilstandene slik
		*/
		oppgave_8a.leggTilFunksjon(0 , '0' , 1);
		oppgave_8a.leggTilFunksjon(0 , '1' , 3);
		oppgave_8a.leggTilFunksjon(1 , '0' , 1);
		oppgave_8a.leggTilFunksjon(1 , '1' , 2);
		oppgave_8a.leggTilFunksjon(2 , '0' , 2);
		oppgave_8a.leggTilFunksjon(2 , '1' , 3);
		oppgave_8a.leggTilFunksjon(3 , '0' , 3);
		oppgave_8a.leggTilFunksjon(3 , '1' , 3);

		oppgave_8a.sjekkInput_print("");
		oppgave_8a.sjekkInput_print("100");
		oppgave_8a.sjekkInput_print("010");
		oppgave_8a.sjekkInput_print("111");
		oppgave_8a.sjekkInput_print("010110");
		oppgave_8a.sjekkInput_print("001000");

		println_Color("blue" , "\n\nOppgave 8b: strenger som starter med ab");
		int[] akseppterende_tilstander_8b = { 3 };
		Automat oppgave_8b = new Automat("ab" , 5 , akseppterende_tilstander_8b);

		oppgave_8b.leggTilFunksjon(0 , 'a' , 1);
		oppgave_8b.leggTilFunksjon(0 , 'b' , 2);
		oppgave_8b.leggTilFunksjon(1 , 'a' , 4);
		oppgave_8b.leggTilFunksjon(1 , 'b' , 3);
		oppgave_8b.leggTilFunksjon(2 , 'a' , 3);
		oppgave_8b.leggTilFunksjon(2 , 'b' , 4);
		oppgave_8b.leggTilFunksjon(3 , 'a' , 3);
		oppgave_8b.leggTilFunksjon(3 , 'b' , 3);
		oppgave_8b.leggTilFunksjon(4 , 'a' , 4);
		oppgave_8b.leggTilFunksjon(4 , 'b' , 4);

		oppgave_8b.sjekkInput_print("abbb");
		oppgave_8b.sjekkInput_print("aaab");
		oppgave_8b.sjekkInput_print("babab");
		oppgave_8b.sjekkInput_print("bbba");
	}



	//legger inn en overgang fra en tilstand til en annen utifra et gitt tegn
	private void leggTilFunksjon(int fra_tilstand , char tegn , int til_tilstand)
	{
		neste_tilstands_funksjon[fra_tilstand][finnIndeks(tegn)] = til_tilstand;
	}



	//finner hvilken indeks tegnet har i neste_tilstands_funksjon tabellen
	private int finnIndeks(char tegn)
	{
		int tegn_indeks = -1;
		for(int i = 0 ; i < this.alfabet.length ; i++)
		{
			if(this.alfabet[i] == tegn)
			{
				tegn_indeks = i;
			}
		}
		return tegn_indeks;
	}



	//printer ut resultatet på en ryddig måte
	private void sjekkInput_print(String input)
	{
		if(sjekkInput(input))
		{
			String res = string_Color("green" , " \tTRUE");
			println_Color("yellow" , "\'" + input + "\' " + res + "\n");
			return;
		}
		String res = string_Color("red" , " \tFALSE");
		println_Color("yellow" , "\'" + input + "\' " + res + "\n");
	}



	//returnerer om en streng er akseptert eller ikke av Automaten
	private boolean sjekkInput(String input)
	{
		this.tilstand = 0;
		char[] inputArray = input.toCharArray();

		for(char c : inputArray)
		{
			if(finnIndeks(c) == -1)
			{
				//input strengen inneholder en char som ikke finnes i det gitte alfabetet
				return false;
			}
			//hvis tegnet er gyldig, endres tilstanden
			this.tilstand = funksjon(c);
		}

		//etter å ha gått igjennom hele input strenger sjekker vi om tilstanden vi nå er i, er en aksepterende tilstand
		for(int aksepterende_tilstand : aksepterende_tilstander)
		{
			if(this.tilstand == aksepterende_tilstand)
			{
				return true;
			}
		}
		return false;
	}



	//returnerer den nye tilstanden når vi leser tegnet 'tegn' fra får nåværende tilstand
	private int funksjon(char tegn)
	{
		//neste tilstanden ligger lagret i posisjone til hvilken tilstand vi er i, og indeksen til tegnet vi leser
		int ny_tilstand = neste_tilstands_funksjon[this.tilstand][finnIndeks(tegn)];
		//System.out.println("tilstand: " + this.tilstand + "\t\tleser " + tegn + "\t\tny tilstand: " + ny_tilstand);
		return ny_tilstand;
	}
}
