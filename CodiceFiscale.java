/**
 * La classe Codice Fiscale gestisce i dati di una persona,
 * come: cognome, nome, data di nascita, sesso, luogo di nascita
 * e da questi dati viene calcolato il codice fiscale
 *  
 * autore: Fabio Grasso
 * email: grasso89fabio@gmail.com 
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CodiceFiscale 
{
	private String cognome;
	private String nome;
	private String data;
	private int anno;
	private char mese;
	private int giorno;
	private char sesso;
	private String luogoDiNascita;
	private char carattereControllo;
	static Map<String, String> mappa = new HashMap<String, String>();
	
	public CodiceFiscale()
	{
	}
	
	public CodiceFiscale(String cognome, String nome, String data, char sesso, String luogoDiNascita) {
		setCognome(cognome);
		setNome(nome);
		this.sesso = sesso;
		setData(data);
		setLuogoDiNascita(luogoDiNascita);
		setCarattereControllo();
	}

	public void setCognome(String cognome) {
		int letteraCorrente = 0;
		int totConsonante = 0;
		int totVocale = 0;
		char[] formaCognome = new char[3];
		
		//conteggio delle consonanti e vocali
		for(int i = 0; i < cognome.length(); i++)
		{
			if(cognome.charAt(i) != 'a' && cognome.charAt(i) != 'e' && cognome.charAt(i) != 'i' && cognome.charAt(i) != 'o' && cognome.charAt(i) != 'u')
			{
				totConsonante++;
			}
			else
			{
				totVocale++;
			}
		}
		
		//aggiungo le consonanti
		for(int i = 0; i < cognome.length(); i++)
		{
			if(cognome.charAt(i) != 'a' && cognome.charAt(i) != 'e' && cognome.charAt(i) != 'i' && cognome.charAt(i) != 'o' && cognome.charAt(i) != 'u')
			{
				formaCognome[letteraCorrente] = cognome.charAt(i);
				letteraCorrente++;
			}
				
			if(letteraCorrente == 3)
			{
				break;
			}
		}

		//se ci sono solo 2 consonanti, aggiungo la prima vocale
		if(totConsonante == 2)
		{
			for(int i = 0; i < cognome.length(); i++)
			{
				if(cognome.charAt(i) == 'a' || cognome.charAt(i) == 'e' || cognome.charAt(i) == 'i' || cognome.charAt(i) == 'o' || cognome.charAt(i) == 'u')
				{
					formaCognome[letteraCorrente] = cognome.charAt(i);
					letteraCorrente++;
				}
				if(letteraCorrente == 3)
				{
					break;
				}
			}
		}
		
		//se c'è 1 consonante, aggiungo le prime 2 vocali
		else if(totConsonante == 1)
		{
			for(int i = 0; i < cognome.length(); i++)
			{
				if(cognome.charAt(i) == 'a' || cognome.charAt(i) == 'e' || cognome.charAt(i) == 'i' || cognome.charAt(i) == 'o' || cognome.charAt(i) == 'u')
				{
					formaCognome[letteraCorrente] = cognome.charAt(i);
					letteraCorrente++;
				}
				if(letteraCorrente == 3)
				{
					break;
				}
			}
		}
		
		//se c'è 1 consonante e 1 vocale, aggiungo una 'x'
		else if(totConsonante == 1 && totVocale == 1)
		{
			formaCognome[letteraCorrente] = 'x';
		}
		
		//se ci sono solo 2 vocali, aggiungo una 'x'
		else if(totConsonante == 0 && totVocale == 2)
		{
			for(int i = 0; i < cognome.length(); i++)
			{
				if(cognome.charAt(i) == 'a' || cognome.charAt(i) == 'e' || cognome.charAt(i) == 'i' || cognome.charAt(i) == 'o' || cognome.charAt(i) == 'u')
				{
					formaCognome[letteraCorrente] = cognome.charAt(i);
					letteraCorrente++;
				}
				
				formaCognome[letteraCorrente] = 'x';
			}
		}
		
		//trasformo un array di caratteri in una stringa
		String nuovoCognome = new String(formaCognome).toUpperCase();
		this.cognome = nuovoCognome;
	}
	
	public void setNome(String nome) {
		int letteraCorrente = 0;
		int totConsonante = 0;
		int totVocale = 0;
		char[] formaNome = new char[3];
		
		//conteggio delle consonanti e vocali
		for(int i = 0; i < nome.length(); i++)
		{
			if(nome.charAt(i) != 'a' && nome.charAt(i) != 'e' && nome.charAt(i) != 'i' && nome.charAt(i) != 'o' && nome.charAt(i) != 'u')
			{
				totConsonante++;
			}
			else
			{
				totVocale++;
			}
		}
		
		//si ci sono 4 o + consonanti prendo la 1-3-4 consonante
		if(totConsonante >=4)
		{
			int posizioneConsonante = 1;
			for(int i = 0; i < nome.length(); i++)
			{
				if(nome.charAt(i) != 'a' && nome.charAt(i) != 'e' && nome.charAt(i) != 'i' && nome.charAt(i) != 'o' && nome.charAt(i) != 'u')
				{
					if(posizioneConsonante == 1 || posizioneConsonante == 3 || posizioneConsonante == 4)
					{
						formaNome[letteraCorrente] = nome.charAt(i);
						letteraCorrente++;
					}
					posizioneConsonante++;
				}
					
				if(letteraCorrente == 3)
				{
					break;
				}
			}
		}
		
		//se ci sono 3 o - consonanti
		if(totConsonante <= 3)
		{
			for(int i = 0; i < nome.length(); i++)
			{
				if(nome.charAt(i) != 'a' && nome.charAt(i) != 'e' && nome.charAt(i) != 'i' && nome.charAt(i) != 'o' && nome.charAt(i) != 'u')
				{
					formaNome[letteraCorrente] = nome.charAt(i);
					letteraCorrente++;
				}
							
				if(letteraCorrente == 3)
				{
					break;
				}
			}
		}
		
		//se ci sono solo 2 consonanti, aggiungo la prima vocale
		if(totConsonante == 2)
		{
			for(int i = 0; i < nome.length(); i++)
			{
				if(nome.charAt(i) == 'a' || nome.charAt(i) == 'e' || nome.charAt(i) == 'i' || nome.charAt(i) == 'o' || nome.charAt(i) == 'u')
				{
					formaNome[letteraCorrente] = nome.charAt(i);
					letteraCorrente++;
				}
				if(letteraCorrente == 3)
				{
					break;
				}
			}
		}
		
		//se c'è 1 consonante, aggiungo le prime 2 vocali
		if(totConsonante == 1)
		{
			for(int i = 0; i < nome.length(); i++)
			{
				if(nome.charAt(i) == 'a' || nome.charAt(i) == 'e' || nome.charAt(i) == 'i' || nome.charAt(i) == 'o' || nome.charAt(i) == 'u')
				{
					formaNome[letteraCorrente] = nome.charAt(i);
					letteraCorrente++;
				}
				if(letteraCorrente == 3)
				{
					break;
				}
			}
		}
		
		//se c'è 1 consonante e 1 vocale, aggiungo una 'x'
		if(totConsonante == 1 && totVocale == 1)
		{
			formaNome[letteraCorrente] = 'x';
		}
				
		//se ci sono solo 2 vocali, aggiungo una 'x'
		if(totConsonante == 0 && totVocale == 2)
		{
			for(int i = 0; i < nome.length(); i++)
			{
				if(nome.charAt(i) == 'a' || nome.charAt(i) == 'e' || nome.charAt(i) == 'i' || nome.charAt(i) == 'o' || nome.charAt(i) == 'u')
				{
					formaNome[letteraCorrente] = nome.charAt(i);
					letteraCorrente++;
				}
						
				formaNome[letteraCorrente] = 'x';
			}
		}
		
		//trasformo un array di caratteri in una stringa
		String nuovoNome = new String(formaNome).toUpperCase();
		this.nome = nuovoNome;
	}
	
	public void setData(String data) {
		//la stringa data arriva in questo formato gg/mm/aa
		
		//recupero l'anno
		String anno = data.substring(6, 8);
		this.anno = Integer.parseInt(anno);
		
		//recupero il mese
		String m = (data.substring(3, 5));
		
		if(m.equals("01"))
		{
			this.mese = 'A';
		}
		else if(m.equals("02"))
		{
			this.mese = 'B';
		}
		else if(m.equals("03"))
		{
			this.mese = 'C';
		}
		else if(m.equals("04"))
		{
			this.mese = 'D';
		}	
		else if(m.equals("05"))
		{
			this.mese = 'E';
		}	
		else if(m.equals("06"))
		{
			this.mese = 'H';
		}	
		else if(m.equals("07"))
		{
			this.mese = 'L';
		}	
		else if(m.equals("08"))
		{
			this.mese = 'M';
		}		
		else if(m.equals("09"))
		{
			this.mese = 'P';
		}	
		else if(m.equals("10"))
		{
			this.mese = 'R';
		}
		else if(m.equals("11"))
		{
			this.mese = 'S';
		}		
		else
		{
			this.mese = 'T';
		}			
		
		//recupero il giorno
		int g = Integer.parseInt(data.substring(0, 2));
		if(sesso == 'f' || sesso == 'F')
		{
			this.giorno = g + 40;
		}
		else
		{
			this.giorno = g;
		}
	}

	public void setLuogoDiNascita(String luogoDiNascita) 
	{
		//recupero il valore corrisponente della chiave passata come parametro
		String codiceFinale = mappa.get(luogoDiNascita.toUpperCase());
		this.luogoDiNascita = codiceFinale;
	}
	
	public static boolean controllaPaese(String luogo)
	{
		//recupero da un file di testo delle chiavi - valori (nomeComune ; codice) di tutti i comuni italiani
		String nomeFile = "lista-codici.txt";
		Scanner inputStream = null;
		
		try
		{
			inputStream = new Scanner(new File(nomeFile));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Errore nell'apertura del file");
			System.exit(0);
		}
		while(inputStream.hasNextLine())
		{
			//leggo la riga corrente
			String riga = inputStream.nextLine();
			//trasformo la stringa in un array
			String[] array = riga.split(";");
			String comune = array[0];
			String codice = array[1];
			//aggiungo i valori(comune e codice) in una sorta di tabella
			mappa.put(comune, codice);
		}
		
		//se il luogo di nascita inserito dall'utente è presente nella mappa...
		if(mappa.containsKey(luogo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setCarattereControllo() 
	{
		//prendo solo i primi 15 caratteri, eliminando ogni spazio e converto la stringa in un array di caratteri
		String codice  = stampaCodice().replace(" ", "").substring(0, 15); 
		char[] carattere = codice.toCharArray();
		
		//creo le mappe per salvare le coppie chiavi-valori 
		Map<Character, Integer> dispari = new HashMap<Character, Integer>();
		Map<Character, Integer> pari = new HashMap<Character, Integer>();
		Map<Integer, Character> resto = new HashMap<Integer, Character>();
		
		//sono dei valori che servono per calcolare l'ultimo carattere del codice fiscale
		dispari.put('0', 1);
		dispari.put('1', 0);
		dispari.put('2', 5);
		dispari.put('3', 7);
		dispari.put('4', 9);
		dispari.put('5', 13);
		dispari.put('6', 15);
		dispari.put('7', 17);
		dispari.put('8', 19);
		dispari.put('9', 21);
		dispari.put('A', 1);
		dispari.put('B', 0);
		dispari.put('C', 5);
		dispari.put('D', 7);
		dispari.put('E', 9);
		dispari.put('F', 13);
		dispari.put('G', 15);
		dispari.put('H', 17);
		dispari.put('I', 19);
		dispari.put('J', 21);
		dispari.put('K', 2);
		dispari.put('L', 4);
		dispari.put('M', 18);
		dispari.put('N', 20);
		dispari.put('O', 11);
		dispari.put('P', 3);
		dispari.put('Q', 6);
		dispari.put('R', 8);
		dispari.put('S', 12);
		dispari.put('T', 14);
		dispari.put('U', 16);
		dispari.put('V', 10);
		dispari.put('W', 22);
		dispari.put('X', 25);
		dispari.put('Y', 24);
		dispari.put('Z', 23);
		
		//sono dei valori che servono per calcolare l'ultimo carattere del codice fiscale
		pari.put('0', 0);
		pari.put('1', 1);
		pari.put('2', 2);
		pari.put('3', 3);
		pari.put('4', 4);
		pari.put('5', 5);
		pari.put('6', 6);
		pari.put('7', 7);
		pari.put('8', 8);
		pari.put('9', 9);
		pari.put('A', 0);
		pari.put('B', 1);
		pari.put('C', 2);
		pari.put('D', 3);
		pari.put('E', 4);
		pari.put('F', 5);
		pari.put('G', 6);
		pari.put('H', 7);
		pari.put('I', 8);
		pari.put('J', 9);
		pari.put('K', 10);
		pari.put('L', 11);
		pari.put('M', 12);
		pari.put('N', 13);
		pari.put('O', 14);
		pari.put('P', 15);
		pari.put('Q', 16);
		pari.put('R', 17);
		pari.put('S', 18);
		pari.put('T', 19);
		pari.put('U', 20);
		pari.put('V', 21);
		pari.put('W', 22);
		pari.put('X', 23);
		pari.put('Y', 24);
		pari.put('Z', 25);
		
		//sono dei valori che servono per calcolare l'ultimo carattere del codice fiscale
		resto.put(0, 'A');
		resto.put(1, 'B');
		resto.put(2, 'C');
		resto.put(3, 'D');
		resto.put(4, 'E');
		resto.put(5, 'F');
		resto.put(6, 'G');
		resto.put(7, 'H');
		resto.put(8, 'I');
		resto.put(9, 'J');
		resto.put(10, 'K');
		resto.put(11, 'L');
		resto.put(12, 'M');
		resto.put(13, 'N');
		resto.put(14, 'O');
		resto.put(15, 'P');
		resto.put(16, 'Q');
		resto.put(17, 'R');
		resto.put(18, 'S');
		resto.put(19, 'T');
		resto.put(20, 'U');
		resto.put(21, 'V');
		resto.put(22, 'W');
		resto.put(23, 'X');
		resto.put(24, 'Y');
		resto.put(25, 'Z');
		
		//in base alla posizione del carattere si prende il valore della tabella pari o dispari
		int somma = 0;
		int posizione = 1;
		for(int i = 0; i < carattere.length; i++)
		{
			//se la posizione di i è pari...
			if(posizione % 2 == 0)
			{
				somma += pari.get(carattere[i]);
			}
			//se è dispari...
			else
			{
				somma += dispari.get(carattere[i]);
			}
			posizione++;
		}
		
		//la somma del valore dei 15 caratteri viene divisa per 26 e viene salvato il resto
		int r = somma % 26;
		
		this.carattereControllo = resto.get(r);
	}
	
	public String stampaCodice()
	{
		//aggiungo lo 0 davanti al numero se esso è inferiore o uguale a 9
		if(this.giorno <= 9)
		{
			return cognome + " " + nome + " " + anno + " " + mese + " " + "0" + giorno + " " + luogoDiNascita + " " + carattereControllo;
		}
		else
		{
			return cognome + " " + nome + " " + anno + " " + mese + " " + giorno + " " + luogoDiNascita + " " + carattereControllo;
		}
	}
	
}
