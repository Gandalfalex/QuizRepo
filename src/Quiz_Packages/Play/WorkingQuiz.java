package Quiz_Packages.Play;

import Quiz_Packages.File.Frage;
import Quiz_Packages.File.FragenKatalog;

import java.util.*;


/**
 * the LogicClass of the PlayMVC
 */
public class WorkingQuiz {
    

                                                                 //Nutzbare Liste der Fragen
    private int actualQuestion = 0;
    private List<GetStats> inputInformations = new ArrayList<>();
    private int points = 0;
    private FragenKatalog fragenKatalog = FragenKatalog.getInstance();
    private ArrayList<Frage> questions;
    private Frage frage = null;
    private int limit;


    /**
     * The Quiz Constructor
     * @param amountOfQuestions amount of Questions used in each round
     */
    public WorkingQuiz(int amountOfQuestions) {
        questions = fragenKatalog.getQuestions(amountOfQuestions);
        limit = amountOfQuestions;
        System.out.println(questions.size());
    }

    /**
     * Creates a Multiplicative group using size of remaining Questions
     */
    public void calculateNextQuestion(){

         if(questions.isEmpty()){
            fragenKatalog.getQuestions(limit);
        }
        else if (questions.size()==1){
            actualQuestion= 0;
            frage = questions.get(actualQuestion);
        }
        else {
            Random rand = new Random();

            actualQuestion =(actualQuestion + rand.nextInt(questions.size()-1)+1) % questions.size();
            frage = questions.get(actualQuestion);
        }

    }


    /**
     * Shuffles the Answers and returns the result
     * @param temp List of all Strings
     * @return Strings in random order
     */
    public List<String> mixAnswers(List<String> temp){
        List<Integer> zahl = new ArrayList<>();
        List<String> oldInformations = temp;
        Random rand = new Random();
        while (zahl.size() < 4) {                                                  //r steht für die Anzahl an Fragen, bekommt man aus der Settings-Klasse
            int i = rand.nextInt(4)+1;                                            //neue Random Zahl
            if (!zahl.contains(i))                                                 //Liste darf diese Zahl nicht enthalten
                zahl.add(i);                                                           //dann wird sie geaddet
        }
        temp.set(1,oldInformations.get(zahl.get(0)));
        temp.set(2,oldInformations.get(zahl.get(1)));
        temp.set(3,oldInformations.get(zahl.get(2)));
        temp.set(4,oldInformations.get(zahl.get(3)));
        return temp;
    }


    /**
     *
     * @param answer given Answer
     * @return returns true, if the answer is correct
     */
    public boolean isCorrect(String answer) {
        if (answer == null || answer.equals("") && !questions.isEmpty()) throw new IllegalArgumentException();         //A muss Wert enthalten
        if (questions.get(actualQuestion).getCorrectAnswers().equals(answer)){                  //bei einer Lösung: A muss gleich der Lösung sein
            points +=1;
            return true;
        }
        return false;
    }


    public void saveStats(String givenAnswer, int time){
       inputInformations.add(
               new GetStats(frage.getQuestion(), frage.getCorrectAnswers(), givenAnswer, time, frage.getUsedChances(), points));

        questions.remove(actualQuestion);         //löschen der gespielten Frage aus der Liste
    }       



    public List<String> setText() {                                         //um texte der aktuell genutzten Frage zurückzugeben
        return mixAnswers(frage.getList());
    }

    public void decrementChances(){frage.decChances();}               //wurde eine Frage übersprungen, dann passe die genutzen Versuche in dem FragenObjekt an
                                                                                               //Frage stellt diese Funktion selbst bereit
    public int getRemainingChances(){return frage.getUsedChances();}  //zur Anzeige, wieviele Versuche man noch hat, bei dem letzten Versuch irrelevant

    public int getAllObjectsSize(){                                                             //Rückgabe, wie viele Fragen noch vorhanden sind
        return questions.size();
    }
    
    public int getPoints(){             
        return this.points;
    }

    public List<GetStats> printStats(){     //Erstelle LIste aus Objekten, adde jedesmal Instanz von addStats hinzu, um im laufenden Spiel darauf zuzugreifen
        return inputInformations;            //return Liste mit Stats    
    }                                      //Ausgabe für die Statistik
}
