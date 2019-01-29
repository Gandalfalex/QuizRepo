package Quiz_Packages.File;

import java.util.*;


/**
 * This class represents the backbone of the program.
 * it stores a list of all Questions and return the list if needed
 */
public class FragenKatalog {

    private ArrayList<Frage> listOfAllQuestions = new ArrayList<>();		//als Datentyp der Array-Liste wird die Klasse Frage verwendet (ähnlich wie String für Zeichenketten verwendet wird)
    private static FragenKatalog fragenKatalog = null;						//statische Variable fragenkatalog

    /**
     * privater Konstrucktor, somit kann keine Klasse eine eigene Instanz des Konstrucktors erstellen
     * Andere Objekte können sich nur die eine existierende Instanz der Klasse über die "static getInstance()"-Methode hohlen
     * Somit besitzt jedes Objekt, welches diese Klasse benötigt, immer die gleiche Instanz
     *
     * Umsetzung des Singleton-Patterns
     */
    private FragenKatalog(){ }							//privater(?) Default-Konstruktor


    /**
     *Statische Methode (kann von anderen Objekten/Klassen aufgerufen werden), die einen neuen Fragenkatalog erstellt, wenn zuvor keiner vorhanden ist.
     * @return instanze dieser Klasse, sofern nicht null, wenn null, erstelle neue Instanz
     */
    public static FragenKatalog getInstance(){
        if (fragenKatalog == null) {
            fragenKatalog = new FragenKatalog();		//erstellt neuen fragenkatalog als Instanz des default-Konstruktors FragenKatalog()
        }
        return fragenKatalog;
    }

    /**
	 * Überprüft ob eine neu hinzu zu fügende Frage schon vorhanden ist, indem ein übergebener String mit dem Fragenkatalog zeilenweise verglichen wird
	 * Wenn der übergebene String bereits verwendet wird, dann wird dies angezeigt, falls nicht kann die Frage zum Katalog hinzugefügt werden. (true/false)
     * These function determines if a new question is viable by simply comparing the answer to all answers who are already part of the list
     * @param question Frage, die hinzugefügt werden soll
     * @return true, if valid, false if not
     */
    protected boolean validateNewQuestion(String question) {				//String question wird übergeben
        if (listOfAllQuestions.isEmpty()){									//wenn Liste aller Fragen leer ist, dann gebe false zurück
            return false;
        }
        else {																//ansonsten:
            System.out.println(question);									//gebe auf der Konsole den übergebenen Paramater (die Frage) question aus
            question = question + System.getProperty("line.separator");	//fügt der Frage question einen Zeilenumbruch hinzu
            for (Frage frage: listOfAllQuestions){							//for-each Schleife: für jedes Element frage der Liste listOfAllQuestions tue:

                if (frage.getQuestion().contains(question)){				//wenn die Frage question bereits als Frage vorhanden ist, dann
                    System.out.print(" existiert schon.");					//gebe auf der Konsole aus, dass sie bereits existiert
                    return false;											//und gebe false zurück
                }
            }
            return true;													// sonst gebe true zurück 
        }
    }

    /**
     * Setter-Methode, die eine neue Frage sowohl zur Fragenliste als auch zur Datei hinzufügt, die gerade verwendet wird
     * Protected, da sie nur innerhalb des Packages aufgerufen werden muss
     * @param frage Diese Frage soll zum Quiz hinzugefügt werden
     */
    protected void addQuestion(Frage frage){                                                //Fügt neue Frage zur Liste aller Fragen hinzu
        listOfAllQuestions.add(frage);
    }


    /**
     * Diese Setter-Methode erstellt Fragen-Objeckte und fügt sie der Liste hinzu (nach dem Zeilenweisen durchlaufen);
     * Protected, da sie nur innerhalb des Packages aufgerufen werden muss
     * @param file entspricht dem Textfile, Jede Zeile = ein Eintrag in der Liste
     * @param chanceTotal Anzahl an Chancen, wenn 0, dann übernehme aus File
     */
    protected void createQuestionList(ArrayList<String> file, int chanceTotal){		//Datei und totale Chancenanzahl als Parameter übergeben
        listOfAllQuestions.clear();													//clear entfernt alle Elemente aus der ArrayList -> leere Array-Liste
        if (file.isEmpty()) throw new NullPointerException();						//überprüfe ob Datei (?) leer ist, wenn ja -> Fehlermeldung durch Exception
        int limit = file.size();													//Interger Limit soll Größe der Datei speichern als max. Durchlauf der for-Schleife
        
		//for-Schleife
		for (int i = 0; i+7<=limit; i = i+7) {							// "7", da Block aus 7 Zeilen für Frage, 4 Antworten, richtige Antwort und Chancenzahl besteht
            int chances = 1;											//setzt Chancenzahl auf 1 (wird beibehalten, falls in Datei kein korrekter Wert angegeben)

            if (chanceTotal <= 3 && chanceTotal >= 1) {					//überprüft ob in Datei angegebene Chancenzahl zwischen 1 und 3 liegt (erlaubter Bereich)
                chances = chanceTotal;									//wenn ja, wird Wert aus Datei genutzt und als Wert für die Chancen gesetzt
            } else {
				
				//try-catch-Phrase
                try {
                    String[] t = file.get(i + 6).split("");				//Funktion um die Chancenzeile (7. der Frage) in einen verwertbaren Integer zu übersetzen
                    chances = Integer.parseInt(t[0]);
                } 
				catch (Exception e) {									//soll Fehler abfangen, wenn String nicht in Integer übersetzt werden kann
                    System.out.println("Ein Fehler ist aufgetreten bei der Übersetzung des Strings.");
                }
            }
			//Füge als neue Frage hinzu (zeilenweise)
            listOfAllQuestions.add(new Frage(file.get(i), file.get(i + 1),
                        file.get(i + 2), file.get(i + 3), file.get(i + 4), file.get(i + 5), chances)); //zuvor durchlaufener 7er-Block nun zeilenweise aufgespalten

        }
    }

	/**
	 *Getter-Methode
	 *@param amount (int) Anzahl der zu nutztenden Fragen
	 *@return Liste an Fragen, die genutzt wurden
	 */
    public ArrayList<Frage> getQuestions(int amount){				//Parameter amount wird übergeben (amount= Anzahl der zu spielenden Fragen im Quiz)

        if (amount >= listOfAllQuestions.size() || amount <=0){	//wenn amount größer-gleich der Listengröße aller Fragen oder kleiner als 0, dann
            amount = listOfAllQuestions.size()-1;					//setze amount auf den um 1 verringerten Wert der Größe aller Fragen (da Liste bei 0 beginnt)
        }
		
        ArrayList<Frage> temp = new ArrayList<>();					//Erstelle eine neue Arrayliste "temp" (soll temporär da sein?)
        System.out.println(getSizeUsedQuestions());					//gebe auf der Konsole den Rückgabewert aus der Methode "getSizeUsedQuestions()" [folgend gSUQ] aus
        
		if (amount>=getSizeUsedQuestions()) {						//wenn übergebener amount-Wert größer-gleich dem gSUD-Wert, dann
            System.out.println("i should be here");				//gebe auf der Konsole aus "Ich sollte hier sein" und: for-each Schleife ausführen
            for (Frage frage : listOfAllQuestions) {				//für jedes Element frage aus dem Array listOfAllQuestions tue:
                frage.setUsed(false);								//setze den "benutzt-Status" der Frage auf false
            }
        }

        if (amount < 3) {											//wenn übergebener Parameter amount kleiner als 3 ist, dann:
            return listOfAllQuestions;								//gebe Liste aller Fragen zurück
        }
        else {														//ansonsten:
            ArrayList<Frage> tempUnUsed = new ArrayList<>();		//erstelle neuen Fragen-Array "tempUnUsed" als Array-Liste (soll derzeit ungenutzt Fragen erhalten)
            for (Frage frage: listOfAllQuestions){					//for-each-Schleife: für jedes Element Frage der Liste aller Fragen tue:
                if (!frage.getUsed()){								//wenn Frage noch nicht genutzt wurde  !Boolean == Boolean == false
                    tempUnUsed.add(frage);							//füge Frage zu tempUnUsed hinzu
                }
            }
            while (temp.size() != amount) {						//solange wie die Größe von temp ungleich dem amount-Wert
                Random rand = new Random();						//erstelle neue pseudeo-Random-Zahl
                int randomQ = rand.nextInt(tempUnUsed.size());		//erstelle Integer randomQ
                if (!temp.contains(tempUnUsed.get(randomQ))) {		//wenn tempUnUsed an der Stelle randomQ nicht in temp enthalten ist (?), dann
                    temp.add(tempUnUsed.get(randomQ));				//füge tempUnUsed an der Stelle randomQ zu temp hinzu
                    tempUnUsed.get(randomQ).setUsed(true);			//und setze den "Benutzt"-Status von tempUnUsed an der Stelle randomQ auf true
                }
            }
        }


        for (Frage frage: temp){									//for-each-Schleife: für jedes Element frage aus dem Array temp tue:
            System.out.print(frage.getQuestion());					//gebe auf der Konsole die Frage aus
        }
        return temp;
    }

	/**Getter-Methode, die die Größe (in Bits) der verwendeten Fragen zurückgibt.
     * @return amount Anzahl der benutzten Fragen
	 */
    protected int getSizeUsedQuestions(){
        int amount = 0;									//Interger für Anzahl der Bits erstellt und standardmäßig auf 0 gesetzt
        for (Frage frage: listOfAllQuestions){			//for-each Schleife: für jedes Element "frage" (Datentyp Frage) vom Array listOfAllQuestion:
            if (!frage.getUsed()){						//erhöhe amount um 1 wenn ???
                amount++;
            }
        }
        return amount;									//gebe amount zurück
    }

}
