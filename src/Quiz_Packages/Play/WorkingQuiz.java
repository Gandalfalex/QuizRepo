package Quiz_Packages.Play;

import Quiz_Packages.File.Frage;
import Quiz_Packages.File.FragenKatalog;

import java.util.*;


public class WorkingQuiz extends Observable{
    

                                                                 //Nutzbare Liste der Fragen
    private int actualQuestion = 0;
    private List<GetStats> inputInformations = new ArrayList<>();
    private int points = 0;
    private FragenKatalog fragenKatalog = FragenKatalog.getInstance();
    private ArrayList<Frage> questions = new ArrayList<>();
    private Frage frage = null;
    private int limit = 0;
   
    
    
    //Konstruktor
    public WorkingQuiz(int amountOfQuestions) {
        questions = fragenKatalog.getQuestions(amountOfQuestions);
        limit = amountOfQuestions;
        System.out.println(questions.size());
    }
    
    
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
            int i =  (actualQuestion + rand.nextInt(questions.size()-1)+1) % questions.size();
            actualQuestion = i;
            frage = questions.get(actualQuestion);
        }

    }
   


    public List<String> mixAnswers(List<String> q){
        List<Integer> zahl = new ArrayList<>();
        List<String> t = q;
        Random rand = new Random();
        while (zahl.size() < 4) {                                                  //r steht für die Anzahl an Fragen, bekommt man aus der Settings-Klasse
            int i = rand.nextInt(4)+1;                                            //neue Random Zahl
            if (!zahl.contains(i))                                                 //Liste darf diese Zahl nicht enthalten
                zahl.add(i);                                                           //dann wird sie geaddet
        }
        q.set(1,t.get(zahl.get(0)));
        q.set(2,t.get(zahl.get(1)));
        q.set(3,t.get(zahl.get(2)));
        q.set(4,t.get(zahl.get(3)));
        return q;
    }






    public boolean isCorrect(String a) {
        if (a == null || a == "" && !questions.isEmpty()) throw new IllegalArgumentException();         //A muss Wert enthalten
        if (questions.get(actualQuestion).getCorrectAnswers().equals(a)){                  //bei einer Lösung: A muss gleich der Lösung sein
            points +=1;
            return true;
        }
        return false;
    }


    public boolean saveStats(String givenAnswer, int time){
       inputInformations.add(
               new GetStats(frage.getQuestion(), frage.getCorrectAnswers(), givenAnswer, time, frage.getUsedChances(), points));

        questions.remove(actualQuestion);         //löschen der gespielten Frage aus der Liste
        return true;
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
