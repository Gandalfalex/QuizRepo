package Quiz_Packages.Settings;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;


public class Setting_Controller {
    
    private GUI_Settings setting;
    private String newFilePath ="";
    private Settings settings = Settings.getInstance();


    public Setting_Controller(GUI_Settings setting){
        this.setting = setting;
        setting.addListener(new Listen());

    }
    
    
    public class Listen implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent evt) {

            if ((evt.getActionCommand().equals(GUI_Settings.NEWQUIZ))){
                newFilePath = fileChoose();
                settings.setOverwrittenFilePath(newFilePath);
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
    
    
    
    
    
    private String fileChoose(){
        JFileChooser fileChooser = new JFileChooser();                          //Suche SpeicherPfad für neue Datei, erstelle FileChooser
        int returnVal = fileChooser.showOpenDialog(null);
        String filePath = "";
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                filePath = file.getAbsolutePath();              
            }
            catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Abbruch durch User");
        }
        return filePath;
    }


    private int getMaxQuestions(String filePath){                                //teste auf max an ZeilenElementen
        int count = 0;                                                          //limitiere dann die Eingabe für die MaximalAnzahl an Fragen
        if (filePath.equals("")) return 0;
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
