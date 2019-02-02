
package Quiz_Packages.Play;
import java.util.*;


public class GetStats {
    
    

    private String givenAnswer;
    private String question;
    private String correct;
    private int time;
    private int usedChances;
    private int points;
    
    
    
    public GetStats(String question, String correct, String answer, int t, int c, int p){
        if (question == null ||
                correct == null ||
                answer == null ||
                t < 0) throw new NullPointerException();
        if (question.equals("") || correct.equals("") || answer.equals("")) throw new IllegalArgumentException();
        this.question = question;
        this.correct = correct;
        this.time = t;
        this.usedChances = c;
        this.givenAnswer = answer;   
        this.points = p;
    }
    

    @Override
    public String toString(){       // toString Methode überschrieben
        if (givenAnswer.equals(correct)){
            return ("\nDie Frage: " + question.replace(System.lineSeparator(),"") + " wurde richtig beantwortet in einer Zeit von " + Math.floor(time/100)/10 +
                    " Sekunden. \nDer Spieler nutze dafür " + usedChances + " Versuche");
        }
        return ("\nDie Frage:" + question.replace(System.lineSeparator(),"") + " wurde mit: " + givenAnswer.replace(System.lineSeparator(),"") + " beantwortet. Richtig wäre: "+ correct.replace(System.lineSeparator(),"") + 
                " gewesen. Benötigt wurden  " + Math.floor(time/100)/10 + " Sekunden. \nDer Spieler benötigte dafür "+ usedChances+ " Versuche. Aktuell "+ points + " \n\r");
    }
    
}


    
