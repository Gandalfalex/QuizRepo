package Quiz_Packages.File;

import java.util.*;


/**
 * This class represents the backbone of the program.
 * it stores a list of all Questions and return the list if needed
 */
public class FragenKatalog {

    private ArrayList<Frage> listOfAllQuestions = new ArrayList<>();		//als Datentyp der Array-Liste wird die Klasse Frage verwendet (ähnlich wie String für Zeichenketten verwendet wird)
    private static FragenKatalog fragenKatalog = null;						//statische Variable fragenkatalog

    /**
     * privater Konstrucktor, somit kann keine Klasse eine eigene Instanz des Konstrucktors erstellen
     * Andere Objekte können sich nur die eine existierende Instanz der Klasse über die "static getInstance()"-Methode hohlen
     * Somit besitzt jedes Objekt, welches diese Klasse benötigt, immer die gleiche Instanz
     *
     * Umsetzung des Singleton-Patterns
     */
    private FragenKatalog(){ }							//privater(?) Default-Konstruktor


    /**
     *
     * @return instanze dieser Klasse, sofern nicht null, wenn null, erstelle neue Instanz
     */
    public static FragenKatalog getInstance(){
        if (fragenKatalog == null) {
            fragenKatalog = new FragenKatalog();		//erstellt neuen fragenkatalog als Instanz des default-Konstruktors FragenKatalog()
        }
        return fragenKatalog;
    }

    /**
	 * überprüft ob eine neu hinzu zu fügende Frage schon vorhanden ist, indem ein übergebener String mit dem Fragenkatalog zeilenweise verglichen wird
	 * Wenn der übergebene String bereits verwendet wird, dann wird dies angezeigt, falls nicht kann die Frage zum Katalog hinzugefügt werden. (true/false)
     * These function determines if a new question is viable by simply comparing the answer to all answers who are already part of the list
     * @param question Frage, die hinzugefügt werden soll
     * @return true, if valid, false if not
     */
    protected boolean validateNewQuestion(String question) {
        if (listOfAllQuestions.isEmpty()){
            return false;
        }
        else {
            System.out.println(question);
            question = question + System.getProperty("line.separator");
            for (Frage frage: listOfAllQuestions){

                if (frage.getQuestion().contains(question)){
                    System.out.print(" existiert schon");
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Adds the new question both to the list and the file, which is currently used
     * @param frage Diese Frage soll zum Quiz hinzugefügt werden
     */
    protected void addQuestion(Frage frage){                                                // Add new Questions to a list of object
        listOfAllQuestions.add(frage);
    }


    /**
     * Die Funktion erstellt Fragen-Objeckte und fügt sie der Liste hinzu
     * @param file entspricht dem Textfile, Jede Zeile = ein Eintrag in der Liste
     * @param chanceTotal Anzahl an Chancen, wenn 0, dann übernehme aus File
     */
    protected void createQuestionList(ArrayList<String> file, int chanceTotal){
        listOfAllQuestions.clear();
        if (file.isEmpty()) throw new NullPointerException();
        int limit = file.size();
        for (int i = 0; i+7<=limit; i = i+7) {
            int chances = 1;

            if (chanceTotal <= 3 && chanceTotal >= 1) {
                chances = chanceTotal;
            } else {

                try {
                    String[] t = file.get(i + 6).split("");
                    chances = Integer.parseInt(t[0]);
                } catch (Exception e) {
                    System.out.println("cant parse string");
                }
            }
            listOfAllQuestions.add(new Frage(file.get(i), file.get(i + 1),         //füge es als neue Frsge hinzu
                        file.get(i + 2), file.get(i + 3), file.get(i + 4), file.get(i + 5), chances));

        }
    }


    public ArrayList<Frage> getQuestions(int amount){

        if (amount >= listOfAllQuestions.size() || amount <=0){
            amount = listOfAllQuestions.size()-1;
        }
        ArrayList<Frage> temp = new ArrayList<>();
        System.out.println(getSizeUsedQuestions());
        if (amount>=getSizeUsedQuestions()) {
            System.out.println("i should be here");
            for (Frage frage : listOfAllQuestions) {
                frage.setUsed(false);
            }
        }

        if (amount < 3) {
            return listOfAllQuestions;
        }
        else {
            ArrayList<Frage> tempUnUsed = new ArrayList<>();
            for (Frage frage: listOfAllQuestions){
                if (!frage.getUsed()){
                    tempUnUsed.add(frage);
                }
            }
            while (temp.size() != amount) {
                Random rand = new Random();
                int randomQ = rand.nextInt(tempUnUsed.size());
                if (!temp.contains(tempUnUsed.get(randomQ))) {
                    temp.add(tempUnUsed.get(randomQ));
                    tempUnUsed.get(randomQ).setUsed(true);
                }
            }
        }


        for (Frage frage: temp){
            System.out.print(frage.getQuestion());
        }
        return temp;
    }

    protected int getSizeUsedQuestions(){
        int amount = 0;
        for (Frage frage: listOfAllQuestions){
            if (!frage.getUsed()){
                amount++;
            }
        }
        return amount;
    }

}
