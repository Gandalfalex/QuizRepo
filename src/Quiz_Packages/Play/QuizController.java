package Quiz_Packages.Play;

import Quiz_Packages.File.Readtxt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class QuizController {

    private WorkingQuiz work;
    private GUI_QuizSpielen play;
    private long time;

        
        
    public QuizController(GUI_QuizSpielen play, WorkingQuiz work){
        if (play == null) throw new NullPointerException();
        this.play = play;   
        this.work = work; 
        this.play.addListener(new Listener());
        setTexts(work.setText());
        time = System.currentTimeMillis();                                      //startzeit beim erzeugen des Objekts
    }
        
    
    
    
    public void setTexts(List<String> texts){
        if (texts == null || texts.isEmpty()) throw new IllegalArgumentException();
        play.SetButtonsAText(texts.get(1));                               //setzte die Button Texte
        play.SetButtonsBText(texts.get(2));
        play.SetButtonsCText(texts.get(3));
        play.SetButtonsDText(texts.get(4));
        play.SetLableText(texts.get(0));

        if (work.getRemainingChances() >0){                                     //überspringen Button wird nur benötigt, wenn man überspringen kann
            play.enableButtonWait();                                    
            play.SetButtonWaitText("Überspringen, es bleiben " + (work.getRemainingChances()+1) + " Versuche") ;
        }
        else {
            play.disableButtonWait();
        }
    } 
     
        
       
        
        
    public class Listener implements ActionListener{
            
        @Override
        public void actionPerformed(ActionEvent evt){
            if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONWAIT)){     //wenn eine Frage übersprungen wurde               
                work.decrementChances();                                        //Setze die Verbliebenen Chancen runter
                System.out.print(" Frage wurde übersprungen, es bleiben noch " + work.getRemainingChances() + " über \n");
                nextQuestion();                                                 //rufe die nöchste Frage auf
            } else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONA)) {
                work.isCorrect(play.getButtonsAText());
                work.saveStats(play.getLabel(), play.getButtonsAText(), getTime());     //entferne die Frage
                nextQuestion();
            } else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONB)) {
                work.isCorrect(play.getButtonsBText());
                work.saveStats(play.getLabel(), play.getButtonsBText(), getTime());     //entferne die Frage
                nextQuestion();
            } else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTONC)) {
                work.isCorrect(play.getButtonsCText());
                work.saveStats(play.getLabel(), play.getButtonsCText(), getTime());     //entferne die Frage
                nextQuestion();
            } else if (evt.getActionCommand().equals(GUI_QuizSpielen.BUTTOND)) {
                work.isCorrect(play.getButtonsDText());
                work.saveStats(play.getLabel(), play.getButtonsDText(), getTime());     //entferne die Frage
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
            if (work.prepare() == true && work.allObjects.size() >0){                       //teste, ob noch genug Fragen im Vorrat sind, prepare bereitet alle neuen Daten vor
                setTexts(work.setText());
                play.setFinishedVisible();                                                  //setTexts hohlt sich dann die Informationen für die Buttons
            }
            else {  
                String s = System.getProperty("user.dir") + "\\Stats.txt";                  //dort befindet sich die Stats datei
                Readtxt stats = new Readtxt(s);
                stats.addStats(work.printStats(), s);
                play.disableButtons();                                                      //mache die Buttons unsichtbar, besseres Ende wird noch gesucht
                play.disableButtonWait();
                play.SetLableText("Spiel beendet mit " + work.getPoints() + " Punkten");
                
            }       
        }
    



    }
