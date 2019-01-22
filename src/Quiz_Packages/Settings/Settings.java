package Quiz_Packages.Settings;


public class Settings {

    private static Settings settings = null;
    private String overwrittenFilePath = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";
    private int chances = 0;
    private int amountOfQuestions = 5;

    private Settings (){}

    public static Settings getInstance(){
        if (settings == null){
            settings = new Settings();
        }
        return settings;
    }



    public String getOverwrittenFilePath() {
        return overwrittenFilePath;
    }

    public void setOverwrittenFilePath(String overwrittenFilePath) {
        System.out.println("i am getting overwritten.... :/");
        this.overwrittenFilePath = overwrittenFilePath;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(String chancesString) {
        this.chances = getTotalAmountOfChances(chancesString);
    }

    public int getAmountOfQuestions() {
        return amountOfQuestions;
    }

    public void setAmountOfQuestions(String amountOfQuestionsString) {
        this.amountOfQuestions = getAmountOfQuestions(amountOfQuestionsString);
    }


    private int getTotalAmountOfChances(String s){                               //bekomme den String aus dem TextFeld
        int i = 0;
        try{
            i = Integer.parseInt(s);                                            //Versuche aus dem String ein int zu machen
        }
        catch(Exception e){}
        if (s.equals("") || i == 0) return 0;
        if (i > 3)   return 3;                                                             //muss in den Grenzen sein, max 3 Chance
        else if (i < 1) return 1;
        return i;
    }



    private int getAmountOfQuestions(String s){                                  //Anzahl der Fragen
        int i = 0;                                                               //selbes Prinzip wie drüber, max-Angabe ist später dran
        try{
            i = Integer.parseInt(s);
        }
        catch(Exception e){}
        if (i < 0) return 5;
        return i;
    }

}
