package Quiz_Packages.File;

import java.util.*;


/**
 * This class represents the backbone of the program.
 * it stores a list of all Questions and return the list if needed
 */
public class FragenKatalog {

    private ArrayList<Frage> listOfAllQuestions = new ArrayList<>();
    private static FragenKatalog fragenKatalog = null;

    /**
     * The Constructor needs the filepath of the textfile
     */
    private FragenKatalog(){ }

    public static FragenKatalog getInstance(){
        if (fragenKatalog == null) {
            fragenKatalog = new FragenKatalog();
        }
        return fragenKatalog;
    }

    /**
     * These function determines if a new question is viable by simply comparing the answer to all answers who are already part of the list
     * @param s
     * @return true, if valid, false if not
     */
    protected boolean validateNewQuestion(String s) {
        if (listOfAllQuestions.isEmpty()){
            return false;
        }
        else {
            System.out.println(s);
            s = s + System.getProperty("line.separator");
            for (Frage frage: listOfAllQuestions){

                if (frage.getQuestion().contains(s)){
                    System.out.print(" existiert schon");
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Adds the new question both to the list and the file, which is currently used
     * @param frage
     */
    protected void addQuestion(Frage frage){                                                // Add new Questions to a list of object
        listOfAllQuestions.add(frage);
    }


    /**
     * depended of the chosen settings, this function creates a list of QuestionObjects by reading from a file and adding specific or default chances to the objects
     * @return
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
