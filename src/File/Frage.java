import java.util.ArrayList;
import java.util.List;


public class Frage {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswers;
    private int chances;
    private int usedChances;
    private boolean used = false;
   
    
   
    
    public Frage(String question, String answer1, String answer2, String answer3, 
                                        String answer4, String correctAnswers, int chances){
        //Teste, ob die Elemente wirklich existieren, d.h. ob dort nutzbare Informationen drinnen sind
        if (question == null || answer1 == null || answer2 == null|| answer3 == null || answer4 == null) throw new NullPointerException();
        if (question == "" || answer1 == "" || answer2 == "" || answer3 == "" || answer4 == "" ||  correctAnswers == "" || chances < 0 || chances>3) {
            throw new IllegalArgumentException();
        }                               
        
        this.correctAnswers = correctAnswers;                       //bringe die KonstruktorVariablen in das Objekt
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.chances = chances;
        this.usedChances = chances-1;                                 //kopiere die Chancen, zweite kann bearbeitet werden
    }
    
    
    
    


    public int getChances(){return chances;}
    public int getUsedChances(){return usedChances;}
    
    public void decChances(){                                               //decrementiere die Chancen, wenn Frage übersprungen wurde
        if (getChances() >1)
              usedChances-=1;  
    }
    public int getActChances(){                                             //gibt die Anzahl der Versuche zurück
        return chances - usedChances;
    }
    public void setUsed(boolean b){
        used = b;
    }
    public String getQuestion(){return this.question;}
    public String getCorrectAnswers(){return this.correctAnswers;}
    public boolean getUsed(){
        return used;
    }




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


}
