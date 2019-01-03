package Quiz_Packages.File;
import Quiz_Packages.Play.GUI_QuizSpielen;
import Quiz_Packages.Play.QuizController;
import Quiz_Packages.Play.WorkingQuiz;
import Quiz_Packages.Settings.GUI_Settings;
import Quiz_Packages.Settings.Setting_Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;




public class FileController {
    
    
    
    private GUI_Frage gui_Frage;
    private List<String> emptyList = new ArrayList<>();
    private List<Frage> objectList = new ArrayList<>();
    private String path = System.getProperty("user.dir") + "\\src\\Quiz_Packages\\new.txt";                                            //StandartSpeicherpfad, wird sonst nachträglich verändert
    private String stat = System.getProperty("user.dir") + "\\Stats.txt";
    private int chances = 0;
    private int amountOfQuestions = 0;
    
    private FragenKatalog fragenKatalog;                                                                                //WorkingQuiz benötigt die Liste, auch werden hier neue Fragen erstellt
    private GUI_Settings setting = new GUI_Settings();                                                                  //Einstellungen anpassen (Speicherpfad, allgemeine Chancen und anzahl der Fragen
    private Setting_Controller sc = new Setting_Controller(setting);
    private GUI_QuizSpielen play;
    private WorkingQuiz work;
    private Readtxt stats = new Readtxt(stat);
    
   
    
    
    
    
    public FileController(GUI_Frage gui_Frage){
        if (gui_Frage == null) throw new NullPointerException();  //Fehlervermeidung durch Test ob dateien vorhanden oder leer
        this.gui_Frage = gui_Frage;                                //globale Variable speichern
        this.gui_Frage.addListener(new Listen());               //erstellt Eventlistener -> reagiert darauf, wenn Button gedrückt wird in GUI-Klasse
    }
    
    
    

    public class Listen implements ActionListener{  //interne klasse
        
      //if-else-Fragen  
        //wenn Button gedrückt wird, Abfrage welcher Button (welcher Zusatz) gedrückt wird und was er machen soll
        @Override
        public void actionPerformed(ActionEvent evt){
            if (evt.getActionCommand().equals(GUI_Frage.START_GAME)){   //wenn Button gedrückt wird, wird Start Game ausgefürt
                prepareSecondGUI();
            }
            else if (evt.getActionCommand().equals(GUI_Frage.NEW_QUESTION)){                     //neue Frage hinzufügen
                manageAddQuestion();
            }
            else if (evt.getActionCommand().equals(GUI_Frage.SETTINGS)){                    //Setting ruft eine neue Klasse auf, die hier einen neuen Listener aufruft
                setting.addListener(new Listen());                                          //der Listener liegt für den Übernehmen Button hier, 
                setting.setVisible(true);                                                   //GUI dazu wird auf Sichtbar gemacht, GUI_Frage tritt in den Hintergrund
                gui_Frage.dispose();                                                        //Informationen werden erst bei Übernehmen gehohlt, sonst nicht
            }
            else if (evt.getActionCommand().equals(GUI_Settings.FINISHED)){
                String s = sc.getFilePath();
                if (s != "")  path = s;                                                 //Übernehme den Pfad der neuen Datei, sofern möglich
                chances = sc.getTotalAmountOfChances(setting.getAOC());                 // 0 ist der standartfall, bei keiner eingabe bekommt man 0 zurück
                amountOfQuestions = sc.getAmountOfQuestions(setting.getAOQ());

                gui_Frage.setVisible(true);
                setting.dispose();
            }
            else if(evt.getActionCommand().equals(GUI_QuizSpielen.FINISHED_QUIZ)){          //wenn die Statistik in der GUI_Quiz spielen aufgerufen wird
                System.out.print("gg");
                if (work != null){
                    try{
                        stats.addStats(work.printStats(), stat);
                        Runtime.getRuntime().exec("notepad.exe \"" + stat);         //öffne Texteditor mit der Datei
                    } 
                    catch (Exception e) {}
                    if(work.getAllObjectsSize() == 0){                                         //nur wenn Quiz durchespielt ist
                        objectList = work.getAllQuestionObjects();                              //bekomme die komplette Liste an Fragen aus der Instanz von working Quiz zurück
                        deleteUsedQuestions();                                                  //suche alle genutzten Fragen raus, lösche diese
                        gui_Frage.setVisible(true);                                             //sichtbarkeit anpassen
                        play.dispose();
                    }
                }
            }
        }
    }



    public void deleteUsedQuestions(){
        for (int i = 0; i<objectList.size(); i++){                                              //for each loop
            if (objectList.get(i).getUsed()== true)                                             //wenn Frage genutzt, dann lösche diese
             objectList.remove(i);                                                              //somit enthält der FileController immmer eine Liste an Fragen, die nicht genutzt wurden
        }
    }


    public void prepareSecondGUI(){ 
        try{                                                                                            //Try-Catsch um Fehler abzufangen, falls etwas nicht geladen werden kann etc.
            if (objectList.size() >= sc.getAmountOfQuestions(setting.getAOQ()) && !objectList.isEmpty()){ //schaue Liste an, ist beim ersten Durchgang leer,  bei weiteren Durchgängen muss Anzahl an Fragen))
                work = new WorkingQuiz(objectList, sc.getAmountOfQuestions(setting.getAOQ()));           //>= sein als genutzte Fragen, erstelle WorkingQuiz-Instanz mit diesen Bedingungen
            }
            else if (fragenKatalog != null) {                                                           //wenn Einstellungen durchgeführt wurden, wird kein neuer FraagenKatalog benötigt
                work = new WorkingQuiz(                                                                 //Konstruktor für working Quiz brauht Liste mit allen fragen und Anzahl an Fragen mit denenn man spielen will
                        fragenKatalog.getListQuestionObjects(                                           //returned <liste mit Fragenobjekten (braucht Argumente mit Readtxt (liest und schreibt), welche Speicherpfad braucht)
                                new Readtxt(path), sc.getTotalAmountOfChances(setting.getAOC())),       //neue Instanz von Readtxt mit Speicherpfad
                        sc.getAmountOfQuestions(setting.getAOQ()));              //SC = settingscntroller; Amount of..wird weitergegeben                                  //kontrolliere ob Fragenkatalog leer ist (null), dann erstelle Standardfragenkatalog
            }
            else {                                                                                      //StandartFall beim ersten Durchgang, keine Einstellungen durchgeführt
                fragenKatalog = new FragenKatalog(path);                                                //erstelle neuen FragenKatalog mit FilePath Default-Datei
                                                                                                        //work = Instanz der Klasse Workinguiz
                work = new WorkingQuiz(                                                                 //Konstruktor für working Quiz brauht Liste mit allen fragen und Anzahl an Fragen mit denenn man spielen will
                    fragenKatalog.getListQuestionObjects(                                               //returned <liste mit Fragenobjekten (braucht Argumente mit Readtxt (liest und schreibt), welche Speicherpfad braucht)
                            new Readtxt(path), sc.getTotalAmountOfChances(setting.getAOC())),           //neue Instanz von Readtxt mit Speicherpfad
                    sc.getAmountOfQuestions(setting.getAOQ()));                  //SC = settingscntroller; Amount of..wird weitergegeben
            }


            work.prepare();                                                                         //bereite die ModellKlasse vor
            play = new GUI_QuizSpielen();                                                           //erstelle GUI Element
            play.addListener(new Listen());                                                         //füge einen Listener hier für die play-Instanz ein, da hier die Stats gehandelt werden
            new QuizController(play, work);                                                         //erstelle den Controller, benötigt beide Objekte, Objekte kennen sich selber nicht
            play.setVisible(true);                                                                  //folgende 2 Zeilene: play = Jrame mit den Fragen überlagert und sichtbar gemacht
            gui_Frage.dispose();                                                                    //default (Start)-Frame unsichtbar gemacht
        }
        catch(Exception e){
            System.out.print("Fehler aufgetreten beim erstellen der Objekte für Quiz_Spielen");
            //wenn Fehler auftritt, Ausgabe auf Konsole
        }
    }




    public void manageAddQuestion(){

        if(getInputs().isEmpty() || validInput(getInputs()) == false)
            System.out.print("falsche Eingabe");
        else{
            //bei richtiger Eingabe erstelle neuen FragenKatalog, anfangs Standard-Speicher
            fragenKatalog = new FragenKatalog(path);                                        //Pfad, kann über Setting-Controller aber verändert werden
            fragenKatalog.getListQuestionObjects( new Readtxt(path), sc.getTotalAmountOfChances(setting.getAOC()));    //lese die Datei bereits aus, um zu schauen, ob die Frage bereits vorhanden ist
            List<String> t = getInputs();
            if (fragenKatalog.validNewQuestion(t.get(0)) == true) {                                                                 //schaue ob die Frage bereits existiert
                System.out.print("Frage wird hinzugefügt");
                fragenKatalog.addQuestion(new Frage(t.get(0), t.get(1), t.get(2), t.get(3), t.get(4), t.get(5), getStringToInt(getInputs().get(6))));
            }
                                //erstelle neue Frage und füge diese in den FragenKatalog ein, Validität wird durch Funktionen geprüft
        }

    }







    
    public List<String> getInputs(){
        List<String> input = new ArrayList<>();                                 //erstelle eine neue Liste, inder die Texte eingefügt werden
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
        return emptyList;                                                              //wenn nicht lesbar für Programm, emptyList = leere Liste, wird vom Programm abgefangen
    }


    public boolean validInput(List<String> t){
        List<String> inputs = t;                                                 //Liste kopieren um Datenverlust aus globaler Variable zu vermeiden
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
            if (a <= 1) return true;                                            //darf nur einmal vorhanden sein (nur einmal richtige ANtwort erlaubt)
        }            
        return false;                                                           //sonst return false, Frage nicht gespeichert
    }


    public int getStringToInt(String s){
        if (s.equals("2")) return 2;                                            //nur max 3 Möglichkeiten
        else if (s.equals("3")) return 3;                                       //einfach auf inhalt prüfen
        else return 1;                                                          //standard Fall
    }
    
    
    
}
