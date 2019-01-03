import java.util.*;


public class WorkingQuiz extends Observable{
    

    public List<List<String>> allObjects = new ArrayList<>();                                                          //gewünschte Liste, Nutzung gefordert, einfach durch FragenListe ersetzbar
    private List<Frage> allQuestionObjects;                                                                             //ÜbergabeListe aus dem FragenKatalog
    private List<Frage> questions = new ArrayList<>();                                                                  //Nutzbare Liste der Fragen
    private int actuallQuestion = 0;
    private List<GetStats> inputInformations = new ArrayList<>();
    private int points = 0;
    private int aOQ = 0;
   
    
    
    //Konstruktor
    public WorkingQuiz(List<Frage> allQuestionObjects, int amountOfQuestions) {
        if (allQuestionObjects.isEmpty()) throw new NullPointerException();
        this.allQuestionObjects = allQuestionObjects;
        if (amountOfQuestions != 0 && amountOfQuestions < allQuestionObjects.size())                            //setze die Anzahl an Fragen auf ein sinnvolles/mögliches Level
            this.aOQ = amountOfQuestions;
        else this.aOQ = allQuestionObjects.size();
        if (allObjects.isEmpty()){
            createWorkingList(getRandomQuestion(allQuestionObjects.size(),aOQ));                                                         //bereite QuizSpielen vor, nur beim ersten mal relevant
        }
    }
    
    
    public boolean prepare (){ 

        if (allObjects.size() == 0) return false;
        else {
            Random rand = new Random();
            actuallQuestion = rand.nextInt(allObjects.size());
            return true;
        }
    }










     public List<List<String>> createWorkingList (List<Integer> listOfQuestionNumbers) {
        if (allQuestionObjects.isEmpty()) throw new NullPointerException();                          //bekommt aus dem Fragenkatalog die Liste an FrageObjekten und erstellt daraus die genutzte verkettete Liste
        for (int i = 0; i < listOfQuestionNumbers.size(); i++) {                                     //Die Liste besitzt random zahlen aus den Fragen die genutzt werden sollen
            allObjects.add(mixAnswers(allQuestionObjects.get(listOfQuestionNumbers.get(i)).getList()));          //hohle für jede Frage die Liste; mixAnswers: mischt A,B,C,D untereinander (A könnte D sein etc.)
            questions.add(allQuestionObjects.get(listOfQuestionNumbers.get(i)));                     //kopiere jedes FragenObject, identische Liste, nur mehr Funktionen, für den Used-Status benötigt
        }
        return allObjects;
    }







    public List<Integer> getRandomQuestion(int range, int amount){
        Random rand = new Random();                                             //bekomme eine zufällige Zahl im Bereich der ganzen Fragen
        List<Integer> t = new ArrayList<>();                                    //erstelle neue Liste aus Zahlen, die für die Indizes der FragenListe stehen werden
        while (t.size() < amount) {                                                  //r steht für die Anzahl an Fragen, bekommt man aus der Settings-Klasse
            int i = rand.nextInt(range);                                            //neue Random Zahl
            if (!t.contains(i))                                                 //Liste darf diese Zahl nicht enthalten
            t.add(i);                                                           //dann wird sie geaddet
        }
        return t;       //return an createWorkingList (Liste|wartet auf den Callback (von getRandomQuestion)
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
        if (a == null || a == "" && !allObjects.get(actuallQuestion).isEmpty()) throw new IllegalArgumentException();         //A muss Wert enthalten
        if (allObjects.get(actuallQuestion).get(5).equals(a)){                  //bei einer Lösung: A muss gleich der Lösung sein
            points +=1;
            return true;
        }
        return false;
    }


    public boolean saveStats(String s, String givenAnswer, int time){
        loopThroughQuestions(s);        //entferne die Frage aus der Liste, um keine doppelten zu bekommen
        if (allObjects.get(actuallQuestion).contains(s) && allObjects.size() > 0){    //dafür muss es die Frage enthalten und größer 0 sein, sonst null element
            inputInformations.add(new GetStats(allObjects.get(actuallQuestion).get(0), allObjects.get(actuallQuestion).get(5), givenAnswer, time, questions.get(actuallQuestion).getActChances(), points));
            allObjects.remove(actuallQuestion);         //löschen der gespielten Frage aus der Liste
            questions.get(actuallQuestion).setUsed(true);   //setze Zustand der used-Variable dieser Frage auf true
            return true;
        }
        return false;
    }       
    
    
    
    
    
   
    
    public List<String> setText() {     //um texte der aktuell genutzten Frage zurückzugeben
        if (allObjects.get(actuallQuestion).size() == 7)        //Liste an Strings an Quiz-Controller (entspricht getText?)
            return allObjects.get(actuallQuestion);
        else return null;
    }

    public void loopThroughQuestions(String s){
        for (Frage f : allQuestionObjects ){
            if (f.getQuestion().equals(s)){
               f.setUsed(true);
               System.out.print(f.getQuestion());
            }
        }
    }
    public List<Frage> getAllQuestionObjects(){
        return allQuestionObjects;
    }




    public void decrementChances(){questions.get(actuallQuestion).decChances();}               //wurde eine Frage übersprungen, dann passe die genutzen Versuche in dem FragenObjekt an
                                                                                               //Frage stellt diese Funktion selbst bereit
    public int getRemainingChances(){return questions.get(actuallQuestion).getUsedChances();}  //zur Anzeige, wieviele Versuche man noch hat, bei dem letzten Versuch irrelevant

    public int getAllObjectsSize(){                                                             //Rückgabe, wie viele Fragen noch vorhanden sind
        return allObjects.size();
    }
    
    public int getPoints(){             
        return this.points;
    }

    public List<GetStats> printStats(){     //Erstelle LIste aus Objekten, adde jedesmal Instanz von addStats hinzu, um im laufenden Spiel darauf zuzugreifen
        return inputInformations;            //return Liste mit Stats    
    }                                      //Ausgabe für die Statistik
    
    
    
    
}
