
public class Start_Question {

    
    public static void main(String[] args) {
        GUI_Frage  gui = new GUI_Frage();
        gui.setVisible(true);
        new FileController(gui);
        
    }
    
}
