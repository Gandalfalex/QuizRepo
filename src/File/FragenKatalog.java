import java.util.*;




public class FragenKatalog {
    private String filePath;
    private List<Frage> listOfQuestions = new ArrayList<>();
    private Readtxt usedFile;

    
    
    
    public FragenKatalog(String filePath){
        this.filePath = filePath;
        usedFile = new Readtxt(filePath);
    }

    public boolean validNewQuestion(String s) {
        if (listOfQuestions.isEmpty()){
            return false;
        }
        else {
            for (int i = 0; i < listOfQuestions.size(); i++){
                s = s + System.getProperty("line.separator");
                if (s.equals(listOfQuestions.get(i).getQuestion())){
                    System.out.print(" existiert schon");
                    return false;
                }
            }
            return true;
        }
    }
    
    
    public void addQuestion(Frage frage){                                                // Add new Questions to a list of object
        listOfQuestions.add(frage);
        usedFile.addNewQuestion(frage.getList());
    }    



    
    
    public List<Frage> getListQuestionObjects(Readtxt readtxt, int limitChances){
        listOfQuestions.clear();
        if (readtxt == null) throw new NullPointerException();
        int limit = readtxt.readFile().size();
        for (int i = 0; i+7<=limit; i = i+7) {
            int diff = 1;
            List<String> t = readtxt.readFile();
            if (limitChances == 0) {
                if (t.get(i+6).contains("2")) diff = 2;                 //limit ist ein String, teste nur darauf
                if (t.get(i+6).contains("3")) diff = 3;
            } else diff = limitChances;
            listOfQuestions.add( new Frage(t.get(i),t.get(i+1),         //füge es als neue Frsge hinzu
                    t.get(i+2),t.get(i+3),t.get(i+4), t.get(i+5), diff));
        }
        return listOfQuestions;
    }
    
   
    
}
