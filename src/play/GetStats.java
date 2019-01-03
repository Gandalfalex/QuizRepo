import java.util.*;


public class GetStats {
    
    

    private String givenAnswer;
    private String question;
    private String correct;
    private int time;
    private int usedChances;
    private int points;
    
    
    
    public GetStats(String question, String correct, String answer, int t, int c, int p){        
        if (question == null || correct == null || answer == null || t == 0|| c == 0) throw new NullPointerException();
        if (question == "" || correct == "" || answer == "") throw new IllegalArgumentException();
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
            return ("Die Frage: " + question + " wurde richtig beantwortet in einer Zeit von " + Math.floor(time/1000) +
                    " Sekunden und der Spieler nutze dafür " + usedChances + " Versuche");
        }
        return ("\nDie Frage:" + question + " wurde mit: " + givenAnswer + "beantwortet. Richtig wäre: "+ correct + 
                " gewesen. Benötigt wurden  " + time + " Millisekunden. Der Spieler benötigte dafür "+ usedChances+ " Versuche. Aktuell "+ points + " \n\r");
    }
    
}
