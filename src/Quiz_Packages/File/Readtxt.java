package Quiz_Packages.File;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ICH
 */



// Wir lesen eine Textdatei ein und befüllen die Listen
import Quiz_Packages.Play.GetStats;

import java.io.*;
import java.util.*;


/**
 * This class reads and writes textfiles
 */
public class Readtxt {
    private String fileName = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";		//String fileName erstellt: ergibt sich aus dem Benutzerordner, den Quiz-Ordnern dieses Projektes und trägt den Namen "new.txt"
    private FileWriter writer;									//erstellt neuen "writer" vom Datentyp FileWriter 
    private File file;											//erstellt neues "file" vom Datentyp File
    private static Readtxt readFiles = null;					//erstellt statische Variable "readFiles" als Readtxt (Datentyp)

    /**
     * Privater default-Konstruktor, Singleton Object
	 * @param
	 * @return
     */
    private Readtxt(){}

    /**
	 * Statische Getter-Methode, die eine Instanz dieser Klasse zurückgibt
     * static Method that returns one instance of this class
	 * @param 
     * @return single Method or creates new one if null
     */
    public static Readtxt getInstance(){
        if (readFiles == null){									//wenn readFiles leer ist, dann
            readFiles = new Readtxt();							//erzeuge neue Instanz der Klasse Readtxt
        }
        return readFiles;										//gebe readFiles zurück
    }

    /**
     * Setter-Methode, die übergebenen Dateinamen auf Korrektheit überprüft und anschließend als fileName speichert.
     * @param fileName sets the Path to the file
     */
    public void setFileName(String fileName){						//Parameter fileName (Dateiname) wird übergeben
        if (fileName == null) {										//wenn fileName leer ist, dann:
            throw new NullPointerException();						//NullPointerException (da Dateiname nichtr leer sein darf)
        }
        if (fileName.equals("") || !fileName.contains(".txt")) {	//wenn der fileName zwar vorhanden, aber Endung nicht korrekt ist, dann
            throw new IllegalArgumentException();					//IllegalArgumentException (falsche Dateiendung verwendet)
        }
        this.fileName = fileName;									//wenn beide Fehlerquellen ausgeschlosse, dann übernehme übergebenen Parameter für fileName
    }

    /**
     * Setter-Methode, die neue Datei (file) erstellt, wenn vorher keine vorhanden sein sollte, und sie mit Strings füllt.
     * @param frage, ArrayList that contains all Information about the Question
     */
    public void addNewQuestion(List<String> frage)  {							//"frage" wird als Liste übergeben (Parameter)
        if (frage == null) throw new NullPointerException();                    //wenn frage leer, dann NullPointerException
        file = new File(fileName);                                              //erstelle neue Datei file
        
		//try-catch-Phrase
		try {																	//versuche:
            writer = new FileWriter(file ,true);								//writer als neuen FileWriter erstellen; wozu (file, true)?
            for (String string: frage) {										//writer soll String schreiben
                writer.write(string);											//writer soll Zeilenumbruchszeichen schreiben
                writer.write(System.getProperty("line.separator"));             
            }
            writer.flush();														//schreibe sie in die Datei -> flush-Befehl googlen
            writer.close();														//schließe den writer
        } catch (IOException e) {												//auffangen:
            e.printStackTrace();
        }

    }

    /**
     * Setter-Methode, die neue Statistik-Datei schreibt.
     * @param stats List of all achieved stats
     * @param statPath the location of the StatsFile
     */
    public void addStats(List<GetStats> stats, String statPath) {               //gleiches Prinzip wie bei der Liste
        try{                                                                    //lösche nur vorher die alten Stats
            PrintWriter pw = new PrintWriter(statPath);
            pw.close();
        } catch (Exception e) {}

        if (statPath.isEmpty()) throw new NullPointerException();
        file = new File(statPath);
        try{
            writer = new FileWriter(file, true);
            for (GetStats statObject: stats) {
                writer.write(statObject.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
        }

    }


    /**
     * Getter-Methode, die eine Datei aus einem angegebenem Dateipfad auslesen und ihren Inhalt als String zurückgeben kann.
     * @param filePath the Path to the File
     * @return A List of Strings. They will get converted into Frage_Objects later
     */
    public ArrayList<String> readFile(String filePath) {						//Übergabe des Dateipfads als Parameter

        ArrayList<String> temp = new ArrayList<>();								//Erstelle neue (temporäre) Liste temp
        FileReader fr = null;													//Erstelle leeren FileReader fr
        BufferedReader br = null;												//Erstelle leeren BufferedRead br

        //try-catch-Phrase
		try																		//Versuche
        {
            File file = new File(filePath) ;									//Erstelle neues "file"  im angegebenen Dateipfad
            fr = new FileReader(file);											//Erstelle neuen FileReader fr für "file"
            br = new BufferedReader(fr) ;										//Erstelle neuen BufferedReader br für "file"

            String line ;														//Initialisierung des Strings line
            StringBuffer stringBuffer = new StringBuffer();						//Erstelle neuen Stringbuffer
            String sep = System.getProperty("line.separator");					//Erstelle einen String sep und speichere dort das Zeilenumbruchszeichen

            while( (line=br.readLine()) != null )								//solange wie die vom br gelesene Zeile und line nicht leer sind:
                temp.add(line+sep) ;											//füge zu temp die Zeile line und das Umbruchszeichen sep hinzu
        }
        catch(IOException ex) {													//Auffangen
            System.out.println("konnte nicht gelesen werden.");					//gebe auf der Konsole aus, dass die Datei nicht gelesen werden konnte
        }
		
        finally {																//finally -> Googlen
            try {																//Versuche
                if(br!=null) br.close();										//wenn br nicht leer ist, schließe ihn
                if(fr!=null) fr.close();										//wenn fr nicht leer ist, schließe ihn
            }
            catch(Exception ex) {}												//Auffangen
        }
        
    return temp;																//gebe die ArrayListe temp zurück
    }
    
    
    
}
