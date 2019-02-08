package Quiz_Packages.Play;

import Quiz_Packages.File.Readtxt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class QuizController {
	
	// Variablen der Klasse QuizController
    private WorkingQuiz work;
    private GUI_QuizSpielen play;
    private long time;

        
    // Konstruktor    
    public QuizController(GUI_QuizSpielen play, WorkingQuiz work){			// übergebene Parameter play (aus GUI_Quizspielen) und work vom Typ WorkingQuiz
        if (play == null) throw new NullPointerException();					// wenn play leer -> Nullpointer-Exception
        this.play = play;   												// Zuoprdnung Parameter play zu Klassenvariable play
        this.work = work; 													// Zuordnung Parameter work zu Klassenvariable work
        this.play.addListener(new Listener());								// Erzeuge neuen Listener für Klassenvariable play
        setTexts(work.setText());											// Setze Text bei work
        time = System.currentTimeMillis();                               	// Startzeit beim Erzeugen des Objekts (für später festhalten)
    }
        
    
    
    /*
	 * Setter-Methode
	 * @param texts
	 * @return
	 */
    public void setTexts(List<String> texts){
        if (texts == null || texts.isEmpty()) throw new IllegalArgumentException(); 	// wenn text leer/null -> IllegalArgument-Exception
        play.SetButtonsAText(texts.get(1));                             	// Setze die verschiedenen  Buttontexte von play (Button A bis D)
        play.SetButtonsBText(texts.get(2));
        play.SetButtonsCText(texts.get(3));
        play.SetButtonsDText(texts.get(4));
        play.SetLableText(texts.get(0));									// Setze den Text vom Labelfeld
		//die Indices stellen die Frage sowie die Antworten dar
		
		//überspringen Button wird nur benötigt, wenn man überspringen kann; daher Überprüfung mit if-Schleife
        if (work.getRemainingChances() >0){                    				// wenn verbleibende Chancen größer als 0
            play.enableButtonWait();                                    	// aktiviere Button fürs Warten/Überspringen
            play.SetButtonWaitText("Frage überspringen, es bleiben " + (work.getRemainingChances()+1) + " Versuche") ;		//Text auf Überspringen-Button setzen
        }
        else {																// wenn keine Chancen mehr verbleiben
            play.disableButtonWait();										// deaktiviere Überspringen-Button
            play.SetButtonWaitText("Frage darf nicht mehr übersprungen werden") ;		// Setze Text des Buttons Überspringen
        }
    } 
     
        
       
        
    // Listener     
    public class Listener implements ActionListener{
            
        @Override  //Overrides a given function
        public void actionPerformed(ActionEvent evt){
            if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONWAIT)){     //wenn eine Frage übersprungen wurde               
                work.decrementChances();                                        //Setze die Verbliebenen Chancen runter
                System.out.print(" Frage wurde übersprungen, es bleiben noch " + work.getRemainingChances() + " über \n");
                nextQuestion();                                                 //rufe die nächste Frage auf
            }
            else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONA)) {
                work.isCorrect(play.getButtonsAText());
                work.saveStats(play.getButtonsAText(), getTime());     //entferne die Frage
                nextQuestion();
            }
            else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONB)) {
                work.isCorrect(play.getButtonsBText());
                work.saveStats(play.getButtonsBText(), getTime());     //entferne die Frage
                nextQuestion();
            }
            else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONC)) {
                work.isCorrect(play.getButtonsCText());
                work.saveStats(play.getButtonsCText(), getTime());     //entferne die Frage
                nextQuestion();
            }
            else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTOND)) {
                work.isCorrect(play.getButtonsDText());
                work.saveStats(play.getButtonsDText(), getTime());     //entferne die Frage
                nextQuestion();
            }

        }   
    }   
        
        
    
     public int getTime(){
           long newTime = System.currentTimeMillis();                           //aktuelle Systemzeit
           int t = Math.round(newTime-time);                                    //Differenz aus Startzeit und Aktueller Zeit = benötigte Zeit
           time = newTime;                                                      //setze aktuelle Zeit auf Startzeit, leichte verfälschung, aber irrelevant 
           return t;                                                            //gibt die benötigte Zeit zurück
        }
        
      
        
    public void nextQuestion(){  
        if (work.getAllObjectsSize()>=1){                                  //teste, ob noch genug Fragen im Vorrat sind, prepare bereitet alle neuen Daten vor
            work.calculateNextQuestion();
            setTexts(work.setText());
            play.setFinishedVisible();                                                  //setTexts holt sich dann die Informationen für die Buttons
        }
        else { 
            String s = System.getProperty("user.dir") + "\\Stats.txt";                  //dort befindet sich die Stats-Datei
            Readtxt stats = Readtxt.getInstance();
            stats.addStats(work.printStats(), s);
            play.disableButtons();                                                      //mache die Buttons unsichtbar, besseres Ende wird noch gesucht
            play.disableButtonWait();
            play.SetLableText("Spiel beendet mit " + work.getPoints() + " Punkten");
        }       
    }
    



    }
