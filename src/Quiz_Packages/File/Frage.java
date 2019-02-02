package Quiz_Packages.File;
import java.util.ArrayList;
import java.util.List;


public class Frage {
	//Initialisierung der Objekte der Klasse Frage: Frage, Antworten und korrekte Antworten werden als Strings (privat) erstellt; Chancen und verwendete Chancen als Integer; Wahrheitswert, ob Frage benutzt wurde
    private String question;				
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswers;
    private int chances;
    private int usedChances;
    private boolean used = false;


    /**
	 * Konstruktor der Klasse Frage: 
     * @param question
     * @param answer1
     * @param answer2
     * @param answer3
     * @param answer4
     * @param correctAnswers
     * @param chances
     */
    public Frage(String question, String answer1, String answer2, String answer3, 
                                        String answer4, String correctAnswers, int chances){
        //Teste, ob die Elemente wirklich existieren, d.h. ob dort nutzbare Informationen drinnen sind -> Fehlervermeidung durch Exception
        if (question == null || answer1 == null || answer2 == null|| answer3 == null || answer4 == null) throw new NullPointerException();
        if (question.equals("")||
                answer1.equals("")||
                answer2.equals("")||
                answer3.equals("")||
                answer4.equals("")||
                correctAnswers.equals("")||
                chances < 0 ||
                chances>3) {
            throw new IllegalArgumentException();
        }                               
        
		//Zuordnen der übergebenen Parameter zu den Objekten der Klasse Frage: this.question meint Objekt während question der Parameter ist
        this.correctAnswers = correctAnswers;                 //bringe die KonstruktorVariablen in das Objekt
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.chances = chances;
        this.usedChances = chances-1;                         //kopiere die Chancen, zweite kann bearbeitet werden
																// im Moment des Fragenaufrufs, wirddie 1. Chance bereits verwendet -> es bleiben noch chancen-1
    }

//alle Getter-Methoden
    /**
	 * Getter-Methode für Anzahl der Chancen
     * Integer, amount of chances
     * @return
     */
    public int getChances(){
		return chances;
	}

    /**
	 * Getter-Methode für die Anzahld er verwendeten Chancen
     * Integer, amount of used chances
     * @return
     */
    public int getUsedChances(){
		return usedChances;
	}

    
    /**
	 * Getter-Methode um verbleibende Chancen zu errechnen und zurückzugeben
     * remaining chances
     * @return ? (wenn: "int actC=chances-usedChances; return actC" wäre return actC, wie formuliert man das hier im JavaDoc?)
     */
    public int getActChances(){                              //gibt die Anzahl der verbleibenden Versuche zurück
        return chances - usedChances;
    }



    /**
     * Getter-Methode um "used" zurückzugeben
     * @return used
     */
    public boolean getUsed(){
        return used;
    }

    /**
	 * Getter-Methode um Frage zurückzugeben
     * returns the question
     * @return question
     */
    public String getQuestion(){
		return this.question;
	}

    /**
	 * Getter-Methode zur Rückgabe der richtigen Antwort als String
     * returns the correct answer
     * @return
     */
    public String getCorrectAnswers(){
		return this.correctAnswers;
	}


    /**
     * Erstellt eine Liste(?) aus den Objekten der Klasse und gibt sie als solche zurück
     * simply return all possible answers
     * @return
     */
    public List<String> getList(){
        List<String> kompletteFrage = new ArrayList<>();
            kompletteFrage.add(question);
            kompletteFrage.add(answer1);
            kompletteFrage.add(answer2);
            kompletteFrage.add(answer3);
            kompletteFrage.add(answer4);
            kompletteFrage.add(getCorrectAnswers());
            kompletteFrage.add(String.valueOf(getChances()));
        return kompletteFrage;

    }

//alle Setter-Methoden
	/**
	 * Setter-Methode um verwendete Chancen um 1 zu verringern (dekrementieren)
     * decreases the amount of chances
     */
    public void decChances(){                                //decrementiere die Chancen, wenn Frage übersprungen wurde
        if (getChances() >1)
              usedChances--;  
    }
	
	/**
	 * Setter-Methode
     * Vermeiden von Fragendopplungen (keine Frage soll 2mal benutzt werden)
     * @param b
     */
    public void setUsed(boolean b){
        used = b;								//übergebener Parameter wird als boolean in used gespeichert (wahr oder falsch gesetzt)
    }
	
	/**
	 * Setter-Methode um Anzahl der Chancen zu verändern
	 * @param chances (int)
	 * @return chances
	 */
    public void setChances(int chances){
        this.chances = chances;
    }

}
