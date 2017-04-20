/**
 * La classe Test è il punto di inizio del programma.
 * Chiede di inserire alcuni dati all'utente e tramite la classe
 * CodiceFiscale viene generato il codice fiscale
 *  
 * autore: Fabio Grasso
 * email: grasso89fabio@gmail.com 
 */


import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Test 
{
	
	public static void main(String[] args)
	{
		String cognome;
		String nome;
		Date data = null;
		String d;
		char sesso;
		String luogoDiNascita;
		Scanner tastiera = new Scanner(System.in);
		
//****************COGNOME*********************	
		
		boolean cognomeValido = false;
		do
		{
			System.out.println("Cognome: ");
			cognome = tastiera.nextLine();
			
			//in questo modo accetto solo lettere
			String regex = "[A-Za-z]+";
			
			if(cognome.length() != 0 && Pattern.matches(regex, cognome))
			{
				cognomeValido = true;
			}
			
		}while(!cognomeValido);
		
//****************NOME*********************	
		
		boolean nomeValido = false;
		do
		{
			System.out.println("Nome: ");
			nome = tastiera.nextLine();
			
			//in questo modo accetto solo lettere
			String regex = "[A-Za-z]+";
			
			if(nome.length() != 0 && Pattern.matches(regex, nome))
			{
				nomeValido = true;
			}
		}while(!nomeValido);
		
//****************DATA*********************
		
		//mi procuro la data sotto-forma di una stringa nel formato SHORT
		boolean dataValida = false;
		do{
			System.out.println("Data di nascita (gg/mm/aaaa): ");
			d = tastiera.nextLine();
			try
			{
				//converto la stringa in un oggetto di classe Date
				DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
				//imposto che i calcoli di conversione della data siano rigorosi   
				formatoData.setLenient(false); 
				data = formatoData.parse(d);
				d = formatoData.format(data);
				dataValida = true;
			}
			catch(ParseException e)
			{
				 System.out.println("Formato data non valido.");
				 dataValida = false;
			}
		}while(!dataValida);
		
//****************SESSO*********************
		
		boolean sessoValido = false;
		do
		{
			System.out.println("Sesso (M o F): ");
			sesso = tastiera.next().charAt(0);
			
			if(sesso == 'm' || sesso == 'M' || sesso == 'f' || sesso == 'F')
			{
				sessoValido = true;
			}
			else
			{
				sessoValido = false;
			}
			
		}while(!sessoValido);
		
		tastiera.nextLine();
		
//****************LUOGO DI NASCITA*********************
		
		boolean esito = false;
		do
		{
			System.out.println("Luogo di nascita: ");
			luogoDiNascita = tastiera.nextLine().toUpperCase();
			esito = CodiceFiscale.controllaPaese(luogoDiNascita);
		}while(!esito);
		
		tastiera.close();
		
//****************CREO UN OGGETTO CODICE FISCALE*********************
		
		CodiceFiscale c = new CodiceFiscale(cognome, nome, d, sesso, luogoDiNascita);
		String codice = c.stampaCodice();
		System.out.println(codice);
	}

}
