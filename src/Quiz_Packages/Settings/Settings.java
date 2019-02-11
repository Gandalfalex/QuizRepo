package Quiz_Packages.Settings;


public class Settings {

    private static Settings settings = null;            //setzt das Attribut auf null
    private String overwrittenFilePath = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";
    private int chances = 0;            //Anzahl der Versuche "chances" wird deklariert und initialisiert
    private int amountOfQuestions = 5;          //Anzahl der Fragen wird deklariert und initialisiert

    //nach dem Singleton ist der Konstruktor auf private gesetzt und genau ein Objekt wird in der Klassenmethode getInstance() erzeugt
    private Settings (){}               
    //@return gibt ein Objekt der Klasse Settings zurück
    public static Settings getInstance(){
        if (settings == null){              //überprüft ob ein Objekt schon erzeugt wurde
            settings = new Settings();      //falls nein, dann wird ein Objekt erzeugt
        }
        return settings;                    //falls ja, dann wird das Objekt zurück gegeben
    }


    //getter- und setter-Methoden für Attribut overwrittenFilePath
    public String getOverwrittenFilePath() {
        return overwrittenFilePath;
    }
    
    public void setOverwrittenFilePath(String overwrittenFilePath) {
        System.out.println("i am getting overwritten.... :/");
        this.overwrittenFilePath = overwrittenFilePath;
    }
    //getter- und setter-Methoden für Attribut chances
    public int getChances() {
        return chances;
    }

    public void setChances(String chancesString) {
        this.chances = getTotalAmountOfChances(chancesString);
    }
    //getter- und setter-Methoden für Attribut amountOfQuestions
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
        catch(Exception e){}                                                    //falls nicht funktioniert, wird es beim kompilieren keinen Fehler anzeigen
        if (s.equals("") || i == 0) return 0;                                   //mind. einer der Bed. muss erfüllt sein: s= leer, oder i= 0
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
        if (i < 0) return 5;                                                     //schaut einfach ob die Fragen nicht weniger als 0 sind
        return i;
    }

}
