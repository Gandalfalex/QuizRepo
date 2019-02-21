package Quiz_Packages.Play;

import Quiz_Packages.File.Frage;
import Quiz_Packages.File.FragenKatalog;

import java.lang.reflect.Array;
import java.util.*;


/**
 * the LogicClass of the PlayMVC
 * Klasse welche das Laufen des Quizzes sichert
 */
public class WorkingQuiz {
    

    // Variablen der Klasse WorkingQuiz                                                             //Nutzbare Liste der Fragen
    private int actualQuestion = 0;											// Integer für aktuelle Fragen = 0
    private List<GetStats> inputInformations = new ArrayList<>();			// inputInformations als Array-Liste
    private int points = 0;													// Integer für die Punkte
    private FragenKatalog fragenKatalog = FragenKatalog.getInstance();		// fragenkatalog vom Typ Fragenkatalog (Klasse), Inhalt über getInstance
    private ArrayList<Frage> questions;										// questions als ArrayLIst(Frage)
    private Frage frage = null;												// frage vom Typ Frage, Standardwert null
    private int limit;														// Integer für Limit


    /**
     * Quiz Konstruktor
     * @param amountOfQuestions Anzahl der in jeder Runde verwendeten Fragen
	 * @return 
     */
    public WorkingQuiz(int amountOfQuestions) {								// Übergebener Parameter amountOfQuestions als Integer
        questions = fragenKatalog.getQuestions(amountOfQuestions);			// Übergebe angegebene Anzahl an Fragen aus dem Fragenkatalog in die Variable questions
        limit = amountOfQuestions;											// setze Limit auf die Anzahl der Fragen
        System.out.println(questions.size());								// Gebe auf der Konsole die Anzahl der Fragen aus
    }

    /**
     * Setter-Methode: Erstellt Gruppe aus der Anzahl der verbleibenden Fragen / Creates a Multiplicative group using size of remaining Questions
	 * @param
	 * @return
     */
    public void calculateNextQuestion(){									// es werden keine Parameter übergeben

         if(questions.isEmpty()){											// wenn question leer ist, nehme Fragen aus Fragenkatalog (Anzahl in Limit festgelegt)
            fragenKatalog.getQuestions(limit);
        }
        else if (questions.size()==1){										// ansonsten, wenn nur 1 Frage vorhanden, dann spiele nur diese eine aktuelle Frage
            actualQuestion= 0;
            frage = questions.get(actualQuestion);
        }
        else {																// ansonsten:
            Random rand = new Random();										// erstelle neue Random-Zahl rand mit Java-Methode Random()

            actualQuestion =(actualQuestion + rand.nextInt(questions.size()-1)+1) % questions.size();	// aktuelle Frage ermitteln
            frage = questions.get(actualQuestion);							// setze frage auf aktuelle Frage aus questions
        }

    }


    /**
     * mischt die Antworten durch und gibt das Ergebnis zurück / Shuffles the Answers and returns the result
     * @param infomations Liste aller Strings
     * @return temp Strings in randomisierter Reiehenfolge
     */
    public List<String> mixAnswers(List<String> infomations){					// Übergebener Parameter temp

        Random rand = new Random();


        for (int i = 1; i <=4; i++){
            int random = rand.nextInt(4)+1;
            String s = infomations.get(random);
            infomations.set(random,infomations.get(i));
            infomations.set(i, s);

            System.out.println("swapped " + i + " with " + random);
        }


        return infomations;
    }		


    /**
     * Getter-Methode, die überprüft, ob die gegebene Antwort richtig oder falsch ist und entsprechend Punkte verteilt
     * @param answer given Answer
     * @return returns true, if the answer is correct
     */
    public boolean isCorrect(String answer) {							// übergebe die gegebene Antwort vom Spieler als Parameter
        if (answer == null || answer.equals("") && !questions.isEmpty()) throw new IllegalArgumentException();         //Answer muss Wert enthalten
        if (questions.get(actualQuestion).getCorrectAnswers().equals(answer)){                  //bei einer Lösung: A muss gleich der korrekten Lösung sein
            points = points++;													// bei richtige Antworte gebe einen Punkt, d.h. inkrementiere points
            return true;												// gebe wahr zurück -> Antwort ist richtig
        }
        return false;													// gebe falsch zurück -> Antwort ist falsch
    }

	/**
	 * Setter-Methode, die Statistik speichert.
	 * @param givenAnswer, time
	 * @return 
	 */
    public void saveStats(String givenAnswer, int time){				// übergebe gegebene Antwort (givenAnswer) und Zeit (time)			
       inputInformations.add(											// fügezu inputInformations hinzu: 
               new GetStats(frage.getQuestion(), frage.getCorrectAnswers(), givenAnswer, time, frage.getUsedChances(), points));

        questions.remove(actualQuestion);         						// Löschen der gespielten Frage aus der Liste
    }       


	/**
	 * Getter-Methode um Texte der aktuell genutzten Frage zurückzugeben
	 * @param
	 * @return Fragenliste
	 */
    public List<String> setText() {                                    	// um texte der aktuell genutzten Frage zurückzugeben
        return mixAnswers(frage.getList());								// verkürzte Schreibweise! ab hier häufiger verwendet
    }
	
	/**
	 * Setter-Methode, um genutzte Versuche im Fragenobjekt anzupassen
	 * @param
	 * @return
	 */
    public void decrementChances(){
		frage.decChances();												// dekrementiere Chancen, wenn Frage genutzt wurde
	}                 													//wurde eine Frage übersprungen, dann passe die genutzen Versuche in dem FragenObjekt an
																		//Frage stellt diese Funktion selbst bereit
	
	/**
	 * Getter-Methode zur Anzeige wie viele Versuche verbleiben
	 * @param
	 * @return verwendete Chancen
	 */
    public int getRemainingChances(){
		return frage.getUsedChances();									// gebe verwendete Chancen zurück
	}  																	// zur Anzeige, wieviele Versuche man noch hat, bei dem letzten Versuch irrelevant
	
	/**
	 * Getter-Methode zur Rückgabe noch vorhandener Fragen
	 * @param
	 * @return size (Anzahld der noch spielbaren/unbenutzten Fragen)
	 */
    public int getAllObjectsSize(){                                   	//Rückgabe, wie viele Fragen noch vorhanden sind
        return questions.size();
    }
    
	/**
	 * Getter-Methode zur Rückgabe der erspielten Punkte.
	 * @param
	 * @return points
	 */
    public int getPoints(){             
        return this.points;												// gebe erspielte Punkte zurück
    }
	
	/**
	 * Getter-Methode für die Statistik-Liste
	 * @param
	 * @return inputInformations
	 */
    public List<GetStats> printStats(){     //Erstelle LIste aus Objekten, adde jedesmal Instanz von addStats hinzu, um im laufenden Spiel darauf zuzugreifen
        return inputInformations;            //return Liste mit Stats    
    }                                      //Ausgabe für die Statistik
}
