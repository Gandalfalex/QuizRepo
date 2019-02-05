
package Quiz_Packages.Play;
import java.util.*;


public class GetStats {
    
    
	//Variablen der Klasse GetStats
    private String givenAnswer;			//String für vom Spieler gegebene Antwort im Quiz
    private String question;			//String für die Frage an sich
    private String correct;				//String für korrekte Antwort
    private int time;					//Zeit wird als Inetger gespeichert
    private int usedChances;			//Integer für die verwendeten Chancen pro Frage
    private int points;					//Integer für die erhaltenen Punkte
    
    
    //Konstruktor
    public GetStats(String question, String correct, String answer, int t, int c, int p){ //Übergeben der Parameter: question, correct, answer als String; t, c, p als Integer
        if (question == null ||					//wenn Question, correct oder answer leer oder Zeit unter 0 -> Nullpointer-Exception
                correct == null ||
                answer == null ||
                t < 0) throw new NullPointerException();
        if (question.equals("") || correct.equals("") || answer.equals("")) throw new IllegalArgumentException();
        
		//wenn Nullpointer- und illegalArgument-Exception ausgeschlossen, dann ordne übergebene Parameter zu den Klassenvariablen zu mit "this"
		this.question = question;
        this.correct = correct;
        this.time = t;
        this.usedChances = c;
        this.givenAnswer = answer;   
        this.points = p;
    }
    

    @Override								// override-Befehl -> Überschreiben der Methode
    public String toString(){       		// Methode für die Ausgabe der Statistik
        if (givenAnswer.equals(correct)){	//wenn richtig beantwortet, gebe zurück:
            return ("\nDie Frage: " + question.replace(System.lineSeparator(),"") + " wurde richtig beantwortet in einer Zeit von " + Math.floor(time/100)/10 +
                    " Sekunden. \nDer Spieler nutze dafür " + usedChances + " Versuche. Aktueller Punktestand: "+ points + " \n\r");
        }
		//"sonst" gebe zurück:
        return ("\nDie Frage:" + question.replace(System.lineSeparator(),"") + " wurde mit: " + givenAnswer.replace(System.lineSeparator(),"") + " beantwortet. Richtig wäre: "+ correct.replace(System.lineSeparator(),"") + 
                " gewesen. Benötigt wurden  " + Math.floor(time/100)/10 + " Sekunden. \nDer Spieler benötigte dafür "+ usedChances+ " Versuche. Aktueller Punktestand: "+ points + " \n\r");
    }
    
}


    
