import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;


public class Setting_Controller {
    
    private GUI_Settings setting;
    private String newFilePath ="";


    public Setting_Controller(GUI_Settings setting){
        this.setting = setting;
        setting.addListener(new Listen());
    }
    
    
    public class Listen implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent evt) {
            if ((evt.getActionCommand().equals(GUI_Settings.NEWQUIZ))){
                newFilePath = fileChoose();
                setting.setQuizName(newFilePath);
                int i = getMaxQuestions(newFilePath);
                if (i > 0) {
                    setting.setMaxQuestion(i);
                    setting.setVisibleQ(true);
                }
                else {
                    newFilePath ="";
                }                
            }
        }
        
    }
    
    
    
    
    
    public String fileChoose(){
        JFileChooser fileChooser = new JFileChooser();                          //Suche SpeicherPfad für neue Datei, erstelle FileChooser
        int returnVal = fileChooser.showOpenDialog(null);
        String filePath = "";
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                filePath = file.getAbsolutePath();              
            } catch (Exception e) {}
        } else {
            System.out.println("Abbruch durch User");
        }
        return filePath;
    }


    public int getTotalAmountOfChances(String s){                               //bekomme den String aus dem TextFeld
        int i = 0;                                                      
        try{
            i = Integer.parseInt(s);                                            //Versuche aus dem String ein int zu machen
        }
        catch(Exception e){}
        if (s=="" || i == 0) return 0;
        if (i > 3)   return 3;                                                             //muss in den Grenzen sein, max 3 Chance
        else if (i < 1) return 1;
        return i;
    }

    public int getAmountOfQuestions(String s){                                  //Anzahl der Fragen
        int i = 0;                                                               //selbes Prinzip wie drüber, max-Angabe ist später dran
        try{
            i = Integer.parseInt(s);
        }
        catch(Exception e){}
        if (i < 0) return 5;
        return i;
    }


    public String getFilePath(){
        if (newFilePath != "" && newFilePath.contains(".txt"))              //gebe den Pfad zurück, wird für die ReadTxt gebraucht
            return this.newFilePath;                                              //leite nur .txt weiter    
        return "";                                                          //leerer Strring bedeutet StandartDatei
    }


    public int getMaxQuestions(String filePath){                                //teste auf max an ZeilenElementen
        int count = 0;                                                          //limitiere dann die Eingabe für die MaximalAnzahl an Fragen
        if (filePath == "") return 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));   //BufferReader wird dafür benötigt
            while ( in.readLine() != null ) {
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
