package Quiz_Packages.File;
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


    /**
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
        //Teste, ob die Elemente wirklich existieren, d.h. ob dort nutzbare Informationen drinnen sind
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
        
        this.correctAnswers = correctAnswers;                       //bringe die KonstruktorVariablen in das Objekt
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.chances = chances;
        this.usedChances = chances-1;                                 //kopiere die Chancen, zweite kann bearbeitet werden
    }


    /**
     * Integer, amount of chances
     * @return
     */
    public int getChances(){return chances;}

    /**
     * Integer, amount of used chances
     * @return
     */
    public int getUsedChances(){return usedChances;}

    /**
     * decreases the amount of chances
     */
    public void decChances(){                                               //decrementiere die Chancen, wenn Frage übersprungen wurde
        if (getChances() >1)
              usedChances-=1;  
    }

    /**
     * remaining chances
     * @return
     */
    public int getActChances(){                                             //gibt die Anzahl der Versuche zurück
        return chances - usedChances;
    }

    /**
     * a Question should'nt be used two times
     * @param b
     */
    public void setUsed(boolean b){
        used = b;
    }

    /**
     *
     * @return
     */
    public boolean getUsed(){
        return used;
    }

    /**
     * returns the question
     * @return
     */
    public String getQuestion(){return this.question;}

    /**
     * returns the correct answer
     * @return
     */
    public String getCorrectAnswers(){return this.correctAnswers;}


    /**
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

    public void setChances(int chances){
        this.chances= chances;
    }

}
