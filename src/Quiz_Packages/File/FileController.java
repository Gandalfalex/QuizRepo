package Quiz_Packages.File;
import Quiz_Packages.Play.GUI_QuizSpielen;
import Quiz_Packages.Play.QuizController;
import Quiz_Packages.Play.WorkingQuiz;
import Quiz_Packages.Settings.GUI_Settings;
import Quiz_Packages.Settings.Setting_Controller;
import Quiz_Packages.Settings.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


/**
 * Controller Class of an MVC Model
 */
public class FileController {
    
    
    //Variablen der Klasse "FileController"
    private GUI_Frage gui_Frage;																//neue Instanz gui_Frage der Klasse GUI_Frage
    private String path = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";     //StandartSpeicherpfad für Fragenkatalog, wird sonst nachträglich verändert
    private String stat = System.getProperty("user.dir") + "\\Stats.txt";						//neues String-Element für die Statistik + Speicherort
	
    private FragenKatalog fragenKatalog;                                                        //neue Instanz fragenkatalog der Klasse FragenKtalog -> WorkingQuiz benötigt die Liste, auch werden hier neue Fragen erstellt
    private GUI_Settings setting = new GUI_Settings();                                          //Einstellungen anpassen (Speicherpfad, allgemeine Chancen und Anzahl der Fragen)
    private Settings settings = Settings.getInstance();											// ? Was passiert hier?
    private GUI_QuizSpielen play;																//Variable play vom Typ GUI_QuizSpielen?
    private WorkingQuiz work;																	// -''-
    private Readtxt workWithFiles;																// ?

	
	//Konstruktor
    /**
     * Der FileController benötigt eine Instanz einer grafischen Benutzeroberfläche (needs an instance of a graphical unit interface)
     * @param gui_Frage (ist Instanz von JFrame) is an instance of the JFrame
     */
    public FileController(GUI_Frage gui_Frage){
        if (gui_Frage == null) throw new NullPointerException();  		//Fehlervermeidung durch Test ob Dateien vorhanden oder leer (Nullpointerexception)
        this.gui_Frage = gui_Frage;                               		//globale Variable speichern
        this.gui_Frage.addListener(new Listen());               		//erstellt Eventlistener -> reagiert darauf, wenn Button gedrückt wird in GUI-Klasse
        workWithFiles = Readtxt.getInstance();							//workWithFiles bezieht Infos aus Objekt von Readtxt
        fragenKatalog = FragenKatalog.getInstance();					//fragenkatalog bekommt Infos aus Objekt von FragenKatalog
    }


    /**
     * internal class which implements an actionListener
     * Reacts on everything that happens on the GUI
     */
    public class Listen implements ActionListener{  //interne klasse
        
      //if-else-Fragen  
        //wenn Button gedrückt wird, Abfrage welcher Button (welcher Zusatz) gedrückt wird und was er machen soll

        /**
         * based on the event, this function determines what to do next
         * it will start the game, add a new question-object, moves to the setting-controller or finish the quiz
         * @param evt as some ActionEvent, like clicking a button
         */
        @Override
        public void actionPerformed(ActionEvent evt){
            if (evt.getActionCommand().equals(GUI_Frage.START_GAME)){   //wenn Button gedrückt wird, wird Start Game ausgefürt
                prepareQuiz();
            }
            else if (evt.getActionCommand().equals(GUI_Frage.NEW_QUESTION)){                     //neue Frage hinzufügen
                manageAddQuestion();
            }
            else if (evt.getActionCommand().equals(GUI_Frage.SETTINGS)){                    //Setting ruft eine neue Klasse auf, die hier einen neuen Listener aufruft
                setting.addListener(new Listen());                                          //der Listener liegt für den Übernehmen Button hier, 
                setting.setVisible(true);
                Setting_Controller setting_controller = new Setting_Controller(setting);                                                            //GUI dazu wird auf Sichtbar gemacht, GUI_Frage tritt in den Hintergrund
                gui_Frage.dispose();                                                        //Informationen werden erst bei Übernehmen gehohlt, sonst nicht
            }
            else if (evt.getActionCommand().equals(GUI_Settings.FINISHED)){

                if (settings.getOverwrittenFilePath().equals("")) {
                  //  settings.setOverwrittenFilePath(path);                                                 //Übernehme den Pfad der neuen Datei, sofern möglich
                    settings.setAmountOfQuestions(setting.getAmountOfQuestions());
                    settings.setChances(setting.getAmountOfChances());
                }

                gui_Frage.setVisible(true);
                setting.dispose();
            }
            else if(evt.getActionCommand().equals(GUI_QuizSpielen.FINISHED_QUIZ)){          //wenn die Statistik in der GUI_Quiz spielen aufgerufen wird
                if (work != null){
                    try{
                        workWithFiles.addStats(work.printStats(), stat);
                        Runtime.getRuntime().exec("notepad.exe \"" + stat);         //öffne Texteditor mit der Datei
                    } 
                    catch (Exception e) {
                        System.out.println("konnte Notepad nicht öffnen");
                    }

                    if(work.getAllObjectsSize() == 0){                                         //nur wenn Quiz durchespielt ist
                                                                                                        //suche alle genutzten Fragen raus, lösche diese
                        gui_Frage.setVisible(true);                                             //sichtbarkeit anpassen
                        play.dispose();
                    }
                }
            }
        }
    }


    /**
     * this functions prepares the second mvc
     * it creates a list of Questions, creates a new Controller, JFrame, and Model and set's up the listeners
     */
    private void prepareQuiz(){
        System.out.println(settings.getOverwrittenFilePath());
        if (fragenKatalog.getSizeUsedQuestions()==0) {
            fragenKatalog.createQuestionList(workWithFiles.readFile(settings.getOverwrittenFilePath()), settings.getChances());

            System.out.println(" mein Pfad ist gerade : " +settings.getOverwrittenFilePath());

        }
        work = new WorkingQuiz(settings.getAmountOfQuestions());
        work.calculateNextQuestion();                                                                         //bereite die ModellKlasse vor
        play = new GUI_QuizSpielen();                                                           //erstelle GUI Element
        play.addListener(new Listen());                                                         //füge einen Listener hier für die play-Instanz ein, da hier die Stats gehandelt werden
        new QuizController(play, work);                                                         //erstelle den Controller, benötigt beide Objekte, Objekte kennen sich selber nicht
        play.setVisible(true);                                                                  //folgende 2 Zeilene: play = Jrame mit den Fragen überlagert und sichtbar gemacht
        gui_Frage.dispose();                                                                    //default (Start)-Frame unsichtbar gemacht

    }


    /**
     * validates the Question and checks the inputs
     */
    private void manageAddQuestion(){

        if(getInputs().isEmpty() || !validInput(getInputs()))
            System.out.print("falsche Eingabe");
        else{
            //bei richtiger Eingabe erstelle neuen FragenKatalog, anfangs Standard-Speicher
            fragenKatalog = FragenKatalog.getInstance();
            workWithFiles.setFileName(path);
            fragenKatalog.createQuestionList(workWithFiles.readFile(path), settings.getChances());
            ArrayList<String> temp = getInputs();

            if (fragenKatalog.validateNewQuestion(temp.get(0))) {                                                                 //schaue ob die Frage bereits existiert
                System.out.print("Frage wird hinzugefügt");
                fragenKatalog.addQuestion(new Frage(temp.get(0), temp.get(1), temp.get(2), temp.get(3), temp.get(4),
                        temp.get(5), getStringToInt(getInputs().get(6))));
            }
            workWithFiles.addNewQuestion(temp);
                                //erstelle neue Frage und füge diese in den FragenKatalog ein, Validität wird durch Funktionen geprüft
        }

    }


    /**
     *
     * @return ArrayList</String> that contains all informations
     */
    private ArrayList<String> getInputs(){
        ArrayList<String> input = new ArrayList<>();                                 //erstelle eine neue Liste, inder die Texte eingefügt werden
                                                                                //aber nur, wenn die eingabe nicht leer ist
        if (!gui_Frage.getQuestion().isEmpty()){input.add(gui_Frage.getQuestion());}
        if (!gui_Frage.getAntwortA().isEmpty()){input.add(gui_Frage.getAntwortA());}
        if (!gui_Frage.getAntwortB().isEmpty()){input.add(gui_Frage.getAntwortB());}
        if (!gui_Frage.getAntwortC().isEmpty()){input.add(gui_Frage.getAntwortC());}
        if (!gui_Frage.getAntwortD().isEmpty()){input.add(gui_Frage.getAntwortD());}
        if (!gui_Frage.getRichtigeAntwort().isEmpty()){input.add(gui_Frage.getRichtigeAntwort());}
        if (!gui_Frage.getChances().isEmpty()){input.add(gui_Frage.getChances());}
            if (input.size() == 7) {                                                   //7 weil Zeilenanzahl von Frage, Antwort und Chance , wenn alle Eingaben da sind, muss die Liste 7 lang sein
            return input;                                                              //teste darüber hinaus noch auf Validität   
        }   
        return new ArrayList<>();                                                              //wenn nicht lesbar für Programm, emptyList = leere Liste, wird vom Programm abgefangen
    }

    /**
     *
     * @param temp List of Inputs
     * @return true, if there is an correct answer
     */
    private boolean validInput(List<String> temp){
        List<String> inputs = temp;                                                 //Liste kopieren um Datenverlust aus globaler Variable zu vermeiden
        String correct = inputs.get(5);                                         //hohle die richtige Antwort, speichere
        inputs.remove(0);                                                 //und entferne sie aus der Liste
        inputs.remove(4);                                                 //entferne auch Frage und Chancenzahl  //keine schöne Lösung, aber funktioniert
        inputs.remove(4);
        if (inputs.contains(correct)){                                          //teste. ob der String noch vorhanden ist
            int a = 0;      
            for (int i = 0; i< inputs.size()-1; i++){                           //schaue, dass er nicht mehrfach vorhanden ist
                if (inputs.get(i).equals(correct)){
                    a += 1;
                }
            }
            //darf nur einmal vorhanden sein (nur einmal richtige ANtwort erlaubt)
            return a <= 1;
        }            
        return false;                                                           //sonst return false, Frage nicht gespeichert
    }

    /**
     *
     * @param s should be an integer
     * @return integer, converts the String to an int or returns 1
     */
    private int getStringToInt(String s){
        int value = 1;
        try{
            value = Integer.parseInt(s)%4;
        }
        catch (Exception e){
            System.out.println("cant convert anything");
        }
        System.out.println(value);
        if (value==0){
            return 1;
        }
        return value;
    }
    
    
    
}
