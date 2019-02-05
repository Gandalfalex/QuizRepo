package Quiz_Packages;


import Quiz_Packages.File.FileController;
import Quiz_Packages.File.GUI_Frage;



public class Start_Question {

	/*
	 * Konstruktor der Klasse Start_Question als Main-Klasse des gesamten Projektes
	 * @param args als String-Array
	 * @return
	 */
    
    public static void main(String[] args) {			//statischer Konstruktor, Übergabe des String-Array args
        GUI_Frage gui = new GUI_Frage();				//erzeugen einer neuen Instanz der Klasse GUI_Frage -> Benutzeroberfläche für Frage wird erstellt
        gui.setVisible(true);							//Benutzeroberfläche wird sichtbar gemacht
        new FileController(gui);						//Razupaltuff?! erstelle neuen Filecontroller mit Parameter gui? was ist das?; Wofür wird args verwendet? (Titelzeile des Konstruktors)
        
    }
    
}
