package Quiz_Packages.Settings;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;


public class Setting_Controller {
    
    private GUI_Settings setting;                           //deklariert setting als Objekt der Klasse GUI_Settings
    private Settings settings = Settings.getInstance();     //erzeugt ein (neues) Objekt der Klasse Settings


    public Setting_Controller(GUI_Settings setting){        //Konstruktor erzeugt ein neues Objekt der Klasse Setting_Conroller
        this.setting = setting;
        setting.addListener(new Listen());

    }
    
    
    public class Listen implements ActionListener{                          //Klasse mit einem Interface ActionListener

        @Override
        public void actionPerformed(ActionEvent evt) {                      //wird ausgeführt, wenn Nutzer irgendwelche Eingaben betätigt

            if ((evt.getActionCommand().equals(GUI_Settings.NEWQUIZ))){     //überprüft ob der Button aus GUI-Settings gedrückt wurde, und führt eine Handlung aus
                String newFilePath = fileChoose();                          //übergibt den Pfad einer Datei die man auswählt
                settings.setOverwrittenFilePath(newFilePath);               //überschreibt overwrittenFilePath mit newFilePath
                setting.setQuizName(newFilePath);                           //QuizName zeigt den Pfad der ausgewählten Datei

                int i = getMaxQuestions(newFilePath);                       //i bekommt den Wert der maximalen Anzahl an möglichen Fragen
                if (i > 0) {                                                //falls Bed. erfüllt, dann wird das Label mit dem Wert von i gefüllt
                    setting.setMaxQuestion(i);
                    setting.setVisibleQ(true);                              //und Anzahl an Fragen wird sichtbar
                }
                else {
                    newFilePath ="";                                        //falls Bed. nicht erfüllt, dann wird newFilePath leer gelassen
                }
            }
        }
    }
    
    
    
    
    //@return Pfad der ausgewählten Datei
    private String fileChoose(){                                                
        JFileChooser fileChooser = new JFileChooser();                          //Suche SpeicherPfad für neue Datei, erstelle FileChooser
        int returnVal = fileChooser.showOpenDialog(null);                       //Fenster wird geöffnet um Datei auszuwählen
        String filePath = "";                                                   
        if (returnVal == JFileChooser.APPROVE_OPTION) {                         //wird Auswahl bestätigt, dann 
            File file = fileChooser.getSelectedFile();                          //Datei auswählen
            try {                                                               //versuche Dateipfad in filePath zu speichern
                filePath = file.getAbsolutePath();                              
            }
            catch (Exception e) {
                System.out.println("new filePath cant get detected");
            }
        } else {
            System.out.println("Abbruch durch User");
        }
        return filePath;
    }


    private int getMaxQuestions(String filePath){                               //teste auf max an ZeilenElementen
        int count = 0;                                                          //limitiere dann die Eingabe für die MaximalAnzahl an Fragen
        if (filePath.equals("")) return 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));   //BufferReader wird benötigt um die Datei zu lesen
            while ( in.readLine() != null ) {                                   //zählt die Zeilen die nicht leer sind
                count++;
            }
        }      
        catch (Exception ex) {
            ex.printStackTrace();
        }

        int c = (int) (Math.floor(count/7));
        System.out.print(count + "  " + c);
        if (c < 2)
            return 0;
        return c;
    }
    
    
    
}
